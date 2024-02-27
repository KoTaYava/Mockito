package SkyProSpringStreamAPIOptionalDemo.service.impl;

import SkyProSpringStreamAPIOptionalDemo.exception.EmployeeNotFoundException;
import SkyProSpringStreamAPIOptionalDemo.model.Employee;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {
    @Mock
    private Map<String, Employee> employeeMap;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void addEmployee() {
        String firstName = "Vladimir";
        String lastName = "Losev";
        int salary = 8000;
        int department = 1;
        Mockito.when(employeeMap.containsKey(Mockito.anyString())).thenReturn(false);
        Mockito.when(employeeMap.put(Mockito.anyString(), Mockito.any(Employee.class))).thenReturn(null);

        Employee result = employeeService.addEmployee(firstName, lastName, salary, department);

        Assertions.assertEquals(firstName, result.getFirstName());
        Assertions.assertEquals(lastName, result.getLastName());
        Assertions.assertEquals(salary, result.getSalary());
        Assertions.assertEquals(department, result.getDepartment());
    }



    @Test
    void removeEmployee() {
        String firstName = "Vladimir";
        String lastName = "Losev";
        Mockito.when(employeeMap.remove(Mockito.anyString())).thenReturn(new Employee(firstName, lastName));

        Employee result = employeeService.removeEmployee(firstName, lastName);

        Assertions.assertEquals(firstName, result.getFirstName());
        Assertions.assertEquals(lastName, result.getLastName());

    }

    @Test
    void findEmployee() {
        String firstName = "Vladimir";
        String lastName = "Losev";
        int salary = 8000;
        int department = 1;
        Mockito.when(employeeMap.containsKey(Mockito.anyString())).thenReturn(true);
        Mockito.when(employeeMap.get(Mockito.anyString())).thenReturn(new Employee(firstName, lastName, salary, department));

        Employee result = employeeService.findEmployee(firstName, lastName);

        Assertions.assertEquals(firstName, result.getFirstName());
        Assertions.assertEquals(lastName, result.getLastName());
        Assertions.assertEquals(salary, result.getSalary());
        Assertions.assertEquals(department, result.getDepartment());
    }

    @Test
    public void testFindEmployee_NotFound() {
        String firstName = "Vladimir";
        String lastName = "Losev";

        Mockito.when(employeeMap.containsKey(Mockito.anyString())).thenReturn(false);

        employeeService.findEmployee(firstName, lastName);
    }

}