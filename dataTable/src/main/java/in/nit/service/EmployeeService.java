package in.nit.service;

import java.util.List;
import java.util.Optional;

import in.nit.model.Employee;

public interface EmployeeService {
	
	public List<Employee> getAllEmployees();
//	public Employee getEmployeeById(long id);

	//Optional<Employee> EmployeeById(long id);

	Optional<Employee> getEmployeeById(long id);
	void deleteShipmentType(Long id);
	void updateShipmentType(Employee emp);
}