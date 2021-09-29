package in.nit.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nit.model.Employee;
import in.nit.repo.EmployeeRepository;
import in.nit.service.EmployeeService;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public Optional<Employee> getEmployeeById(long id) {
		Optional<Employee> opt = employeeRepository.findById(id);
		return opt;
	}

	@Override
	public void updateShipmentType(Employee emp) {
		employeeRepository.save(emp);
	}

	@Override
	public void deleteShipmentType(Long id) {
		employeeRepository.deleteById(id);
	}


	
}
