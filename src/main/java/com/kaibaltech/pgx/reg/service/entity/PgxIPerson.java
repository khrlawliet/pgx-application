package com.kaibaltech.pgx.reg.service.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@Document(collection = "pgx_i_persons")
public class PgxIPerson {

    @MongoId
    private String id;

    @Field(name = "firstname")
    private String firstName;

    @Field(name = "lastname")
    private String lastName;

    @Field(name = "middlename")
    private String middleName;

    @Field(name = "nickName")
    private String nickName;

    @Field(name = "email_add")
    @Indexed(unique = true)
    private String emailAddress;

    private PgxIPersonGender pgxIPersonGender;

    @Field(name = "phoneNumbers")
    private List<PgxIPersonPhoneNumbers> phoneNumbers;

    @Field(name = "is_active")
    private Boolean isActive = true;

    @Field(name = "is_announcer")
    private Boolean isAnnouncer;

    @Field(name = "is_admin")
    private Boolean isAdmin;

    @Field(name = "date_created")
    private LocalDate dateCreated;

    @Field(name = "date_updated")
    private LocalDate dateUpdated;

    private String created;

    private String updated;







}
