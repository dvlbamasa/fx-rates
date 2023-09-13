package com.martrust.employee.payroll;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by User: Donato Valenti Leandro Amasa
 * Date: 13/09/2023
 * Time: 4:11 pm
 */

@Repository
public interface PayrollTransactionRepository extends JpaRepository<PayrollTransaction, Long> {
}
