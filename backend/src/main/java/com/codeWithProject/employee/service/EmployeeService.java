package com.codeWithProject.employee.service;

import com.codeWithProject.employee.entity.Employee;
import com.codeWithProject.employee.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
//@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    //constructor
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }
    //crud methods
    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }
//    public Optional<Employee> getEmployeeById(Long id){
//        return employeeRepository.findById(id);
//    }
    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }
    public void deleteEmployee(Long id){
        if(!employeeRepository.existsById(id)){
            throw new EntityNotFoundException("Employee with ID " + id + " not found");
        }
        employeeRepository.deleteById(id);
    }
    public Employee getEmployeeById(Long id){
        return employeeRepository.findById(id).orElse(null);
    }
    public Employee updateEmployee(Long id , Employee employee){
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()){
            Employee existingEmployee = optionalEmployee.get();

            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setName(employee.getName());
            existingEmployee.setPhone(employee.getPhone());
            existingEmployee.setDepartment(employee.getDepartment());

            return employeeRepository.save(existingEmployee);
        }
        return null;
    }
//    public Employee postEmployee(Employee employee){
//        return employeeRepository.save(employee);
//    }
}
