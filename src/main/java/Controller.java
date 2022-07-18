import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import model.IText;

import java.io.FileOutputStream;
import java.io.IOException;

public class Controller {
    public Controller() throws IOException, DocumentException {
        IText iText = new IText();
        String[] info = new String[5];
        info[0] = "Håkan Håkansson";
        iText.createPDF(info);
    }
}
