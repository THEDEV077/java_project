import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

    @FXML
    private TableView<Vente> VentesTableView;

    private String FilePath;
    private List<Vente> ventes;

    @FXML
    private void initialize()
    {
        ObservableList<TableColumn<Vente, ?>> columns = VentesTableView.getColumns();
        columns.get(0).setCellValueFactory(new PropertyValueFactory<>("ID_Vente"));
        columns.get(1).setCellValueFactory(new PropertyValueFactory<>("Date_Vente"));
        columns.get(2).setCellValueFactory(new PropertyValueFactory<>("Produit"));
        columns.get(3).setCellValueFactory(new PropertyValueFactory<>("Categorie"));
        columns.get(4).setCellValueFactory(new PropertyValueFactory<>("Quantite"));
        columns.get(5).setCellValueFactory(new PropertyValueFactory<>("Prix_Unitaire"));
        columns.get(6).setCellValueFactory(new PropertyValueFactory<>("Total"));
    }

    private void LoadGrid()
    {
        ObservableList<Vente> data = FXCollections.observableArrayList(ventes);
        VentesTableView.setItems(data);
    }
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
    private void ShowDataInGrid_CSV()
    {
        this.ventes = CSV.LoadSales(this.FilePath);
        LoadGrid();
    }

    private void ShowDataInGrid_Excel()
    {
        this.ventes = Excel.LoadSales(this.FilePath);
        LoadGrid();
    }

    @FXML
    private void Export()
    {
        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Select Directory");
        filechooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF File", "*.pdf"));
        java.io.File selectedDirectory = filechooser.showSaveDialog(btnExport.getScene().getWindow());
        if (selectedDirectory != null) {
            PDF.ExportAnalysis(this.ventes, selectedDirectory.getAbsolutePath());
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

        Vente selectedVente = VentesTableView.getSelectionModel().getSelectedItem();

        if(selectedVente != null)
        {
            Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
            confirmation.setTitle("Confirm Deletion");
            confirmation.setHeaderText("Do You Really Want to Delete Selected Sale ?");
            confirmation.setContentText("Sale will be deleted PERMANENTLY");

            Optional<ButtonType> result = confirmation.showAndWait();

            if(result.isPresent() && result.get() == ButtonType.OK)
            {
                ventes.remove(selectedVente);
                LoadGrid();
            }
        }
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
        filechooser.setTitle("Select Directory");
        filechooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        filechooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
        java.io.File selectedFile = filechooser.showSaveDialog(btnSave.getScene().getWindow());
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

    
}
