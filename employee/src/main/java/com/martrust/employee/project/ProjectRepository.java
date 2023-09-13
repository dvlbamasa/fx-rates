package com.martrust.employee.project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by User: Donato Valenti Leandro Amasa
 * Date: 13/09/2023
 * Time: 5:37 pm
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
