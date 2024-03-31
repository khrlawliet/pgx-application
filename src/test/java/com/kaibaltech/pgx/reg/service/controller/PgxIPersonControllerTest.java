package com.kaibaltech.pgx.reg.service.controller;

import com.kaibaltech.pgx.reg.service.dto.APIResponse;
import com.kaibaltech.pgx.reg.service.dto.PgxIPersonRequestDTO;
import com.kaibaltech.pgx.reg.service.dto.PgxIPersonResponseDTO;
import com.kaibaltech.pgx.reg.service.service.PgxIPersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PgxIPersonControllerTest {
    @Mock
    private PgxIPersonService service;

    @InjectMocks
    private PgxIPersonController controller;

    private PgxIPersonRequestDTO requestDTO;
    private PgxIPersonResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        requestDTO = new PgxIPersonRequestDTO();
        responseDTO = new PgxIPersonResponseDTO();
    }

    @Test
    void createNewPerson() {
        when(service.createNewPerson(requestDTO)).thenReturn(responseDTO);
        ResponseEntity<APIResponse<PgxIPersonResponseDTO>> responseEntity = controller.createNewPerson(requestDTO);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(responseDTO, responseEntity.getBody().getResults());
    }

    @Test
    void fetchAllPersons() {
        List<PgxIPersonResponseDTO> expectedPersons = Arrays.asList(new PgxIPersonResponseDTO(), new PgxIPersonResponseDTO());
        when(service.getAllPersons()).thenReturn(expectedPersons);

        ResponseEntity<APIResponse<List<PgxIPersonResponseDTO>>> responseEntity = controller.fetchAllPersons();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedPersons, responseEntity.getBody().getResults());
    }

    @Test
    void getPersonById() {
        String id = "1";
        when(service.getPersonById(id)).thenReturn(responseDTO);

        ResponseEntity<APIResponse<PgxIPersonResponseDTO>> responseEntity = controller.getPersonById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseDTO, responseEntity.getBody().getResults());
    }


}