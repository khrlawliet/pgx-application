package com.kaibaltech.pgx.reg.service.dto;


import com.kaibaltech.pgx.reg.service.entity.PgxIPersonGender;
import com.kaibaltech.pgx.reg.service.entity.PgxIPersonPhoneNumbers;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class PgxIPersonRequestDTO {

    @NotBlank(message = "firstname shouldn't be NULL OR EMPTY")
    private String firstName;

    @NotBlank(message = "lastname shouldn't be NULL OR EMPTY")
    private String lastName;

    @NotBlank(message = "middlename shouldn't be NULL OR EMPTY")
    private String middleName;

    private String nickName;
    @NotBlank(message = "email address shouldn't be NULL OR EMPTY")
    private String emailAddress;

    private PgxIPersonGender pgxIPersonGender;

    private List<PgxIPersonPhoneNumbers> phoneNumbers;

    private Boolean isAnnouncer;

    private Boolean isAdmin;

}
