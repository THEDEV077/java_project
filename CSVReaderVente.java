import com.opencsv.CSVReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVReaderVente {
    public static List<Vente> lireFichier(String path) {
        List<Vente> ventes = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(path))) {
            String[] line;
            reader.readNext(); // skip header
            while ((line = reader.readNext()) != null) {
                Vente v = new Vente(
                    Integer.parseInt(line[0]),
                    line[1],
                    line[2],
                    line[3],
                    Integer.parseInt(line[4]),
                    Double.parseDouble(line[5]),
                    Double.parseDouble(line[6])
                );
                ventes.add(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ventes;
    }
}
