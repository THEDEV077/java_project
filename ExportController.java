import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.stage.FileChooser;

public class ExportController {

    private String SavePath;

    @FXML
    private Button PathButton;

    @FXML
    private Button SaveButton;

    @FXML
    private RadioButton WordRadio;

    @FXML
    private RadioButton PdfRadio;

    @FXML
    private void SaveFile() 
    {
        List<Vente> ventes = CSV.LoadSales("ventes.csv");
        if(PdfRadio.isSelected())
        {
            PDF.ExportAnalysis(ventes, SavePath);
        }
        else if(WordRadio.isSelected())
        {
            // Save Word
        }
        PathButton.getScene().getWindow().hide();
    }

    @FXML
    private void ChooseDirectory()
    {
        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Select Directory");
        filechooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
        java.io.File selectedDirectory = filechooser.showSaveDialog(PathButton.getScene().getWindow());
        if (selectedDirectory != null) {
            SavePath = selectedDirectory.getAbsolutePath();
        }
    }   
}

