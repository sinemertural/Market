public class Sale {
    public Product product;
    public double quantity;
    public double totalPrice;
    public String paymentMethod;

    public Sale(Product product, double quantity, double totalPrice, String paymentMethod) {
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.paymentMethod = paymentMethod;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public Product getProduct() {
        return product;
    }
}
