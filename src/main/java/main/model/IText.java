package main.model;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class IText {
    private Unicode unicode;
    public IText() {
        unicode = new Unicode();
    }

    public void createPDF(List info,List times) throws DocumentException, IOException {
        String title = "Offert | " + info.get(0).toString();
        PdfReader reader = new PdfReader("pdf/Offert_HWG_Golf.pdf");
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream("pdf/Offert_HWG_Golf_" + info.get(0).toString() + ".pdf"));

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
            over.showText(info.get(i+1).toString().replaceAll("å", "\u00E5").replaceAll("Å", "\u212B").replaceAll("ä", "\u00E4").replaceAll("Ä", "\u00C4").replaceAll("ö", "\u00F6").replaceAll("Ö", "\u00D6"));
        }

        for (int i = 8; i < 11; i++) {
            if (i==8) {
                over.setFontAndSize(BaseFont.createFont("fonts/futura medium bt.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 12);
            }
            if (i>8) {
                over.setFontAndSize(BaseFont.createFont("fonts/Futura Book font.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 12);
            }
            over.setTextMatrix(92,y-(i*20)-20);
            over.showText(info.get(i+1).toString().replaceAll("å", "\u00E5").replaceAll("Å", "\u212B").replaceAll("ä", "\u00E4").replaceAll("Ä", "\u00C4").replaceAll("ö", "\u00F6").replaceAll("Ö", "\u00D6"));
        }

        System.out.println(times.size());
        if (!times.isEmpty()) {
            int b = 0;
            for (int i = 0; i < times.size(); i++) {
                if (!Character.isDigit(times.get(i).toString().charAt(0))) {
                    if (i != 0) {
                        b++;
                    }
                    over.setFontAndSize(BaseFont.createFont("fonts/futura medium bt.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 12);
                    over.setTextMatrix(378, 437 - ((i * 20) + (5 * b)));
                    over.showText(times.get(i).toString());
                } else {
                    over.setFontAndSize(BaseFont.createFont("fonts/Futura Book font.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 12);
                    over.setTextMatrix(378, 437 - ((i * 20) + (5 * b)));
                    over.showText(times.get(i).toString());
                }
            }
        }

        over.setRGBColorFill(255, 0, 0);
        over.setFontAndSize(BaseFont.createFont("fonts/Futura Heavy font.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 20);
        over.showTextAligned(PdfContentByte.ALIGN_CENTER,info.get(12).toString() + ":-",438,120,0);
        over.endText();

        stamper.close();
    }
}
