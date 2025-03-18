using System;
using System.Collections.Generic;
public interface IPastaneView
{
    void StokGoster(Dictionary<string, int> stok);
    void SatisSonucu(bool basarili, string urunAdi, int adet);
    string KullaniciGirdisi(string mesaj);
    void MesajGoster(string mesaj);
}
