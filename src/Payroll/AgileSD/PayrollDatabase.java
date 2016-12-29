package Payroll.AgileSD;

import java.util.Map;

public class PayrollDatabase {

	private static Map<Integer, Employee> employees;
	
	public static Employee getEmployee(int empId){
		return employees.get(empId);
	}
	
	public static void addEmployee(int empId, Employee e){
		employees.put(empId, e);
	}
}
