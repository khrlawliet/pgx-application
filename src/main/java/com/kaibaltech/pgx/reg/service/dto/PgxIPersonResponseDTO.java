package com.kaibaltech.pgx.reg.service.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PgxIPersonResponseDTO implements Serializable {
    private String firstName;
    private String lastName;
    private String middleName;
    private String nickName;

}
