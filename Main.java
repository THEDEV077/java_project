import java.util.List;


public class Main {

    public static void main(String[] args) {
        List<Vente> ventes = CSV.LoadSales("ventes.csv");
        PDF.ExportAnalysis(ventes, "Temp/export.pdf");
        PDF.ShowPreview("Temp/export.pdf");
        
    }    
}
