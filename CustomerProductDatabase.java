import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

// CustomerProductDatabase extending Database
public class CustomerProductDatabase extends Data {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public CustomerProductDatabase(String filename) {
        super(filename);
    }

    @Override
    protected Record createRecordFrom(String line) {
        // Split the line by comma
        String[] data = line.split(",");
        
        // Extract customer SSN and product ID
        String ssn = data[0];
        String prodId = data[1];
        
        // Parse the date
        LocalDate date = LocalDate.parse(data[2], DATE_FORMATTER);
        
        // Check if paid status exists
        boolean isPaid = false;
        if (data.length > 3) {
            isPaid = Boolean.parseBoolean(data[3]);
        }
        
        // Create and return the CustomerProduct object
        CustomerProduct cp = new CustomerProduct(ssn, prodId, date, isPaid);
        return cp;
    }

    // Method to get all customer products as an ArrayList
    public ArrayList<CustomerProduct> getAllCustomerProducts() {
        ArrayList<CustomerProduct> customerPurchases = new ArrayList<>();
        
        // Loop through all records and add them to the list
        for (int i = 0; i < records.size(); i++) {
            Record rec = records.get(i);
            CustomerProduct custProd = (CustomerProduct) rec;
            customerPurchases.add(custProd);
        }
        
        return customerPurchases;
    }

    // Method to get a specific customer product record by key
    public CustomerProduct getCustomerProductRecord(String key) {
        Record rec = getRecord(key);
        
        if (rec == null) {
            return null;
        } else {
            return (CustomerProduct) rec;
        }
    }
}
