//import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;

public class PDF {

    public List<Vente> ventes;

    public static void ExportAnalysis(List<Vente> ventes, String path)
    {
        List<BufferedImage> graphImages = Graphs.ProcessAllGraphs(ventes);

        try 
        {
            PdfWriter writer = new PdfWriter(new FileOutputStream(path));
            com.itextpdf.kernel.pdf.PdfDocument pdfDocument = new com.itextpdf.kernel.pdf.PdfDocument(writer);
            Document document = new Document(pdfDocument);

            for (BufferedImage bufferedImage : graphImages) {
                ImageData imageData = ImageDataFactory.create(bufferedImage, null);
                Image image = new Image(imageData);
                document.add(image);
            }  

            document.close();
            System.out.println("PDF created successfully.");
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
    public static void ShowPreview(String pdfPath)
    {
        try 
        {
            PDDocument document = PDDocument.load(new File(pdfPath));
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            for (int page = 0; page < document.getNumberOfPages(); ++page) 
            {
                BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300);
                ImageIO.write(bim, "png", new File("Temp/page_" + (page + 1) + ".png"));
            }
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }


}
