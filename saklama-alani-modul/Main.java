import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Ürünleri oluştur
        List<Urun> urunler = new ArrayList<>();
        urunler.add(new Urun(1, "Elma", 10, 50));
        urunler.add(new Urun(2, "Çilek", 5, 80));
        urunler.add(new Urun(3, "Brokoli", 8, 60));
        urunler.add(new Urun(4, "Balık", 12, 100));
        urunler.add(new Urun(5, "Patates", 20, 40));

        // Depo koşullarını oluştur
        SaklamaAlani depo = new SaklamaAlani(-2, 55, 75);

        // ViewModel oluştur
        MarketViewModel viewModel = new MarketViewModel(depo, urunler);

        // Ürün fiyatlarını güncelle
        viewModel.urunFiyatlariniGuncelle();

        // Güncellenmiş bilgileri göster
        for (Urun urun : viewModel.getUrunler()) {
            System.out.println(urun.getIsim() + " - Güncellenmiş Fiyat: " + urun.getUcret() + " TL");
        }

        // Doluluk oranını göster
        System.out.println(viewModel.dolulukOraniHesapla());
    }
}
