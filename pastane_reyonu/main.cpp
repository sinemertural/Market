#include <iostream>
#include <string>
#include <map>

using namespace std;

class Bakery {
private:
    // Ürünlerin alım ve satım fiyatları
    double adetSimitAlisFiyat = 5.0;
    double adetSimitSatisFiyat = 10.0;
    double adetPastaAlisFiyat = 15.0;
    double adetPastaSatisFiyat = 25.0;
    double kiloBörekAlisFiyat = 25.0;
    double kiloBörekSatisFiyat = 50.0;
    double kiloBaklavaAlisFiyat = 50.0;
    double kiloBaklavaSatisFiyat = 100.0;

    // Satılan miktarların saklanacağı map
    map<string, double> satislar;

public:
    // Satış işlemi gerçekleştiren fonksiyon
    void satisYap(string urun, double miktar) {
        satislar[urun] += miktar;
    }

    // Kazancı hesaplayan fonksiyon
    double kazancHesapla() {
        double kazanc = 0.0;

        // Simit kazancı
        kazanc += (adetSimitSatisFiyat - adetSimitAlisFiyat) * satislar["Simit"];
        // Pasta kazancı
        kazanc += (adetPastaSatisFiyat - adetPastaAlisFiyat) * satislar["Pasta"];
        // Börek kazancı
        kazanc += (kiloBörekSatisFiyat - kiloBörekAlisFiyat) * satislar["Börek"];
        // Baklava kazancı
        kazanc += (kiloBaklavaSatisFiyat - kiloBaklavaAlisFiyat) * satislar["Baklava"];

        return kazanc;
    }

    // Satış bilgilerini yazdıran fonksiyon
    void satisBilgileriniYazdir() {
        cout << "Satılan Ürünler:\n";
        cout << "Simit: " << satislar["Simit"] << " adet\n";
        cout << "Pasta: " << satislar["Pasta"] << " adet\n";
        cout << "Börek: " << satislar["Börek"] << " kilo\n";
        cout << "Baklava: " << satislar["Baklava"] << " kilo\n";
    }

    // Kazancı yazdıran fonksiyon
    void kazancYazdir() {
        cout << "Toplam Kazanç: " << kazancHesapla() << " TL\n";
    }

    // Müşteriye ürün seçtiren ve fiyatı hesaplayan fonksiyon
    void urunSat() {
        string urun;
        double miktar;

        cout << "Hangi ürünü almak istersiniz? (Simit, Pasta, Börek, Baklava): ";
        cin >> urun;

        if (urun == "Simit" || urun == "Pasta" || urun == "Börek" || urun == "Baklava") {
            cout << "Kaç adet/kilo almak istersiniz? ";
            cin >> miktar;

            satisYap(urun, miktar);

            // Ürün fiyatını hesapla ve kullanıcıya göster
            double fiyat = 0.0;
            if (urun == "Simit") {
                fiyat = adetSimitSatisFiyat * miktar;
            } else if (urun == "Pasta") {
                fiyat = adetPastaSatisFiyat * miktar;
            } else if (urun == "Börek") {
                fiyat = kiloBörekSatisFiyat * miktar;
            } else if (urun == "Baklava") {
                fiyat = kiloBaklavaSatisFiyat * miktar;
            }

            cout << "Toplam Tutar: " << fiyat << " TL\n";
        } else {
            cout << "Geçersiz ürün! Lütfen 'Simit', 'Pasta', 'Börek' veya 'Baklava' girin.\n";
        }
    }
};

int main() {
    Bakery pastane;
    char devam;

    do {
        // Yeni bir müşteri geldiğinde ürün seçmesini sağla
        pastane.urunSat();

        // Gün sonu bilgilerini yazdır
        pastane.satisBilgileriniYazdir();
        pastane.kazancYazdir();

        cout << "Başka bir müşteri var mı? (E/H): ";
        cin >> devam;

    } while (devam == 'E' || devam == 'e');

    cout << "Gün sonu hesaplamaları tamamlandı.\n";
    pastane.satisBilgileriniYazdir();
    pastane.kazancYazdir();

    return 0;
}