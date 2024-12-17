package RMI;

import java.io.Serializable;

public class Order implements Serializable {
    private static final long serialVersionUID = 20241132L;

    private String id;
    private String customerCode;
    private String orderDate;
    private String shippingType;
    private String orderCode;

    public Order() {
        // Constructor mặc định
    }

    public Order(String id, String customerCode, String orderDate, String shippingType) {
        this.id = id;
        this.customerCode = customerCode;
        this.orderDate = orderDate;
        this.shippingType = shippingType;
    }

    // Getter and Setter methods
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getShippingType() {
        return shippingType;
    }

    public void setShippingType(String shippingType) {
        this.shippingType = shippingType;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    // Tạo orderCode theo quy tắc đã cho
    public void generateOrderCode() {
        String shippingPrefix = shippingType.length() > 1 ? shippingType.substring(0, 2).toUpperCase() : shippingType.toUpperCase();
        String customerSuffix = customerCode.length() > 3 ? customerCode.substring(customerCode.length() - 3) : customerCode;
        String dayMonth = orderDate.substring(8, 10) + orderDate.substring(5, 7);  // ddMM

        this.orderCode = shippingPrefix + customerSuffix + dayMonth;
    }

    @Override
    public String toString() {
        return "Order{id='" + id + "', customerCode='" + customerCode + "', orderDate='" + orderDate + "', shippingType='" + shippingType + "', orderCode='" + orderCode + "'}";
    }
}
