package SkyProSpringStreamAPIOptionalDemo.service.impl;

import SkyProSpringStreamAPIOptionalDemo.model.Employee;
import SkyProSpringStreamAPIOptionalDemo.service.DepartmentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


class DeportmentServiceImpTest {
    @Mock
    private EmployeeServiceImpl employeeService;
    @InjectMocks
    private DepartmentService departmentService;

    @Test
    void departmentServiceImpl() {
    }

    @Test
    void findEmployeeMinSalaryByDepartment() {
        List<Employee> employees = Arrays.asList(new Employee("Vova", "Losev", 10000, 1),
                new Employee("Anna", "Nikulina", 8000, 1),
                new Employee("Rayal","Agadjanov",9000,2));
        Mockito.when(employeeService.findAllEmployees()).thenReturn(employees);
        Optional<Employee> result = departmentService.findEmployeeMinSalaryByDepartment(1);

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("Anna", result.get().getFirstName());
    }

    @Test
    void findEmployeeMaxSalaryByDepartment() {
        List<Employee> employees = Arrays.asList(new Employee("Vova", "Losev", 10000, 1),
                new Employee("Anna", "Nikulina", 8000, 1),
                new Employee("Rayal","Agadjanov",9000,2));
        Mockito.when(employeeService.findAllEmployees()).thenReturn(employees);
        Optional<Employee> result = departmentService.findEmployeeMaxSalaryByDepartment(1);

        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("Jane", result.get().getFirstName());
    }


    @Test
    void allEmployeesByDepartment() {
        List<Employee> employees = Arrays.asList(new Employee("Vova", "Losev", 10000, 1),
                new Employee("Anna", "Nikulina", 8000, 1),
                new Employee("Rayal","Agadjanov",9000,2));
        Mockito.when(employeeService.findAllEmployees()).thenReturn(employees);
        List<Employee> result = departmentService.allEmployeesByDepartment(1);

        Assertions.assertEquals(2, result.size());
    }


    @Test
    void findAll() {List<Employee> employees = Arrays.asList(new Employee("Vova", "Losev", 10000, 1),
            new Employee("Anna", "Nikulina", 8000, 1),
            new Employee("Rayal","Agadjanov",9000,2));
        Mockito.when(employeeService.findAllEmployees()).thenReturn(employees);

        Map<Integer, List<Employee>> result = departmentService.findAll();

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(2, result.get(1).size());
        Assertions.assertEquals(1, result.get(2).size());
    }

}