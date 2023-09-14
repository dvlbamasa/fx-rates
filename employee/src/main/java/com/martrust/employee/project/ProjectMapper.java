package com.martrust.employee.project;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * Created by User: Donato Valenti Leandro Amasa
 * Date: 13/09/2023
 * Time: 3:52 pm
 */

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectMapper {

    ProjectDto toDto(Project project);
    Project toEnity(ProjectDto projectDto);
}
