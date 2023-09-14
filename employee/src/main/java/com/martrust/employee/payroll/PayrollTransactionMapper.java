package com.martrust.employee.payroll;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * Created by User: Donato Valenti Leandro Amasa
 * Date: 13/09/2023
 * Time: 2:33 pm
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PayrollTransactionMapper {

    @Mapping(source = "employee.id", target = "employeeId")
    PayrollTransactionDto toDto(PayrollTransaction payrollTransaction);
    PayrollTransaction toEntity(PayrollTransactionDto payrollTransactionDto);
}
