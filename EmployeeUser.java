public class EmployeeUser implements Record{
	private String employeeId;
	private String Name;
	private String Email;
	private String Address;
	private String PhoneNumber;
	
	// Constructor for initializing 
	public EmployeeUser(String employeeId, String name, String email, String address, String phoneNumber){
	this.employeeId = employeeId;
        this.Name = name;
        this.Email = email;
        this.Address = address;
        this.PhoneNumber = phoneNumber;
	}
	
	// Method(1): returns the data of the employee comma separated
	@Override
	public String lineRepresentation(){
	return employeeId + "," + Name + "," + Email + "," + Address + "," + PhoneNumber;
	}
	
	// Method(2): returns the employee id
	@Override
	 public String getSearchKey() {
        return employeeId;
    }
	 
	// Setter for Id
    public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId; 
	}

   	// Getters & Setters for Name
	public String getName() {
		return Name; 
	}
    public void setName(String name) { 
		this.Name = name; 
	}

		// Getters & Setters for Email
    public String getEmail() {
		return Email; 
	}
    public void setEmail(String email) {
		this.Email = email;
	}

		// Getters & Setters for Address
    public String getAddress() {
		return Address;
	}
    public void setAddress(String address) {
		this.Address = address; 
	}

		// Getters & Setters for PhoneNumber
    public String getPhoneNumber() {
		return PhoneNumber; 
	}
    public void setPhoneNumber(String phoneNumber) {
		this.PhoneNumber = phoneNumber;
	}
}
