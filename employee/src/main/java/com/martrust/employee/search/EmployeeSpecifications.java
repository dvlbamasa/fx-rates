package com.martrust.employee.search;

import com.martrust.employee.project.Project;
import com.martrust.employee.registration.Employee;
import com.martrust.employee.registration.EmployeeStatus;
import com.martrust.employee.registration.Employee_;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

/**
 * Created by User: Donato Valenti Leandro Amasa
 * Date: 13/09/2023
 * Time: 7:17 pm
 */
public final class EmployeeSpecifications {

    public static Specification<Employee> createEmployeeSpecification(EmployeeSearchCriteria searchCriteria) {
        return statusEqualTo(searchCriteria.getEmployeeStatus())
                .and(projectNameEqualTo(searchCriteria.getProjectName()));
    }

    public static Specification<Employee> statusEqualTo(Optional<EmployeeStatus> employeeStatus) {
        return ((root, query, criteriaBuilder) ->
                employeeStatus.map(status -> criteriaBuilder.equal(root.get(Employee_.STATUS),
                        status)).orElse(null));
    }

    public static Specification<Employee> projectNameEqualTo(Optional<String> projectName) {
        return ((root, query, criteriaBuilder) -> {
            Join<Employee, Project> payrollEmployee = root.join("project");
            return projectName.map(name ->
                    criteriaBuilder.equal(payrollEmployee.get("name"), name)).orElse(null);

        });
    }
}
