using System;

public class Program
{
    public static void Main()
    {
        PastaneModel model = new PastaneModel();
        ConsoleView view = new ConsoleView();
        PastanePresenter presenter = new PastanePresenter(model, view);

        while (true)
        {
            Console.WriteLine("\nPastane Satış ve Stok Takip Sistemi");
            Console.WriteLine("1. Stok Durumunu Görüntüle");
            Console.WriteLine("2. Satış Yap");
            Console.WriteLine("3. Çıkış");
            Console.Write("Seçiminizi yapın: ");

            string secim = Console.ReadLine();
            
            switch (secim)
            {
                case "1":
                    presenter.StokGoruntule();
                    break;
                case "2":
                    presenter.SatisYap();
                    break;
                case "3":
                    Console.WriteLine("Programdan çıkılıyor...");
                    return;
                default:
                    Console.WriteLine("Geçersiz seçim! Lütfen tekrar deneyin.");
                    break;
            }
        }
    }
}
