package com.martrust.employee.search;

import com.martrust.employee.payroll.PayrollTransaction;
import com.martrust.employee.registration.Employee;
import com.martrust.employee.registration.EmployeeStatus;
import com.martrust.employee.registration.Employee_;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Created by User: Donato Valenti Leandro Amasa
 * Date: 13/09/2023
 * Time: 7:17 pm
 */
public final class EmployeeSpecifications {

    public static Specification<Employee> createEmployeeSpecification(EmployeeSearchCriteria searchCriteria) {
        return statusEqualTo(searchCriteria.getEmployeeStatus())
                .and(amountBetween(searchCriteria.getMinAmount(), searchCriteria.getMaxAmount()));
    }

    public static Specification<Employee> statusEqualTo(Optional<EmployeeStatus> employeeStatus) {
        return ((root, query, criteriaBuilder) ->
                employeeStatus.map(status -> criteriaBuilder.equal(root.get(Employee_.STATUS),
                        employeeStatus.get())).orElse(null));
    }

    public static Specification<Employee> amountBetween(Optional<BigDecimal> minAmount,
                                                        Optional<BigDecimal> maxAmount) {
        return ((root, query, criteriaBuilder) -> {
            Join<Employee, PayrollTransaction> payrollEmployee = root.join("payrollTransactions");
            return minAmount.map(min ->
                    maxAmount.map(max -> criteriaBuilder.between(payrollEmployee.get("amount"), min, max))
                    .orElse(null)).orElse(null);
        });
    }
}
