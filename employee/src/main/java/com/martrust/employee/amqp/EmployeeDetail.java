package com.martrust.employee.amqp;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 19/09/2023
 * Time: 4:40 pm
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = EmployeeDetail.class)
public class EmployeeDetail {
    private String employeeId;
    private String employeeNickName;
}
