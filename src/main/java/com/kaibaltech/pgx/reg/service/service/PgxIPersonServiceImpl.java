package com.kaibaltech.pgx.reg.service.service;

import com.kaibaltech.pgx.reg.service.dto.PgxIPersonRequestDTO;
import com.kaibaltech.pgx.reg.service.dto.PgxIPersonResponseDTO;
import com.kaibaltech.pgx.reg.service.entity.PgxIPerson;
import com.kaibaltech.pgx.reg.service.exception.PersonServiceBusinessException;
import com.kaibaltech.pgx.reg.service.repository.PgxIPersonRepository;
import com.kaibaltech.pgx.reg.service.util.ValueMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class PgxIPersonServiceImpl implements PgxIPersonService {

    private final PgxIPersonRepository repository;

    private static final String PERSON_CACHE = "personCache";

    @Override
    @Cacheable(value = PERSON_CACHE)
    public List<PgxIPersonResponseDTO> getAllPersons() {
        log.info("PgxIPersonServiceImpl:getAllPersons execution started.");
        try {
            return repository.findAll()
                    .stream()
                    .map(this::convertToDTO)
                    .toList();
        } catch (RuntimeException ex) {
            log.error("Exception occurred while retrieving persons from database, Exception message {}", ex.getMessage());
            throw new PersonServiceBusinessException("Exception occurred while fetch all persons from Database");
        }
    }

    @Override
    public PgxIPersonResponseDTO createNewPerson(PgxIPersonRequestDTO requestDTO) {
        if (checkEmailExists(requestDTO))
            throw new PersonServiceBusinessException("Email already exist");
        try {
            log.info("PgxIPersonServiceImpl:createNewPerson execution started.");
            PgxIPerson person = convertToEntity(requestDTO);
            log.debug("PgxIPersonServiceImpl:createNewPerson request parameters {}", ValueMapper.jsonAsString(requestDTO));

            PgxIPerson personResult = repository.save(person);
            return convertToDTO(personResult);
        } catch (RuntimeException ex) {
            log.error("Exception occurred while persisting person to database , Exception message {}", ex.getMessage());
            throw new PersonServiceBusinessException("Exception occurred while create a new person");
        }
    }

    private boolean checkEmailExists(PgxIPersonRequestDTO requestDTO) {
        return repository
                .checkEmailExists(requestDTO.getEmailAddress())
                .map(PgxIPerson::getEmailAddress)
                .isPresent();
    }

    private PgxIPerson convertToEntity(PgxIPersonRequestDTO requestDTO) {
        return PgxIPerson
                .builder()
                .firstName(requestDTO.getFirstName())
                .lastName(requestDTO.getLastName())
                .middleName(requestDTO.getMiddleName())
                .nickName(requestDTO.getNickName())
                .emailAddress(requestDTO.getEmailAddress())
                .pgxIPersonGender(requestDTO.getPgxIPersonGender())
                .phoneNumbers((requestDTO.getPhoneNumbers()))
                .isAnnouncer(requestDTO.getIsAnnouncer())
                .isAdmin(requestDTO.getIsAdmin())
                .dateCreated(LocalDate.now())
                .build();
    }

    private PgxIPersonResponseDTO convertToDTO(PgxIPerson person) {
        return PgxIPersonResponseDTO
                .builder()
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .middleName(person.getMiddleName())
                .nickName(person.getNickName())
                .build();
    }
}
