public class Urun {
    private int urunId;
    private String isim;
    private double kg;
    private double ucret;

    public Urun(int urunId, String isim, double kg, double ucret) {
        this.urunId = urunId;
        this.isim = isim;
        this.kg = kg;
        this.ucret = ucret;
    }

    public int getUrunId() { return urunId; }
    public String getIsim() { return isim; }
    public double getKg() { return kg; }
    public double getUcret() { return ucret; }
    
    public void setUcret(double yeniUcret) { this.ucret = yeniUcret; }
}
