package com.martrust.employee.search;

import com.martrust.employee.registration.EmployeeDto;

import java.util.List;

/**
 * Created by User: Donato Valenti Leandro Amasa
 * Date: 14/09/2023
 * Time: 11:04 am
 */
public interface EmployeeSearchService {

    List<EmployeeDto> search(EmployeeSearchCriteria criteria);
}
