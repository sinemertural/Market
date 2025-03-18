// Presenter
public class PastanePresenter
{
    private readonly PastaneModel model;
    private readonly IPastaneView view;

    public PastanePresenter(PastaneModel model, IPastaneView view)
    {
        this.model = model;
        this.view = view;
    }

    public void StokGoruntule()
    {
        view.StokGoster(model.GetStokDurumu());
    }

    public void SatisYap()
    {
        string urunAdi = view.KullaniciGirdisi("Satılacak ürün adını girin:");
        if (int.TryParse(view.KullaniciGirdisi("Kaç adet satılacak?"), out int adet))
        {
            bool basarili = model.SatisYap(urunAdi, adet);
            view.SatisSonucu(basarili, urunAdi, adet);
        }
        else
        {
            view.MesajGoster("Geçersiz giriş! Lütfen bir sayı girin.");
        }
    }
}