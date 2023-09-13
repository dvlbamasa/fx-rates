package com.martrust.employee.payroll;

import com.martrust.employee.BaseModel;
import com.martrust.employee.registration.Employee;
import com.martrust.employee.registration.EmployeeDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by User: Donato Valenti Leandro Amasa
 * Date: 13/09/2023
 * Time: 11:50 am
 */

@Data
@Entity
@Table
@EqualsAndHashCode(callSuper=false)
public class PayrollTransaction extends BaseModel {

    private LocalDateTime transactionDate;
    private BigDecimal amount;
    private String currency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;
}
