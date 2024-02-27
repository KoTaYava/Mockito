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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

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
        int beforeCount = employeeService.findAllEmployees().size();
        String firstName = "Vladimir";
        String lastName = "Losev";
        int salary = 8000;
        int department = 1;
        when(employeeMap.containsKey(firstName + " " + lastName)).thenReturn(false);
        when(employeeMap.put(anyString(), any(Employee.class))).thenReturn(null);


        Employee result = employeeService.addEmployee(firstName, lastName, salary, department);


        assertEquals(firstName, result.getFirstName());
        assertEquals(lastName, result.getLastName());
        assertEquals(salary, result.getSalary());
        assertEquals(department, result.getDepartment());


        when(employeeMap.containsKey(firstName + " " + lastName)).thenReturn(true);
        Employee existingEmployeeResult = employeeService.addEmployee(firstName, lastName, salary, department);
        assertNull(existingEmployeeResult); // Ensure that the existing employee was not added again
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