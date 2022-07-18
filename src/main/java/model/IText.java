package model;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.FileOutputStream;
import java.io.IOException;

public class IText {
    private Unicode unicode;
    public IText() {
        unicode = new Unicode();
    }

    public void createPDF(String[] info) throws DocumentException, IOException {
        String title = "Offert | " + info[0];
        PdfReader reader = new PdfReader("pdf/Offert_HWG_Golf.pdf");
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream("pdf/Offert_HWG_Golf_Modified.pdf")); // output PDF
        BaseFont bf = BaseFont.createFont("fonts/Futura Heavy font.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        System.out.println("Size: " + reader.getPageSize(1));

        //loop on pages (1-based)
        for (int i=1; i<=reader.getNumberOfPages(); i++){

            // get object for writing over the existing content;
            // you can also use getUnderContent for writing in the bottom layer
            PdfContentByte over = stamper.getOverContent(i);

            // write text
            over.beginText();
            over.setRGBColorFill(8,76,52);
            over.setFontAndSize(bf, 24);    // set font and size
            over.showTextAligned(PdfContentByte.ALIGN_CENTER,title.replaceAll("å","\u00E5").replaceAll("Å","\u212B").replaceAll("ä","\u00E4").replaceAll("Ä","\u00C4").replaceAll("ö","\u00F6").replaceAll("Ö","\u00D6"), (float) (595.276/2),525,0);  // set text
            over.endText();
        }

        stamper.close();
    }
}
