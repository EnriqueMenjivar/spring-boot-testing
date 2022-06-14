package com.testing.springboot.repository;

import com.testing.springboot.model.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    //JUnit test for save employee operation
    @DisplayName("JUnit test for save employee operation")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {

        //given
        Employee employee = Employee.builder()
                .firstName("Ramesh")
                .lastName("Ramesh")
                .email("ramesh@gmail.com")
                .build();

        //when
        Employee savedEmployee = employeeRepository.save(employee);

        //then
        Assertions.assertThat(savedEmployee).isNotNull();
        Assertions.assertThat(savedEmployee.getId()).isPositive();
    }

    //JUnit test for get all employees operation
    @DisplayName("JUnit test for get all employees operation")
    @Test
    public void givenEmployeesList_whenFindAll_thenEmployeesList() {

        //given - condition or setup
        Employee employee1 = Employee.builder()
                .firstName("Ramesh")
                .lastName("Ramesh")
                .email("ramesh@gmail.com")
                .build();

        Employee employee2 = Employee.builder()
                .firstName("John")
                .lastName("John")
                .email("john@gmail.com")
                .build();


        employeeRepository.save(employee1);
        employeeRepository.save(employee2);

        //when - action or behaviour that are going to test
        List<Employee> employees = employeeRepository.findAll();

        //then - verify the output
        Assertions.assertThat(employees).isNotNull();
        Assertions.assertThat(employees.size()).isEqualTo(2);
    }

    //JUnit test for get employee by id operation
    @DisplayName("JUnit test for get employee by id operation")
    @Test
    public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject() {

        //given - condition or setup
        Employee employee = Employee.builder()
                .firstName("John")
                .lastName("John")
                .email("john@gmail.com")
                .build();

        employeeRepository.save(employee);

        //when - action or behaviour that are going to test
        Employee employeeDB = employeeRepository.findById(employee.getId()).get();

        //then - verify the output
        Assertions.assertThat(employeeDB).isNotNull();
    }

    //JUnit test for get employee by email operation
    @DisplayName("JUnit test for get employee by email operation")
    @Test
    public void givenEmployeeEmail_whenFindByEmail_thenReturnEmployeeObject() {

        //given - condition or setup
        Employee employee = Employee.builder()
                .firstName("John")
                .lastName("John")
                .email("john@gmail.com")
                .build();
        employeeRepository.save(employee);

        ///when - action or behaviour that are going to test
        Employee employeeDB = employeeRepository.findByEmail(employee.getEmail()).get();

        //then - verify the output
        Assertions.assertThat(employeeDB).isNotNull();
    }

    //JUnit test for update employee operation
    @DisplayName("JUnit test for update employee operation")
    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee() {

        //given - condition or setup
        Employee employee = Employee.builder()
                .firstName("John")
                .lastName("John")
                .email("john@gmail.com")
                .build();
        employeeRepository.save(employee);

        ///when - action or behaviour that are going to test
        Employee employeeDB = employeeRepository.findById(employee.getId()).get();
        employeeDB.setEmail("user@gmail.com");
        employeeDB.setFirstName("user");

        employeeDB = employeeRepository.save(employeeDB);

        //then - verify the output
        Assertions.assertThat(employeeDB.getEmail()).isEqualTo("user@gmail.com");
        Assertions.assertThat(employeeDB.getFirstName()).isEqualTo("user");
    }

    //JUnit test for delete employee operation
    @DisplayName("JUnit test for delete employee operation")
    @Test
    public void givenEmployeeObject_whenDelete_thenRemoveEmployee() {

        //given - condition or setup
        Employee employee = Employee.builder()
                .firstName("John")
                .lastName("John")
                .email("john@gmail.com")
                .build();
        employeeRepository.save(employee);

        //when - action or behaviour that are going to test
        employeeRepository.delete(employee);
        Optional<Employee> employeeDel = employeeRepository.findById(employee.getId());

        //then - verify the output
        Assertions.assertThat(employeeDel).isEmpty();
    }
}
