package com.martrust.employee;

import com.martrust.employee.project.Project;
import com.martrust.employee.project.ProjectDto;
import com.martrust.employee.project.ProjectRepository;
import com.martrust.employee.registration.Employee;
import com.martrust.employee.registration.EmployeeDto;
import com.martrust.employee.registration.EmployeeMapper;
import com.martrust.employee.registration.EmployeeRegistrationServiceImpl;
import com.martrust.employee.registration.EmployeeRepository;
import com.martrust.employee.registration.EmployeeStatus;
import com.martrust.employee.search.EmployeeSearchCriteria;
import com.martrust.employee.search.EmployeeSearchServiceImpl;
import com.martrust.employee.search.EmployeeSpecifications;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
class EmployeeApplicationTests {

    @InjectMocks
    private EmployeeSearchServiceImpl employeeSearchService;

    @InjectMocks
    private EmployeeRegistrationServiceImpl employeeRegistrationService;

    @Mock
    private ProjectRepository projectRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeeMapper employeeMapper;

    @Test
    public void givenValidRequest_whenRegistered_thenSuccess() {
        Mockito.when(projectRepository.findById(any(Long.class))).thenReturn(generateProjectEntity());
        Mockito.when(employeeMapper.toEntity(any(EmployeeDto.class))).thenReturn(generateEmployee());
        Mockito.when(employeeRepository.save(any(Employee.class))).thenReturn(generateEmployee());
        Mockito.when(employeeMapper.toDto(any(Employee.class))).thenReturn(generateEmployeeDto());
        EmployeeDto request = generateEmployeeRegistrationRequestDto();
        EmployeeDto result = employeeRegistrationService.register(request);
        assertThat(result.getId()).isGreaterThan(0L);
    }

    @Test
    public void givenInvalidProjectDtoInRequest_whenRegistered_throwException() {
        Mockito.when(projectRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(null));
        EmployeeDto request = generateEmployeeRegistrationRequestDto();
        assertThrows(RuntimeException.class, () -> employeeRegistrationService.register(request));
    }

    @Test
    public void givenValidCriteria_whenSearched_returnSuccess() {
        EmployeeSearchCriteria searchCriteria = criteriaBuild(EmployeeStatus.CONSULTANT, "Martrust");
        Specification<Employee> specification = EmployeeSpecifications.createEmployeeSpecification(searchCriteria);
        Mockito.when(employeeRepository.findAll(Specification.anyOf(specification))).thenReturn(generateEmployeeList());

        Mockito.when(employeeMapper.toDto(any(Employee.class))).thenReturn(generateEmployeeDto());
        List<EmployeeDto> employees = employeeSearchService.search(searchCriteria);

        assertThat(employees.size()).isEqualTo(0);
    }

    private EmployeeSearchCriteria criteriaBuild(EmployeeStatus status, String projectName) {
        return EmployeeSearchCriteria.builder()
                .employeeStatus(Optional.ofNullable(status))
                .projectName(Optional.ofNullable(projectName))
                .build();
    }

    private List<Employee> generateEmployeeList() {
        List<Employee> employeeList = Lists.newArrayList();
        employeeList.add(generateEmployee());
        return employeeList;
    }

    private Optional<Project> generateProjectEntity() {
        Project project = new Project();
        project.setId(1L);
        project.setClientName("Marcura");
        project.setName("Martrust");
        return Optional.of(project);
    }

    private Optional<Project> generateWrongProjectEntity() {
        Project project = new Project();
        project.setId(999L);
        project.setClientName("Marcura");
        project.setName("Martrust");
        return Optional.of(project);
    }

    private EmployeeDto generateEmployeeRegistrationRequestDto() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName("Employee1");
        employeeDto.setLastName("Last Name");
        employeeDto.setStatus(EmployeeStatus.CONSULTANT);
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(1L);
        projectDto.setClientName("Marcura");
        projectDto.setName("Martrust");
        employeeDto.setProject(projectDto);
        return employeeDto;
    }

    private Employee generateEmployee() {
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("Employee1");
        employee.setLastName("Last Name");
        employee.setStatus(EmployeeStatus.CONSULTANT);
        Project project = new Project();
        project.setId(1L);
        project.setClientName("Marcura");
        project.setName("Martrust");
        employee.setProject(project);
        return employee;
    }

    private EmployeeDto generateEmployeeDto() {
        EmployeeDto employee = new EmployeeDto();
        employee.setId(1L);
        employee.setFirstName("Employee1");
        employee.setLastName("Last Name");
        employee.setStatus(EmployeeStatus.CONSULTANT);
        ProjectDto project = new ProjectDto();
        project.setId(1L);
        project.setClientName("Marcura");
        project.setName("Martrust");
        employee.setProject(project);
        return employee;
    }

    @Test
    void contextLoads() {
    }

}
