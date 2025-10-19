import java.util.ArrayList;

public class AdminRole {
	private EmployeeUserDatabase database;

	// Constructor for initializing 
	public AdminRole() {
		this.database = new EmployeeUserDatabase("Employees.txt");
	}

	// Method(1): adding a new employee
	public void addEmployee(String employeeId, String name, String email, String address, String phoneNumber) {
		EmployeeUser newEmployee = new EmployeeUser(employeeId, name, email, address, phoneNumber);
		database.insertRecord(newEmployee);
		database.saveToFile();
	}

	// Method(2): getting a list of all employees as an array
	public EmployeeUser[] getListOfEmployees() {
	ArrayList<EmployeeUser> employeeList = database.getAllEmployees();
	return employeeList.toArray(new EmployeeUser[0]);
	}

	// Method(3): remove an employee by Id
	public void removeEmployee(String key) {
		database.deleteRecord(key);
		database.saveToFile();
	}

	// Method(4):save any unsaved data
	public void logout() {
		database.saveToFile();
	}
}
