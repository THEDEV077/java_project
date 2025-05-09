import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSV{

    public static void SaveSales(List<Vente> ventes, String path, Boolean append)
    {
        try 
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
 
            System.out.println("read existent data");
            BufferedReader reader = new BufferedReader(new FileReader(path));
            System.out.println(reader.ready());
            String inputData = "";
            String inputline;
            while((inputline=reader.readLine()) != null)
            {
                inputData += inputline;
                inputData += "\n";
                System.out.println("data existed : " + inputline + "\n");
                writer.write(inputData);
            }
            
            reader.close();
            
            for (Vente vente : ventes) 
            {
                String line = vente.ID_Vente + "," + vente.Date_Vente + "," + vente.Produit + "," + vente.Categorie + "," + vente.Quantite + "," + vente.Prix_Unitaire + "," + vente.Total;
                writer.write(line);
                writer.newLine();
                System.out.println("new data : " + line + "\n");
            }
            writer.close();
            
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        
    }
    public static void SaveSales(Vente vente, String path, Boolean append)
    {
        try 
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));

            if(append == true)
            {
                System.out.println("read existent data");
                BufferedReader reader = new BufferedReader(new FileReader(path));
                String inputData = "";
                String inputline;
                while((inputline=reader.readLine()) != null)
                {
                    inputData += inputline;
                    inputData += "\n";
                    System.out.println("data existed : " + inputline + "\n");
                    writer.write(inputData);
                }
                
                reader.close();
            }
            
            String line = vente.ID_Vente + "," + vente.Date_Vente + "," + vente.Produit + "," + vente.Categorie + "," + vente.Quantite + "," + vente.Prix_Unitaire + "," + vente.Total;
            writer.write(line);
            writer.newLine();

            writer.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public static List<Vente> LoadSales(String path)
    {
        List<Vente> ventes = new ArrayList<>();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while((line=reader.readLine()) != null)
            {
                ventes.add(new Vente(line));
            }
            reader.close();
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return ventes;
    }
}
