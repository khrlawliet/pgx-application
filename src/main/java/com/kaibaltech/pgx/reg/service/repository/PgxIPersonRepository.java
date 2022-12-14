package com.kaibaltech.pgx.reg.service.repository;

import com.kaibaltech.pgx.reg.service.entity.PgxIPerson;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;


public interface PgxIPersonRepository extends MongoRepository<PgxIPerson, String> {

    @Query("{\"email_add\" : \"?0\"}")
    Optional<PgxIPerson> checkEmailExists(String email);
}
