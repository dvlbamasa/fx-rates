package com.martrust.employee.search;

import com.martrust.employee.registration.EmployeeStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Created by User: Donato Valenti Leandro Amasa
 * Date: 13/09/2023
 * Time: 7:12 pm
 */
@Data
@Builder
public class EmployeeSearchCriteria {

    private Optional<EmployeeStatus> employeeStatus;
    private Optional<BigDecimal> minAmount;
    private Optional<BigDecimal> maxAmount;
}
