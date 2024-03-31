package com.kaibaltech.pgx.reg.service.util;

import com.kaibaltech.pgx.reg.service.exception.JsonProcessingRuntimeException;
import org.junit.jupiter.api.Test;
import com.kaibaltech.pgx.reg.service.dto.PgxIPersonResponseDTO;

import static org.junit.jupiter.api.Assertions.*;

class JsonUtilTest {

    @Test
    void toJson() {
        PgxIPersonResponseDTO person = new PgxIPersonResponseDTO();
        person.setFirstName("John");
        person.setLastName("Doe");
        String expectedJson = "{\"firstName\":\"John\",\"lastName\":\"Doe\"}";
        String actualJson = JsonUtil.toJson(person);

        assertEquals(expectedJson, actualJson);
    }

    @Test
    void toJson_throwsException() {
        assertThrows(JsonProcessingRuntimeException.class, () -> JsonUtil.toJson(new NonSerializableObject()));
    }

    static class NonSerializableObject {}
}