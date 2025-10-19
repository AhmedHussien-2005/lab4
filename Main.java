import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        System.out.println("========== Inventory Management System ==========\n");
        
        // ==================== Testing AdminRole ====================
        System.out.println("--- Testing AdminRole ---");
        AdminRole admin = new AdminRole();
        
        // Add employees
        System.out.println("\n1. Adding employees:");
        admin.addEmployee("E1001", "Ahmed", "ahmed@email.com", "Alexandria", "01012345678");
        admin.addEmployee("E1002", "Fatima", "fatima@email.com", "Cairo", "01087654321");
        admin.addEmployee("E1003", "Mohamed", "mohamed@email.com", "Giza", "01011223344");
        System.out.println("Successfully added 3 employees");
        
        // Display employee list
        System.out.println("\n2. Employee list:");
        EmployeeUser[] employees = admin.getListOfEmployees();
        for (EmployeeUser emp : employees) {
            System.out.println("  - " + emp.lineRepresentation());
        }
        
        // Remove employee
        System.out.println("\n3. Removing employee E1002:");
        admin.removeEmployee("E1002");
        System.out.println("Successfully removed");
        
        // Display updated employee list
        System.out.println("\n4. Employee list after removal:");
        employees = admin.getListOfEmployees();
        for (EmployeeUser emp : employees) {
            System.out.println("  - " + emp.lineRepresentation());
        }
        
        // Save data
        System.out.println("\n5. Saving employee data:");
        admin.logout();
        System.out.println("Data saved successfully");
        
        // ==================== Testing EmployeeRole ====================
        System.out.println("\n\n--- Testing EmployeeRole ---");
        EmployeeRole employee = new EmployeeRole();
        
        // Add products
        System.out.println("\n1. Adding products:");
        employee.addProduct("P001", "Laptop", "Dell", "TechSupply", 10, 1500.0f);
        employee.addProduct("P002", "Mouse", "Logitech", "TechSupply", 50, 25.0f);
        employee.addProduct("P003", "Keyboard", "Corsair", "TechSupply", 30, 100.0f);
        System.out.println("Successfully added 3 products");
        
        // Display product list
        System.out.println("\n2. Product list:");
        Product[] products = employee.getListOfProducts();
        for (Product prod : products) {
            System.out.println("  - " + prod.lineRepresentation());
        }
        
        // Purchase products
        System.out.println("\n3. Processing purchases:");
        LocalDate date1 = LocalDate.of(2025, 10, 15);
        LocalDate date2 = LocalDate.of(2025, 10, 16);
        LocalDate date3 = LocalDate.of(2025, 10, 17);
        
        boolean purchase1 = employee.purchaseProduct("1234567890", "P001", date1);
        boolean purchase2 = employee.purchaseProduct("9876543210", "P002", date2);
        boolean purchase3 = employee.purchaseProduct("1111111111", "P003", date3);
        
        System.out.println("  Purchase 1 (Laptop): " + (purchase1 ? "SUCCESS" : "FAILED"));
        System.out.println("  Purchase 2 (Mouse): " + (purchase2 ? "SUCCESS" : "FAILED"));
        System.out.println("  Purchase 3 (Keyboard): " + (purchase3 ? "SUCCESS" : "FAILED"));
        
        // Display products after purchase (quantities should decrease)
        System.out.println("\n4. Product list after purchases:");
        products = employee.getListOfProducts();
        for (Product prod : products) {
            System.out.println("  - " + prod.lineRepresentation());
        }
        
        // Display purchasing operations
        System.out.println("\n5. Purchasing operations:");
        CustomerProduct[] purchases = employee.getListOfPurchasingOperations();
        for (CustomerProduct cp : purchases) {
            System.out.println("  - " + cp.lineRepresentation());
        }
        
        // Apply payment
        System.out.println("\n6. Applying payments:");
        boolean payment1 = employee.applyPayment("1234567890", "P001", date1);
        boolean payment2 = employee.applyPayment("9876543210", "P002", date2);
        System.out.println("  Payment 1: " + (payment1 ? "SUCCESS" : "FAILED"));
        System.out.println("  Payment 2: " + (payment2 ? "SUCCESS" : "FAILED"));
        
        // Attempt duplicate payment (should fail)
        System.out.println("  Duplicate payment attempt: " + (employee.applyPayment("1234567890", "P001", date1) ? "SUCCESS" : "FAILED (already paid)"));
        
        // Display purchasing operations after payment (paid status should be true)
        System.out.println("\n7. Purchasing operations after payment:");
        purchases = employee.getListOfPurchasingOperations();
        for (CustomerProduct cp : purchases) {
            System.out.println("  - " + cp.lineRepresentation());
        }
        
        // Return product
        System.out.println("\n8. Processing returns:");
        LocalDate returnDate1 = LocalDate.of(2025, 10, 20);
        double refund1 = employee.returnProduct("1234567890", "P001", date1, returnDate1);
        System.out.println("  Return Laptop: " + (refund1 > 0 ? "SUCCESS - Refund: " + refund1 : "FAILED"));
        
        // Test failed return cases
        LocalDate returnDate2 = LocalDate.of(2025, 11, 5); // More than 14 days
        double refund2 = employee.returnProduct("9876543210", "P002", date2, returnDate2);
        System.out.println("  Return Mouse after 14 days: " + (refund2 == -1 ? "FAILED (too late)" : "SUCCESS"));
        
        // Display products after return (quantities should increase)
        System.out.println("\n9. Product list after returns:");
        products = employee.getListOfProducts();
        for (Product prod : products) {
            System.out.println("  - " + prod.lineRepresentation());
        }
        
        // Save data
        System.out.println("\n10. Saving data to files:");
        employee.logout();
        System.out.println("Data saved successfully");
        
        // ==================== Testing File Reload ====================
        System.out.println("\n\n--- Testing Data Reload from Files ---");
        
        System.out.println("\n1. Reloading employees:");
        AdminRole admin2 = new AdminRole();
        employees = admin2.getListOfEmployees();
        System.out.println("  Total employees loaded: " + employees.length);
        for (EmployeeUser emp : employees) {
            System.out.println("  - " + emp.lineRepresentation());
        }
        
        System.out.println("\n2. Reloading products:");
        EmployeeRole employee2 = new EmployeeRole();
        products = employee2.getListOfProducts();
        System.out.println("  Total products loaded: " + products.length);
        for (Product prod : products) {
            System.out.println("  - " + prod.lineRepresentation());
        }
        
        System.out.println("\n3. Reloading purchasing operations:");
        purchases = employee2.getListOfPurchasingOperations();
        System.out.println("  Total operations loaded: " + purchases.length);
        for (CustomerProduct cp : purchases) {
            System.out.println("  - " + cp.lineRepresentation());
        }
        
        System.out.println("\n\n========== Testing Complete ==========");
    }
}
