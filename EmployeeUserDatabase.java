import java.util.ArrayList;

public class EmployeeUserDatabase extends Data {

	public EmployeeUserDatabase(String filename) {
        super(filename); 
    }

	    // Method(2): returns an EmployeeUser object
    @Override
		protected Record createRecordFrom(String line) {
		String[] parts = line.split(",");
        if (parts.length == 5) {
            String employeeId = parts[0].trim();
            String name = parts[1].trim();
            String email = parts[2].trim();
            String address = parts[3].trim();
            String phoneNumber = parts[4].trim();
            return new EmployeeUser(employeeId, name, email, address, phoneNumber);
        } else {
            System.err.println("Invalid");
            return null;
        }
    }
		public ArrayList<EmployeeUser> getAllEmployees() {
    ArrayList<EmployeeUser> employees = new ArrayList<>();
    for (int i = 0; i < this.records.size(); i++) {
        Record rec = this.records.get(i);
        employees.add((EmployeeUser) rec);
    }
    return employees;
}

    public EmployeeUser getEmployee(String key) {
        return (EmployeeUser) getRecord(key);
    }
}
