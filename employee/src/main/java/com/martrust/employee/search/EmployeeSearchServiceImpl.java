package com.martrust.employee.search;

import com.martrust.employee.registration.Employee;
import com.martrust.employee.registration.EmployeeDto;
import com.martrust.employee.registration.EmployeeMapper;
import com.martrust.employee.registration.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by User: Donato Valenti Leandro Amasa
 * Date: 14/09/2023
 * Time: 11:04 am
 */
@Service
@RequiredArgsConstructor
public class EmployeeSearchServiceImpl implements EmployeeSearchService{

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeSearchServiceImpl.class);
    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDto> search(EmployeeSearchCriteria criteria) {
        Specification<Employee> employeeSpecification = EmployeeSpecifications.createEmployeeSpecification(criteria);
        List<Employee> employees = repository.findAll(employeeSpecification);
        return employees.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
