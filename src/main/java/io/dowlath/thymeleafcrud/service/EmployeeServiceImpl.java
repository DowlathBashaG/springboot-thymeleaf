package io.dowlath.thymeleafcrud.service;

import io.dowlath.thymeleafcrud.model.Employee;
import io.dowlath.thymeleafcrud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author Dowlath
 * @create 6/1/2020 2:09 AM
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public void saveEmployee(Employee employee) {
        this.employeeRepository.save(employee);
    }

    @Override
    public Employee getEmployeeById(long id) {
        Optional<Employee> employeeOption = employeeRepository.findById(id);
        Employee employee = null;
        if (employeeOption.isPresent()) {
            employee = employeeOption.get();
        } else {
            throw new RuntimeException("Employee not found for id : " + id);
        }
        return employee;

    }

    @Override
    public void deleteEmployeeById(long id) {
        this.employeeRepository.deleteById(id);
    }

    /*
    @Override
    public Page<Employee> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo -1 ,pageSize);
        return employeeRepository.findAll(pageable);
    }
   */

    @Override
    public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                    Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize,sort);
        return employeeRepository.findAll(pageable);


    }


}
