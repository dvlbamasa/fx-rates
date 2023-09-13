package com.martrust.employee.payroll;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by User: Donato Valenti Leandro Amasa
 * Date: 13/09/2023
 * Time: 4:27 pm
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payroll")
public class PayrollTransactionController {

    private final PayrollTransactionService payrollTransactionService;

    @PostMapping
    public PayrollTransactionDto pay(@RequestBody PayrollTransactionDto payrollTransactionDto) {
        return payrollTransactionService.pay(payrollTransactionDto);
    }
}
