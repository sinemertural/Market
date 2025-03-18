using System;
using System.Collections.Generic;
public class ConsoleView : IPastaneView
{
    public void StokGoster(Dictionary<string, int> stok)
    {
        Console.WriteLine("\n--- Stok Durumu ---");
        foreach (var urun in stok)
        {
            Console.WriteLine($"{urun.Key}: {urun.Value} adet");
        }
    }

    public void SatisSonucu(bool basarili, string urunAdi, int adet)
    {
        if (basarili)
        {
            Console.WriteLine($"{adet} adet {urunAdi} satıldı.");
        }
        else
        {
            Console.WriteLine("Yetersiz stok veya yanlış ürün adı!");
        }
    }

    public string KullaniciGirdisi(string mesaj)
    {
        Console.Write(mesaj + " ");
        return Console.ReadLine();
    }

    public void MesajGoster(string mesaj)
    {
        Console.WriteLine(mesaj);
    }
}
