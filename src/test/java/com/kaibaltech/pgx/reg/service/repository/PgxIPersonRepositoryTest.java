package com.kaibaltech.pgx.reg.service.repository;

import com.kaibaltech.pgx.reg.service.entity.PgxIPerson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class PgxIPersonRepositoryTest {

    @Autowired
    private PgxIPersonRepository underTest;

    @AfterEach
    public void tearDown() throws Exception {
        underTest.deleteAll();
    }


    @Test
    void itShouldCheckIfPersonEmailExists() {
        // given
        String emailAddress = "kaibaltech@gmail.com";
        PgxIPerson person = PgxIPerson
                .builder()
                .firstName("Kervin")
                .emailAddress(emailAddress)
                .build();
        underTest.save(person);

        // when
        Optional<PgxIPerson> expected = underTest.checkEmailExists(emailAddress);

        // then
        assertThat(expected).isPresent();
    }

}