package entity;

public class Customer {
    private String customerId;
    private String customerName;
    private String customerContactNumber;

    public Customer(String customerId, String customerName, String customerContactNumber) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerContactNumber = customerContactNumber;
    }

    public String getCustomerId() { return customerId; }
    public String getCustomerName() { return customerName; }
    public String getCustomerContactNumber() { return customerContactNumber; }
}
