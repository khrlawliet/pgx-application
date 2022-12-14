package com.kaibaltech.pgx.reg.service.controller;

import com.kaibaltech.pgx.reg.service.dto.APIResponse;
import com.kaibaltech.pgx.reg.service.dto.PgxIPersonRequestDTO;
import com.kaibaltech.pgx.reg.service.dto.PgxIPersonResponseDTO;
import com.kaibaltech.pgx.reg.service.service.PgxIPersonService;
import com.kaibaltech.pgx.reg.service.util.ValueMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@Slf4j
@RestController
@RequestMapping("/api/v1/persons")
public class PgxIPersonController {

    private final PgxIPersonService service;
    public static final String SUCCESS = "Success";

    @PostMapping
    public ResponseEntity<APIResponse<PgxIPersonResponseDTO>> createNewPerson(
            @RequestBody @Valid PgxIPersonRequestDTO requestDTO) {
        log.info("ProductController::createNewProduct request body {}", ValueMapper.jsonAsString(requestDTO));

        PgxIPersonResponseDTO pgxIPersonResponseDTO = service.createNewPerson(requestDTO);

        APIResponse<PgxIPersonResponseDTO> responseDTO = APIResponse
                .<PgxIPersonResponseDTO>builder()
                .status(SUCCESS)
                .results(pgxIPersonResponseDTO)
                .build();

        log.info("PgxIPersonController::createNewPerson response {}", ValueMapper.jsonAsString(responseDTO));

        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<APIResponse<List<PgxIPersonResponseDTO>>> fetchAllPersons() {
        List<PgxIPersonResponseDTO> pgxIPersonResponseDTO = service.getAllPersons();
        APIResponse<List<PgxIPersonResponseDTO>> responseDTO = APIResponse
                .<List<PgxIPersonResponseDTO>>builder()
                .status(SUCCESS)
                .results(pgxIPersonResponseDTO)
                .build();

        log.info("PgxIPersonsController::fetchAllPersons response {}", ValueMapper.jsonAsString(responseDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
