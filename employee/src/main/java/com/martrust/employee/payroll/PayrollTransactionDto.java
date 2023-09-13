package com.martrust.employee.payroll;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by User: Donato Valenti Leandro Amasa
 * Date: 13/09/2023
 * Time: 11:53 am
 */

@Data
public class PayrollTransactionDto {
    private Long id;
    private LocalDateTime transactionDate;
    private BigDecimal amount;
    private String currency;
    private Long employeeId;
}
