package akilli_kasa;

import java.util.Scanner;

public class MainView {

    private static Scanner scanner = new Scanner(System.in);
    private static KasaViewModel kasaViewModel = new KasaViewModel();

    public static void main(String[] args) {
        kasaViewModel.addProduct(new Product("Çikolata", 20.0));
        kasaViewModel.addProduct(new Product("Meyve", 15.0));

        while (true) {
            System.out.println("\n==== Akıllı Kasa Modülü ====");
            System.out.println("1. Ürün Satışı");
            System.out.println("2. Sebze & Meyve Tartı");
            System.out.println("3. Ürün İade (RFID)");
            System.out.println("4. Çıkış");
            System.out.print("Seçiminiz: ");

            int secim = scanner.nextInt();
            scanner.nextLine(); // Buffer temizleme

            switch (secim) {
                case 1:
                    urunSat();
                    break;
                case 2:
                    urunTarti();
                    break;
                case 3:
                    urunIade();
                    break;
                case 4:
                    System.out.println("Çıkış yapılıyor...");
                    return;
                default:
                    System.out.println("Geçersiz seçim! Lütfen tekrar deneyin.");
            }
        }
    }

    private static void urunSat() {
        System.out.print("Satılan ürün adı: ");
        String urunAdi = scanner.nextLine();
        System.out.print("Miktar: ");
        double miktar = scanner.nextDouble();
        scanner.nextLine(); // Buffer temizleme

        // Ürünü bul
        Product product = findProductByName(urunAdi);
        if (product != null) {
            System.out.println("Ödeme Yöntemi Seçin: ");
            System.out.println("1. Nakit");
            System.out.println("2. POS (Kredi Kartı)");
            System.out.print("Seçiminiz: ");
            int odemeSecim = scanner.nextInt();
            scanner.nextLine(); // Buffer temizleme

            String odemeYontemi = (odemeSecim == 1) ? "Nakit" : "POS";
            kasaViewModel.urunSat(product, miktar, odemeYontemi);
        } else {
            System.out.println("Ürün bulunamadı!");
        }
    }

    private static void urunTarti() {
        System.out.print("Sebze/Meyve adı: ");
        String urunAdi = scanner.nextLine();
        System.out.print("Kg cinsinden ağırlık: ");
        double agirlik = scanner.nextDouble();
        System.out.print("Kg başına fiyat: ");
        double fiyatKg = scanner.nextDouble();
        scanner.nextLine(); // Buffer temizleme

        System.out.println("Ödeme Yöntemi Seçin: ");
        System.out.println("1. Nakit");
        System.out.println("2. POS (Kredi Kartı)");
        System.out.print("Seçiminiz: ");
        int odemeSecim = scanner.nextInt();
        scanner.nextLine(); // Buffer temizleme

        String odemeYontemi = (odemeSecim == 1) ? "Nakit" : "POS";
        kasaViewModel.urunTarti(urunAdi, agirlik, fiyatKg, odemeYontemi);
    }

    private static void urunIade() {
        System.out.print("İade edilen ürün adı: ");
        String urunAdi = scanner.nextLine();
        kasaViewModel.urunIade(urunAdi);
    }

    private static Product findProductByName(String name) {
        for (Product product : kasaViewModel.productList) {
            if (product.getName().equalsIgnoreCase(name)) {
                return product;
            }
        }
        return null; // Ürün bulunamazsa null döndür
    }

}
