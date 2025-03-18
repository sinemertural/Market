using System;
using System.Collections.Generic;
public class PastaneModel
{
    private Dictionary<string, int> stok = new Dictionary<string, int>
    {
        { "Ekmek", 10 },
        { "Poğaça", 15 },
        { "Simit", 20 },
        { "Pasta", 5 },
        { "Kruvasan", 8 }
    };

    public Dictionary<string, int> GetStokDurumu()
    {
        return stok;
    }

    public bool SatisYap(string urunAdi, int adet)
    {
        if (stok.ContainsKey(urunAdi) && stok[urunAdi] >= adet)
        {
            stok[urunAdi] -= adet;
            return true;
        }
        return false;
    }
}

