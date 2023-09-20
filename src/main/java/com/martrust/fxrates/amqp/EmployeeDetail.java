package com.martrust.fxrates.amqp;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

/**
 * Created by IntelliJ IDEA.
 * User: d.amasa
 * Date: 20/09/2023
 * Time: 1:04 pm
 */

@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = EmployeeDetail.class)
public class EmployeeDetail {
    private String employeeId;
    private String employeeNickName;
}
