import java.util.ArrayList;
import java.util.List;

public class KasaViewModel {

    public List<Product> productList = new ArrayList<>();
    public List<Sale> salesList = new ArrayList<>();

    // Ürünleri ekle
    public void addProduct(Product product) {
        productList.add(product);
    }

    // Ürün satışı işlemi
    public void urunSat(Product product, double quantity, String paymentMethod) {
        double totalPrice = product.getPrice() * quantity;
        Sale sale = new Sale(product, quantity, totalPrice, paymentMethod);
        salesList.add(sale);
        System.out.println("Satış işlemi başarılı! " + product.getName() + " - Toplam: " + totalPrice + " TL.");
    }

    // Sebze ve meyve tartım işlemi
    public void urunTarti(String productName, double weight, double pricePerKg, String paymentMethod) {
        double totalPrice = weight * pricePerKg;
        Product product = new Product(productName, pricePerKg);
        Sale sale = new Sale(product, weight, totalPrice, paymentMethod);
        salesList.add(sale);
        System.out.println("Tartım işlemi başarılı! " + productName + " için toplam: " + totalPrice + " TL.");
    }

    // İade işlemi
    public void urunIade(String productName) {
        System.out.println(productName + " iade ediliyor...");
    }

    // Satışların görüntülenmesi (isteğe bağlı)
    public void displaySales() {
        for (Sale sale : salesList) {
            System.out.println("Ürün: " + sale.getProduct().getName() + ", Tutar: " + sale.getTotalPrice()
                    + " TL, Ödeme: " + sale.getPaymentMethod());
        }
    }
}
