package com.kaibaltech.pgx.reg.service.service;

import com.kaibaltech.pgx.reg.service.dto.PgxIPersonRequestDTO;
import com.kaibaltech.pgx.reg.service.dto.PgxIPersonResponseDTO;
import com.kaibaltech.pgx.reg.service.entity.PgxIPerson;
import com.kaibaltech.pgx.reg.service.exception.PersonNotFoundException;
import com.kaibaltech.pgx.reg.service.exception.PersonServiceBusinessException;
import com.kaibaltech.pgx.reg.service.repository.PgxIPersonRepository;
import com.mongodb.MongoException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PgxIPersonServiceTest {

    @Mock
    private PgxIPersonRepository repository;

    @InjectMocks
    private PgxIPersonServiceImpl service;

    private PgxIPerson person;

    private PgxIPersonRequestDTO personDTO;


    @BeforeEach
    public void setUp() {
        person = PgxIPerson
                .builder()
                .firstName("kervin")
                .lastName("bal")
                .middleName("hom")
                .nickName("kai")
                .emailAddress("test@gmail.com")
                .dateCreated(LocalDate.now())
                .build();

        personDTO = PgxIPersonRequestDTO
                .builder()
                .firstName(person.getFirstName())
                .lastName(person.getLastName())
                .middleName(person.getMiddleName())
                .nickName(person.getNickName())
                .emailAddress(person.getEmailAddress())
                .build();
    }

    @DisplayName("test for getAllPersons method")
    @Test
    void shouldGetAllPersons() {
        // given
        given(repository.findAll()).willReturn(singletonList(person));

        //when
        List<PgxIPersonResponseDTO> personList = service.getAllPersons();

        //then
        assertThat(personList).isNotEmpty();
    }

    @DisplayName("test for getPersonById method")
    @Test
    void shouldGetPersonById() {
        // given
        given(repository.findById("1")).willReturn(Optional.of(person));

        // when
        PgxIPersonResponseDTO expected = service.getPersonById("1");

        // then
        assertThat(expected).isNotNull();
    }

    @DisplayName("test for db connection failure while fetching person list")
    @Test
    void shouldFailWhenFetchHavingDbIssue() {
        // when
        when(repository.findAll()).thenThrow(MongoException.class);

        //then
        PersonServiceBusinessException exception =
                assertThrows(PersonServiceBusinessException.class,
                        () -> service.getAllPersons());

        assertThat(exception.getMessage())
                .contains("Exception occurred while fetch all persons from Database");

    }

    @DisplayName("test for createNewPerson method")
    @Test
    void shouldCreateNewPerson() {
        // given
        given(repository.checkEmailExists(person.getEmailAddress()))
                .willReturn(Optional.empty());

        given(repository.save(person)).willReturn(person);

        // when
        PgxIPersonResponseDTO expected = service.createNewPerson(personDTO);

        // then
        assertThat(expected).isNotNull();
    }

    @DisplayName("test for checkEmailExists method")
    @Test
    void shouldFailWhenEmailExists() {
        //when
        when(repository.checkEmailExists(person.getEmailAddress()))
                .thenReturn(Optional.ofNullable(person));


        PersonServiceBusinessException exception =
                assertThrows(PersonServiceBusinessException.class,
                        () -> service.createNewPerson(personDTO));

        //then
        assertEquals("Email already exist", exception.getMessage());
        verify(repository, never()).save(any(PgxIPerson.class));
    }

    @DisplayName("test for null person")
    @Test
    void shouldFailWhenPersonIsNull() {
        //given
        given(repository.save(person))
                .willReturn(null);

        //then
        PersonServiceBusinessException exception =
                assertThrows(PersonServiceBusinessException.class,
                        () -> service.createNewPerson(personDTO));

        assertThat(exception.getMessage())
                .contains("Exception occurred while create a new person");
    }

    @DisplayName("test for finding not registered person")
    @Test
    void shouldFailWhenPersonIsNotFound() {
        // given
        given(repository.findById("1")).willReturn(Optional.empty());

        // then
        PersonNotFoundException exception =
                assertThrows(PersonNotFoundException.class,
                        () -> service.getPersonById("1"));

        assertThat(exception.getMessage())
                .contains("Exception occurred while fetch person by id from Database");
    }
}