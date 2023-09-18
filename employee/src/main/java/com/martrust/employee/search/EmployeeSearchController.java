package com.martrust.employee.search;

import com.martrust.employee.registration.EmployeeDto;
import com.martrust.employee.registration.EmployeeStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Created by User: Donato Valenti Leandro Amasa
 * Date: 14/09/2023
 * Time: 11:16 am
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/employee")
public class EmployeeSearchController {

    private final EmployeeSearchService searchService;

    @GetMapping
    public List<EmployeeDto> search(@RequestParam(required = false) Optional<String> projectName,
                                    @RequestParam(required = false) Optional<EmployeeStatus> status) {
        EmployeeSearchCriteria searchCriteria = EmployeeSearchCriteria.builder()
                .projectName(projectName)
                .employeeStatus(status)
                .build();
        return searchService.search(searchCriteria);
    }
}
