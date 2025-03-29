package com.prod_ready_features.clients.impl;

import com.prod_ready_features.advice.ApiResponse;
import com.prod_ready_features.clients.EmployeeClient;
import com.prod_ready_features.dto.EmployeeDTO;
import com.prod_ready_features.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeClientImpl implements EmployeeClient {

    private final RestClient restClient;
    Logger log = LoggerFactory.getLogger(EmployeeClientImpl.class);

    @Override
    public List<EmployeeDTO> getAllEmployees() {

        log.error("error log");
        log.warn("warn log");
        log.info("info log");
        log.trace("trace log");

        try {
            ApiResponse<List<EmployeeDTO>> employeeDTOList = restClient.get()
                    .uri("employees")
                    .retrieve()
                    .body(new ParameterizedTypeReference<ApiResponse<List<EmployeeDTO>>>() {});

            return employeeDTOList.getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO getEmployeeById(Long employeeId) {
        try {
            ApiResponse<EmployeeDTO> employeeResponse = restClient.get()
                    .uri("employees/{employeeId}", employeeId)
                    .retrieve()
                    .body(new ParameterizedTypeReference<ApiResponse<EmployeeDTO>>() {});

            return employeeResponse.getData();
        } catch (HttpClientErrorException.NotFound e) {
            throw new ResourceNotFoundException("Employee not found with ID: " + employeeId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        try {
            ApiResponse<EmployeeDTO> employeeDTOApiResponse = restClient.post()
                    .uri("employees")
                    .body(employeeDTO)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                        String errorResponse = res.getBody() != null ? new String(res.getBody().readAllBytes()) : "Unknown Error";
                        System.out.println("Error Occurred: " + errorResponse);
                        throw new ResourceNotFoundException("Could not create the employee");
                    })
                    .onStatus(HttpStatusCode::is5xxServerError, (req, res) -> {
                        throw new RuntimeException("Server Error Occurred");
                    })
                    .body(new ParameterizedTypeReference<ApiResponse<EmployeeDTO>>() {});

            return employeeDTOApiResponse.getData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
