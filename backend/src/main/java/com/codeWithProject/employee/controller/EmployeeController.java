package com.codeWithProject.employee.controller;

import com.codeWithProject.employee.entity.Employee;
import com.codeWithProject.employee.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.Lombok;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/api")
@RequestMapping("/employees")
//@RequiredArgsConstructor
@CrossOrigin("*")
public class EmployeeController {

    private final EmployeeService employeeService;
    //constructor
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    //get all employees
    @GetMapping
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }
    //get employee by id
//    @GetMapping("/{id}")
//    public Optional<Employee> getEmployeeById(@PathVariable Long id){
//        return employeeService.getEmployeeById(id);
//    }
    //create a new employee
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee){
        return employeeService.saveEmployee(employee);
    }
    //delete an employee
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id){
        try{
            employeeService.deleteEmployee(id);
            return new ResponseEntity<>("Employee with ID " + id + " deleted successfully", HttpStatus.OK);
        }catch (EntityNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeByID(@PathVariable Long  id){
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employee);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id,@RequestBody Employee employee){
        Employee  updatedEmployee = employeeService.updateEmployee(id, employee);

        if (updatedEmployee == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.ok(updatedEmployee);
    }
//    @DeleteMapping("/{id}")
//    public void deleteEmployee(@PathVariable Long id){
//        employeeService.deleteEmployee(id);
//    }
//    @PostMapping("/employee")
//    public Employee postEmployee(@RequestBody Employee employee){
//        return employeeService.postEmployee(employee);
//    }
}
