package com.martrust.employee.project;

import com.martrust.employee.BaseModel;
import com.martrust.employee.registration.Employee;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

/**
 * Created by User: Donato Valenti Leandro Amasa
 * Date: 13/09/2023
 * Time: 3:47 pm
 */

@Data
@Table
@Entity
@EqualsAndHashCode(callSuper=false)
public class Project extends BaseModel {

    private String name;
    private String clientName;

}
