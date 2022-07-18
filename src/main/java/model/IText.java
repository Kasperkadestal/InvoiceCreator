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
        System.out.println("Size: " + reader.getPageSize(1));

        // get object for writing over the existing content;
        // you can also use getUnderContent for writing in the bottom layer
        PdfContentByte over = stamper.getOverContent(1);

        // write title
        over.beginText();
        over.setRGBColorFill(8, 76, 52);
        over.setFontAndSize(BaseFont.createFont("fonts/Futura Heavy font.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 24);
        over.showTextAligned(PdfContentByte.ALIGN_CENTER, title.replaceAll("å", "\u00E5").replaceAll("Å", "\u212B").replaceAll("ä", "\u00E4").replaceAll("Ä", "\u00C4").replaceAll("ö", "\u00F6").replaceAll("Ö", "\u00D6"), (float) (595.276 / 2), 525, 0);
        over.endText();

        // write content
        over.beginText();
        over.setRGBColorFill(0, 0, 0);
        over.setFontAndSize(BaseFont.createFont("fonts/Futura Book font.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 12);
        int y = 437;
        for (int i = 0; i < 8; i++) {
            over.setTextMatrix(160,y-(i*20));
            over.showText("Gran Canaria");
        }
        over.setFontAndSize(BaseFont.createFont("fonts/futura medium bt.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 12);
        over.setTextMatrix(92,y-(9*20));
        over.showText("Köpenhamn - Gran Canaria | SAS".replaceAll("å", "\u00E5").replaceAll("Å", "\u212B").replaceAll("ä", "\u00E4").replaceAll("Ä", "\u00C4").replaceAll("ö", "\u00F6").replaceAll("Ö", "\u00D6"));
        over.setFontAndSize(BaseFont.createFont("fonts/Futura Book font.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 12);
        over.setTextMatrix(92,y-(10*20));
        over.showText("SK2837 18DEC CPH-LPA 0805- 1230");
        over.setTextMatrix(92,y-(11*20));
        over.showText("SK2837 18DEC CPH-LPA 0805- 1230");

        over.endText();

        stamper.close();
    }
}
