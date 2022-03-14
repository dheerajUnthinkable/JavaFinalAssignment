package com.harbilas.JavaAssignment;

import com.harbilas.JavaAssignment.Entity.Department;
import com.harbilas.JavaAssignment.Entity.Employee;
import com.harbilas.JavaAssignment.Repsitory.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@SpringBootApplication
@EnableSwagger2
public class JavaAssignmentApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(JavaAssignmentApplication.class, args);
	}

	@Autowired
	EmployeeRepository employeeRepository;


	@Override
	public void run(String... args) throws Exception {
		Employee emp1 = new Employee(
				"Harbilas Singh",
				"9780612025",
				LocalDate.of(2000, Month.JANUARY, 14),
				"singhharbilas2000@gmail.com"
		);
		Employee emp2 = new Employee(
				"Vatsal Aggarwal",
				"7888486819",
				LocalDate.of(1999, Month.DECEMBER, 11),
				"aggarwal.vatsal5@gmail.com"
		);

		Department dept1 = new Department("IT", "SANJEEV KAPOOR");
		Department dept2 = new Department("HR", "KIRTI");
		Department dept3 = new Department("SALES", "ISHIKA GUPTA");

		emp1.getDepartments().add(dept1);
		emp1.getDepartments().add(dept2);

		emp2.getDepartments().add(dept2);
		emp2.getDepartments().add(dept3);

		dept1.getEmployees().add(emp1);

		dept2.getEmployees().add(emp1);
		dept2.getEmployees().add(emp2);

		dept3.getEmployees().add(emp2);

		employeeRepository.saveAll(List.of(emp1, emp2));
	}
}
