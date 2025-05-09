import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private Button btnImport;

    @FXML
    private Button btnAnalyse;

    @FXML
    private Button btnTopProduits;

    @FXML
    private Button btnGraphiques;

    @FXML
    private Button btnFormulaire;

    @FXML
    private Button btnModify;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnChargerBDD;

    @FXML
    private Button btnExport;

    @FXML
    private Button btnSave;

    @FXML
    private StackPane contentPane;

    private String FilePath;
    private List<Vente> ventes;

    @FXML
    private void Import()
    {
        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Select Folder");
        filechooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        filechooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
        java.io.File selectedFile = filechooser.showOpenDialog(btnImport.getScene().getWindow());
        if (selectedFile != null) {
            FilePath = selectedFile.getAbsolutePath();
            String FileExtention = FilePath.substring(FilePath.length() - 5);

            if(FileExtention.contains(".csv"))
            {
                ShowDataInGrid_CSV();
            }
            else if(FileExtention.contains(".xlsx"))
            {
                ShowDataInGrid_Excel();
            }
        }
    }

    @FXML
    private void Export()
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ExportInterface.fxml"));
        try
        {
            Parent root = loader.load();
            Stage exportStage = new Stage();
            exportStage.setTitle("Export Options");
            exportStage.setScene(new Scene(root));
            exportStage.show();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    private void AddVente()
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/AddFormulaire.fxml"));
        try
        {
            Parent root = loader.load();
            Stage exportStage = new Stage();
            exportStage.setTitle("Add New Vente");
            exportStage.setScene(new Scene(root));
            exportStage.show();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    private void DeleteVente()
    {
        // Delete Vente Selected in Grid
    }

    @FXML
    private void ModifyVente()
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/ModifyFormulaire.fxml"));
        try
        {
            Parent root = loader.load();
            Stage exportStage = new Stage();
            exportStage.setTitle("Modify Selected Vente");
            exportStage.setScene(new Scene(root));
            exportStage.show();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    private void SaveVentes()
    {
        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Select Folder");
        filechooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        filechooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
        java.io.File selectedFile = filechooser.showOpenDialog(btnImport.getScene().getWindow());
        if (selectedFile != null) 
        {
            FilePath = selectedFile.getAbsolutePath();
            String FileExtention = FilePath.substring(FilePath.length() - 5);

            if(FileExtention.contains(".csv"))
            {
                CSV.SaveSales(this.ventes, FilePath, false);
            }
            else if(FileExtention.contains(".xlsx"))
            {
                Excel.SaveSales(ventes, FilePath);
            }
        }
    }

    private void ShowDataInGrid_CSV()
    {
        this.ventes = CSV.LoadSales(this.FilePath);
        // Show Sales in Grid
    }
    private void ShowDataInGrid_Excel()
    {
        this.ventes = Excel.LoadSales(this.FilePath);
        // Show Sales in Grid
    }


    @FXML
    private void loadFormulaire()
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/formulaire-view.fxml"));
        try
        {
            Parent root = loader.load();
            Stage exportStage = new Stage();
            exportStage.setTitle("formulaire Options");
            exportStage.setScene(new Scene(root));
            exportStage.show();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    }
