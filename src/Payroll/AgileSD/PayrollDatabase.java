package Payroll.AgileSD;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PayrollDatabase {

	private static Map<Integer, Employee> employees = new HashMap<>();
	private static Map<Integer, Employee> members = new HashMap<>();
	
	public static Employee getEmployee(int empId){
		return employees.get(empId);
	}
	
	public static void addEmployee(int empId, Employee e){
		employees.put(empId, e);
	}

	public static void deleteEmployee(int empId) {
		employees.remove(empId);
	}

	public static void addUnionMember(int memberId, Employee e) {
		members.put(memberId, e);
	}

	public static Employee getUnionMember(int memberId) {
		return members.get(memberId);
	}

	public static void RemoveUnionMember(int memberId) {
		members.remove(memberId);
	}

	public static void getAllEmployyIds(List<Integer> empIds) {
		Set<Integer> keys = employees.keySet();
		for(int empId : keys){
			empIds.add(empId);
		}
	}
}
