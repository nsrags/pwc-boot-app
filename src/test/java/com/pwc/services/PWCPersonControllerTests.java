package com.pwc.services;

import org.instancio.Instancio;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import com.pwc.services.domain.PWCPerson;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PWCPersonControllerTests {

    private static final String API_PATH = "/api/v1";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @Order(1)
    void add() {
        PWCPerson obj = restTemplate.postForObject(API_PATH, Instancio.create(PWCPerson.class), PWCPerson.class);
        assertNotNull(obj);
        assertEquals(1, obj.getId());
    }

    @Test
    @Order(2)
    void findAll() {
        PWCPerson[] objs = restTemplate.getForObject(API_PATH, PWCPerson[].class);
        assertTrue(objs.length > 0);
    }

    @Test
    @Order(2)
    void findById() {
        PWCPerson obj = restTemplate.getForObject(API_PATH + "/{id}", PWCPerson.class, 1L);
        assertNotNull(obj);
        assertEquals(1, obj.getId());
    }

    @Test
    @Order(3)
    void delete() {
        restTemplate.delete(API_PATH + "/{id}", 1L);
        PWCPerson obj = restTemplate.getForObject(API_PATH + "/{id}", PWCPerson.class, 1L);
        assertNull(obj.getId());
    }

}