package com.company.controller;

import com.company.dto.EmployeeDto;
import com.company.entity.Employee;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Artur Tomeyan
 * @date 01/09/2022
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class EmployeeControllerTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    private String getUrl() {
        return "http://localhost:" + port;
    }

    @Test
    void getAllEmployees() {
        HttpHeaders httpHeaders = new HttpHeaders();

        HttpEntity<String> httpEntity = new HttpEntity<>(null, httpHeaders);

        ResponseEntity<String> response = testRestTemplate.exchange(getUrl() + "/employees", HttpMethod.GET,
                httpEntity, String.class);
        assertNotNull(response.getBody());
    }

    @Test
    void addEmployee() {
        Employee employee = new Employee();

        employee.setFirstName("Armen");
        employee.setLastName("");
        employee.setDateOfBirth(LocalDate.of(1992, 5, 2));
        employee.setEmail("arm.y@mail.com");
        employee.setGender("M");
        employee.setActive(true);
        employee.setPosition(employee.getPosition());
        employee.setDepartment(employee.getDepartment());

        ResponseEntity<EmployeeDto> postResponse = testRestTemplate.postForEntity(getUrl() + "/employees", employee, EmployeeDto.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }
}