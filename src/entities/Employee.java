package entities;

import java.time.LocalDateTime;
import java.util.List;

public class Employee {
	private Integer id;
	private String name;
	private Double salary;
	private LocalDateTime date;
	
	
	public Employee() {
	}
	
	public Employee(Integer id, String name, Double salary, LocalDateTime date) {
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.date = date;
	}
	
	
	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	
	public void percentageToIncrease(Double percentage) {
		this.salary += ((this.salary *percentage)/100);
	}
	
	public static Integer findId(List<Employee> list, int id) {
		Integer result = null;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getId() == id) {
				result = i;
			}
		}
		return result;
	}

}
