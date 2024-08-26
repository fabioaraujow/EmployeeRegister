package application;

import java.util.Scanner;
import entities.Employee;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;

public class Program {

	public static void main(String[] args) {
		
		LocalDateTime date = LocalDateTime.now();
		Instant gmt = Instant.now();
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		DateTimeFormatter fmt2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").withZone(ZoneId.of("GMT"));
		Scanner sc = new Scanner(System.in);
		List<Employee> empData = new ArrayList<>();
		
		System.out.println("GMT time: " + fmt2.format(gmt));
		System.out.println("Local Time: " + fmt.format(date));
		
		System.out.println("Enter employee data to register:");
		
		char resp;
		int p = 1;
		Integer id;
		String name;
		Double salary;
		
		do {
			System.out.printf("Employee #%d%n", p);
			System.out.print("Id: ");
			while (true) {
				if (sc.hasNextInt()) {
					id = sc.nextInt();
					if (id > 0) {
						break;
					} else {
						System.out.println("Invalid data. Try again!");
						System.out.print("Id: ");
					}
				} else {
					System.out.println("Invalid data. Try again!");
					System.out.print("Id: ");
					sc.nextLine();
				}
			}
			
			while (Employee.findId(empData, id) != null) {
				System.out.println("This ID has already been registered. Try again ");
				System.out.print("Id: ");
				while (true) {
					if (sc.hasNextInt()) {
						id = sc.nextInt();
						if (id > 0) {
							break;
						} else {
							System.out.println("Invalid data. Try again!");
							System.out.print("Id: ");
							sc.next();
						}
					} else {
						System.out.println("Invalid data. Try again!");
						System.out.print("Id: ");
						sc.next();
					}
				}
			}
			sc.nextLine();
			
			System.out.print("Name: ");
			while (true) {
				name = sc.nextLine();
				if (name.matches("[a-zA-ZÀ-ÿ ]+")) {
					break;
				} else {
					System.out.println("Invalid data. Try again!");
					System.out.print("Name: ");
				}
			}
			
			System.out.print("Salary: ");
			while (true) {
				if (sc.hasNextDouble()) {
					salary = sc.nextDouble();
					if (salary > 0) {
						break;
					} else {
						System.out.println("Invalid data. Try again!");
						System.out.print("Salary: ");
						sc.nextLine();
					}
				} else {
					System.out.println("Invalid data. Try again!");
					System.out.print("Salary: ");
					sc.next();
				}
			}
			sc.nextLine();
			
			empData.add(new Employee(id, name, salary, date));
			
			p++;
			
			System.out.print("To CONTINUE registering type (C) or (S) to STOP: ");
			resp = sc.next().charAt(0);
			while (true) {
	            if (resp == 'C' || resp == 'c' || resp == 'S' || resp == 's') {
	            	sc.nextLine();
	                break;
	            } else {
	                System.out.println("Invalid data. Try again!");
	                sc.nextLine();
	                System.out.print("type (C) to continue ou (S) to stop: ");
	    			resp = sc.next().charAt(0);
	            }
	        }
		}while (resp != 's' && resp != 'S');
		
		System.out.printf("%nEnter the employee id that will have salary increase: ");
		Integer idToInc;
		while (true) {
			if (sc.hasNextInt()) {
				idToInc = sc.nextInt();
				if (idToInc > 0) {
					break;
				} else {
					System.out.println("Invalid data. Try again!");
					System.out.print("Id: ");
					sc.nextLine();
				}
			} else {
				System.out.println("Invalid data. Try again!");
				System.out.print("Id: ");
				sc.next();
			}
		}
		
		Double percentage;
		if (Employee.findId(empData, idToInc) != null) {
			int pId = Employee.findId(empData, idToInc);
			System.out.print("Enter the percentage to increase: ");
			while (true) {
				if (sc.hasNextDouble()) {
					percentage = sc.nextDouble();
					if (percentage >= 0) {
						break;
					} else {
						System.out.println("Invalid data. Try again!");
						System.out.print("Enter the percentage to increase: ");
						sc.next();
					}
				} else {
					System.out.println("Invalid data. Try again!");
					System.out.print("Enter the percentage to increase: ");
					sc.next();
				}
			}
			empData.get(pId).percentageToIncrease(percentage);
		} else {
			System.out.println("This id does not exist!");
		}
		
		System.out.printf("%nList of Employees:");
		for (int i = 0; i < empData.size(); i++) {
			System.out.printf("%n#%d %n", i+1);
			System.out.println("ID: " + empData.get(i).getId());
			System.out.println("Name: " + empData.get(i).getName());
			System.out.println("Salary: " + empData.get(i).getSalary());
			System.out.println("Date of Hire (local): " + empData.get(i).getDate().format(fmt));
		}
		
		sc.close();
	}
}