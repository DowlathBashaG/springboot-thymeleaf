package io.dowlath.thymeleafcrud.service;

import io.dowlath.thymeleafcrud.model.Employee;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author Dowlath
 * @create 6/1/2020 2:07 AM
 */

public interface EmployeeService {
    List<Employee> getAllEmployees();
    void saveEmployee(Employee employee);
    Employee getEmployeeById(long id);
    void deleteEmployeeById(long id);
    // Pagination method
    //Page<Employee> findPaginated(int pageNo, int pageSize);
    // Page sorting method
    Page<Employee> findPaginated(int pageNo, int pageSize,String sortField,String sortDirection);


}
