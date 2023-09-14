package com.martrust.employee.registration;

import com.martrust.employee.payroll.PayrollTransactionDto;
import com.martrust.employee.project.ProjectDto;
import lombok.Data;

import java.util.Set;

/**
 * Created by User: Donato Valenti Leandro Amasa
 * Date: 12/09/2023
 * Time: 5:09 pm
 */
@Data
public class EmployeeDto {

    private String firstName;
    private String lastName;
    private EmployeeStatus status;
    private Long id;
    private Set<PayrollTransactionDto> payrollTransactions;
    private ProjectDto project;
}
