package com.martrust.employee.project;

import com.martrust.employee.payroll.PayrollTransactionMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * Created by User: Donato Valenti Leandro Amasa
 * Date: 13/09/2023
 * Time: 3:52 pm
 */

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectMapper {

    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    ProjectDto toDto(Project project);
    Project toEnity(ProjectDto projectDto);

}
