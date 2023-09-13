package com.martrust.employee.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by User: Donato Valenti Leandro Amasa
 * Date: 12/09/2023
 * Time: 6:10 pm
 */

@RestController
@RequestMapping("/api/employee")
@AllArgsConstructor
public class EmployeeRegistrationController {

    private final EmployeeRegistrationService employeeRegistrationService;

    @PostMapping
    public EmployeeDto register(@RequestBody EmployeeDto employeeDto) {
        return employeeRegistrationService.register(employeeDto);
    }
}
