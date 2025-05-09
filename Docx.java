import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.common.usermodel.PictureType;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class Docx {

    // public static void ExportAnalysis(List<Vente> ventes, String path)
    // {
    //     XWPFDocument document = new XWPFDocument();

    //     // Create a paragraph
    //     XWPFParagraph paragraph = document.createParagraph();
    //     XWPFRun run = paragraph.createRun();

    //     try
    //     {
    //         List<BufferedImage> graphImages = Graphs.ProcessAllGraphs(ventes);
    //         for (BufferedImage bufferedImage : graphImages) {

    //             //InputStream pic = new FileInputStream(path);
    //             run.addPicture(
    //             pic,
    //             PictureType.PNG, // or PICTURE_TYPE_JPEG, etc.
    //             path,
    //             Units.toEMU(200), // width
    //             Units.toEMU(200)  // height
    //             );
    //             pic.close();
    //         }  

    //         FileOutputStream out = new FileOutputStream(path);
    //         document.write(out);

    //         document.close();
    //         System.out.println("Image inserted successfully!");
    //     }
    //     catch(IOException e)
    //     {
    //         e.printStackTrace();
    //     }  
    //     catch(InvalidFormatException e)
    //     {
    //         e.printStackTrace();
    //     }
    // }
}
