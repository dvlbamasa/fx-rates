package com.martrust.employee.registration;

import com.martrust.employee.BaseModel;
import com.martrust.employee.payroll.PayrollTransaction;
import com.martrust.employee.project.Project;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

/**
 * Created by User: Donato Valenti Leandro Amasa
 * Date: 12/09/2023
 * Time: 4:54 pm
 */
@Data
@Entity
@Table
@EqualsAndHashCode(callSuper=false)
public class Employee extends BaseModel {

    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private EmployeeStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "employee")
    private Set<PayrollTransaction> payrollTransactions;
}
