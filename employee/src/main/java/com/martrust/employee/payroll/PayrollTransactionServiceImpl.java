package com.martrust.employee.payroll;

import com.martrust.employee.registration.Employee;
import com.martrust.employee.registration.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


/**
 * Created by User: Donato Valenti Leandro Amasa
 * Date: 13/09/2023
 * Time: 4:10 pm
 */

@Service
@RequiredArgsConstructor
public class PayrollTransactionServiceImpl implements PayrollTransactionService{

    private final EmployeeRepository employeeRepository;
    private final PayrollTransactionRepository payrollTransactionRepository;
    private final PayrollTransactionMapper payrollTransactionMapper;
    @Override
    public PayrollTransactionDto pay(PayrollTransactionDto payrollTransactionDto) {
        Optional<Employee> employee = employeeRepository.findById(payrollTransactionDto.getEmployeeId());
        if (employee.isPresent()) {
            PayrollTransaction payrollTransaction = payrollTransactionMapper.toEntity(payrollTransactionDto);
            payrollTransaction.setTransactionDate(LocalDateTime.now());
            payrollTransaction.setEmployee(employee.get());
            return payrollTransactionMapper.toDto(payrollTransactionRepository.save(payrollTransaction));
        } else {
            throw new RuntimeException("Employee Does not exist");
        }
    }
}
