import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


// EmployeeRole class extending Role
public class EmployeeRole extends Role {
    private ProductDatabase productsDatabase;
    private CustomerProductDatabase customerProductDatabase;

    public EmployeeRole() {
        this.productsDatabase = new ProductDatabase("Products.txt");
        this.customerProductDatabase = new CustomerProductDatabase("CustomersProducts.txt");
    }

    public void addProduct(String productID, String productName, String manufacturerName, 
                          String supplierName, int quantity, float price) {
        // Create a new product object
        Product newProduct = new Product(productID, productName, manufacturerName, 
                                        supplierName, quantity, price);
        
        // Insert the product into the database
        productsDatabase.insertRecord(newProduct);
        
        // Save changes to file
        productsDatabase.saveToFile();
    }

    public Product[] getListOfProducts() {
        // Get all products from database
        ArrayList<Product> allProducts = productsDatabase.getAllProducts();
        
        // Convert ArrayList to array
        Product[] productArray = new Product[allProducts.size()];
        for (int i = 0; i < allProducts.size(); i++) {
            productArray[i] = allProducts.get(i);
        }
        
        return productArray;
    }

    public CustomerProduct[] getListOfPurchasingOperations() {
        // Get all customer products from database
        ArrayList<CustomerProduct> allPurchases = customerProductDatabase.getAllCustomerProducts();
        
        // Convert ArrayList to array
        CustomerProduct[] purchaseArray = new CustomerProduct[allPurchases.size()];
        for (int i = 0; i < allPurchases.size(); i++) {
            purchaseArray[i] = allPurchases.get(i);
        }
        
        return purchaseArray;
    }

    public boolean purchaseProduct(String customerSSN, String productID, LocalDate purchaseDate) {
        // Get the product from database
        Product prod = productsDatabase.getProductRecord(productID);
        
        // Check if product exists
        if (prod == null) {
            return false;
        }
        
        // Check if product is in stock
        if (prod.getQuantity() == 0) {
            return false;
        }

        // Decrease the quantity by 1
        int currentQuantity = prod.getQuantity();
        prod.setQuantity(currentQuantity - 1);
        
        // Create a new purchase record
        CustomerProduct newPurchase = new CustomerProduct(customerSSN, productID, purchaseDate);
        customerProductDatabase.insertRecord(newPurchase);
        
        // Save both databases to file
        productsDatabase.saveToFile();
        customerProductDatabase.saveToFile();
        
        return true;
    }

    public double returnProduct(String customerSSN, String productID, 
                               LocalDate purchaseDate, LocalDate returnDate) {
        // Check if return date is before purchase date
        if (returnDate.isBefore(purchaseDate)) {
            return -1;
        }

        // Get the product from database
        Product prod = productsDatabase.getProductRecord(productID);
        if (prod == null) {
            return -1;
        }

        // Create the search key for customer product
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dateString = purchaseDate.format(formatter);
        String searchKey = customerSSN + "," + productID + "," + dateString;

        // Check if the purchase record exists
        if (!customerProductDatabase.contains(searchKey)) {
            return -1;
        }

        // Calculate days between purchase and return
        long daysDifference = ChronoUnit.DAYS.between(purchaseDate, returnDate);
        
        // Check if more than 14 days have passed
        if (daysDifference > 14) {
            return -1;
        }

        // Process the return
        // Increase quantity by 1
        int currentQty = prod.getQuantity();
        prod.setQuantity(currentQty + 1);
        
        // Remove the purchase record
        customerProductDatabase.deleteRecord(searchKey);
        
        // Save both databases
        productsDatabase.saveToFile();
        customerProductDatabase.saveToFile();
        
        // Return the product price
        double productPrice = prod.getPrice();
        return productPrice;
    }

    public boolean applyPayment(String customerSSN, String productID, LocalDate purchaseDate) {
        // Build the search key
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String dateString = purchaseDate.format(formatter);
        String searchKey = customerSSN + "," + productID + "," + dateString;
        
        // Get the purchase record
        CustomerProduct purchase = customerProductDatabase.getCustomerProductRecord(searchKey);
        
        // Check if purchase exists
        if (purchase == null) {
            return false;
        }
        
        // Check if already paid
        if (purchase.isPaid()) {
            return false;
        }

        // Mark as paid
        purchase.setPaid(true);
        
        // Save to file
        customerProductDatabase.saveToFile();
        
        return true;
    }

    @Override
    protected void saveData() {
        // Save both databases
        productsDatabase.saveToFile();
        customerProductDatabase.saveToFile();
    }

    @Override
    public void logout() {
        // Call saveData to write everything to files
        saveData();
    }
}
