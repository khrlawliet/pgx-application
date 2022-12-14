package com.kaibaltech.pgx.reg.service.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PgxIPersonPhoneNumbers {
    private String countryCode;
    private String phoneNumber;
}
