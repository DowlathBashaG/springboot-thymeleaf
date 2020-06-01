package io.dowlath.thymeleafcrud.repository;

import io.dowlath.thymeleafcrud.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author Dowlath
 * @create 6/1/2020 2:06 AM
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
