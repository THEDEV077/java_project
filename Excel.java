import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {
    public static void SaveSales(List<Vente> ventes,String path)
    {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Ventes");

        Row colRow = sheet.createRow(0);
        colRow.createCell(0).setCellValue("ID_Vente");
        colRow.createCell(1).setCellValue("Date_Vente");
        colRow.createCell(2).setCellValue("Produit");
        colRow.createCell(3).setCellValue("Categorie");
        colRow.createCell(4).setCellValue("Quantite");
        colRow.createCell(5).setCellValue("Prix_Unitaire");
        colRow.createCell(6).setCellValue("Total");

        for(int i = 0; i < ventes.size(); i++)
        {
            Row venteRow = sheet.createRow(i+1);
            venteRow.createCell(0).setCellValue(ventes.get(i).ID_Vente);
            System.out.println(ventes.get(i).Date_Vente.toString());
            venteRow.createCell(1).setCellValue(ventes.get(i).Date_Vente.toString());
            venteRow.createCell(2).setCellValue(ventes.get(i).Produit);
            venteRow.createCell(3).setCellValue(ventes.get(i).Categorie);
            venteRow.createCell(4).setCellValue(ventes.get(i).Quantite);
            venteRow.createCell(5).setCellValue(ventes.get(i).Prix_Unitaire);
            venteRow.createCell(6).setCellValue(ventes.get(i).Total);
        }

        try (FileOutputStream out = new FileOutputStream(path)) {
            workbook.write(out);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static List<Vente> LoadSales(String path)
    {
        List<Vente> ventes = new ArrayList<>();
        try
        {
            FileInputStream input = new FileInputStream(new File(path));
            Workbook workbook = new XSSFWorkbook(input);
            Sheet sheet = workbook.getSheetAt(0);
            
            Iterator<Row> iterator = sheet.rowIterator();
            iterator.next();
            
            while(iterator.hasNext())
            {
                Row row = iterator.next();
                int idvente = (int)row.getCell(0).getNumericCellValue();
                LocalDate datevente = LocalDate.parse(row.getCell(1).getStringCellValue());
                String produit = row.getCell(2).getStringCellValue();
                String categorie = row.getCell(3).getStringCellValue();
                int quantite = (int)row.getCell(4).getNumericCellValue();
                double prix_Unitaire = row.getCell(5).getNumericCellValue();
                double total = row.getCell(6).getNumericCellValue();

                ventes.add(new Vente(idvente, datevente, produit, categorie, quantite, prix_Unitaire, total));
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return ventes;
    }
}
