package com.martrust.employee.registration;

import com.martrust.employee.project.Project;
import com.martrust.employee.project.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by User: Donato Valenti Leandro Amasa
 * Date: 12/09/2023
 * Time: 5:31 pm
 */

@Service
@RequiredArgsConstructor
public class EmployeeRegistrationServiceImpl implements EmployeeRegistrationService{

    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository repository;
    private final ProjectRepository projectRepository;

    @Override
    @Transactional
    public EmployeeDto register(EmployeeDto employeeDto) {
        Optional<Project> project = projectRepository.findById(employeeDto.getProject().getId());
        if (project.isPresent()) {
            Employee employee = employeeMapper.toEntity(employeeDto);
            employee.setProject(project.get());
            return employeeMapper.toDto(repository.save(employee));
        } else {
            throw new RuntimeException("Project does not exist!");
        }
    }
}
