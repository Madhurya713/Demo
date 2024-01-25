//package com.example.demo.service;
//
//import com.example.demo.entity.Employee;
//import com.example.demo.repo.EmployeeRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.math.BigDecimal;
//import static org.mockito.BDDMockito.*;
//import static org.assertj.core.api.Assertions.*;
//
//@ExtendWith(MockitoExtension.class)
//public class EmployeeServiceTest {
//
//    @Mock
//    private EmployeeRepository employeeRepository;
//
//    @InjectMocks
//    private EmployeeService employeeService;
//
//    private Employee employee;
//
//    @BeforeEach
//    void setUp() {
//        employee = new Employee();
//         employee.setId(1L);
//         employee.setName("John Doe");
//         employee.setSalary(new BigDecimal(60000));
//    }
//
//    @Test
//    @DisplayName("Add Employee - Employee is Successfully Added")
//    void whenAddEmployee_thenEmployeeShouldBeSavedAndReturned() {
//        given(employeeRepository.saveAndFlush(employee)).willReturn(employee);
//        Employee savedEmployee = employeeService.addEmployee(employee);
//        then(employeeRepository).should().saveAndFlush(employee);
//        assertThat(savedEmployee).isNotNull()
//                                  .isEqualTo(employee);
//    }
//
//    @Test
//    @DisplayName("Delete Employee - Employee is Successfully Deleted")
//    void whenDeleteEmployee_thenEmployeeShouldBeDeleted() {
//        willDoNothing().given(employeeRepository).deleteById(anyLong());
//
//        assertThatCode(() -> employeeService.deleteEmployee(1L))
//            .doesNotThrowAnyException();
//
//        then(employeeRepository).should().deleteById(1L);
//    }
//
//    @Test
//    @DisplayName("Add Employee - Null Employee Throws Exception")
//    void whenAddNullEmployee_thenThrowException() {
//        assertThatIllegalArgumentException()
//            .isThrownBy(() -> employeeService.addEmployee(null));
//    }
//}
