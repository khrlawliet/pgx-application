package com.kaibaltech.pgx.reg.service.service;

import com.kaibaltech.pgx.reg.service.dto.PgxIPersonRequestDTO;
import com.kaibaltech.pgx.reg.service.dto.PgxIPersonResponseDTO;

import java.util.List;

public interface PgxIPersonService {

    List<PgxIPersonResponseDTO> getAllPersons();

    PgxIPersonResponseDTO getPersonById(String id);

    PgxIPersonResponseDTO createNewPerson(PgxIPersonRequestDTO requestDTO);
}
