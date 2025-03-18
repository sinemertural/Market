import java.util.List;

public class MarketViewModel {
    private SaklamaAlani saklamaAlani;
    private List<Urun> urunler;

    public MarketViewModel(SaklamaAlani saklamaAlani, List<Urun> urunler) {
        this.saklamaAlani = saklamaAlani;
        this.urunler = urunler;
    }

    public double sicaklikHesapla(Urun urun) {
        final double uygunSicaklik = -5;
        double fark = Math.abs(saklamaAlani.getDepoSicaklik() - uygunSicaklik);
        return Math.max(urun.getUcret() - fark, 0);
    }

    public double nemHesapla(Urun urun) {
        final double uygunNem = 50;
        double yeniFiyat = urun.getUcret();

        if (Math.abs(saklamaAlani.getDepoNem() - uygunNem) > 10) {
            if (saklamaAlani.getDepoNem() > uygunNem) {
                yeniFiyat *= 0.9;
            } else {
                yeniFiyat *= 1.1;
            }
        }
        return yeniFiyat;
    }

    public String dolulukOraniHesapla() {
        return "Saklama alanının doluluk oranı: %" + saklamaAlani.getDepoDoluluk();
    }

    public void urunFiyatlariniGuncelle() {
        for (Urun urun : urunler) {
            double yeniFiyatSicaklik = sicaklikHesapla(urun);
            double yeniFiyatNem = nemHesapla(urun);
            urun.setUcret(Math.min(yeniFiyatSicaklik, yeniFiyatNem));
        }
    }

    public List<Urun> getUrunler() {
        return urunler;
    }
}
