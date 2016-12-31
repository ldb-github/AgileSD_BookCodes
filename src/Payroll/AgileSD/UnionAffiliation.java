package Payroll.AgileSD;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UnionAffiliation implements Affiliation {

	private int memberId;
	private double dues;
	private List<ServiceCharge> serviceCharges;
	
	public UnionAffiliation(int memberId, double dues) {
		this.memberId = memberId;
		this.dues = dues;
		serviceCharges = new ArrayList<>();
	}

	public ServiceCharge getServiceCharge(Date date) {
		for(ServiceCharge sc : serviceCharges){
			if(sc.getDate().getTime() == date.getTime()){
				return sc;
			}
		}
		return null;
	}

	public void addServiceCharge(ServiceCharge serviceCharge) {
		serviceCharges.add(serviceCharge);
	}

	
	
	
}
