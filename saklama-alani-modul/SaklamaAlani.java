public class SaklamaAlani {
    private double depoSicaklik;
    private double depoNem;
    private double depoDoluluk;

    public SaklamaAlani(double depoSicaklik, double depoNem, double depoDoluluk) {
        this.depoSicaklik = depoSicaklik;
        this.depoNem = depoNem;
        this.depoDoluluk = depoDoluluk;
    }

    public double getDepoSicaklik() { return depoSicaklik; }
    public double getDepoNem() { return depoNem; }
    public double getDepoDoluluk() { return depoDoluluk; }
}
