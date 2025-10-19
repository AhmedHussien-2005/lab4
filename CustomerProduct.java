import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class CustomerProduct implements Record {
    private String customerSSN,productID;
    private LocalDate purchaseDate;
    private boolean paid;
    

    protected static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");


    public CustomerProduct(String customerSSN, String productID, LocalDate purchaseDate, boolean paid) {
        this.customerSSN = customerSSN;
        this.productID = productID;
        this.purchaseDate = purchaseDate;
        this.paid = paid;
    }

    public CustomerProduct(String customerSSN, String productID, LocalDate purchaseDate) {
        this(customerSSN, productID, purchaseDate, false);
    }



    public String getCustomerSSN() {
        return customerSSN;
    }

    public String getProductID() {
        return productID;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Override
    public String lineRepresentation() {
        return customerSSN+","+productID+","+purchaseDate.format(DATE_FORMATTER)+","+ paid;
    }

    @Override
    public String getSearchKey() {
        return customerSSN+","+productID+","+purchaseDate.format(DATE_FORMATTER);
    }
}
