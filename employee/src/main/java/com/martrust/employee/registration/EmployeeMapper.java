package com.martrust.employee.registration;

import com.martrust.employee.payroll.PayrollTransactionMapper;
import com.martrust.employee.project.ProjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * Created by User: Donato Valenti Leandro Amasa
 * Date: 12/09/2023
 * Time: 5:52 pm
 */

@Mapper(componentModel = "spring", uses = {PayrollTransactionMapper.class, ProjectMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mappings({
            @Mapping(target = "projectId", source = "project.id")
    })
    EmployeeDto toDto(Employee employee);

    Employee toEntity(EmployeeDto employeeDto);
}
