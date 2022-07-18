package main;

import com.itextpdf.text.DocumentException;
import main.model.IText;
import main.view.GUI;

import java.io.IOException;
import java.util.List;

public class Controller {
    public Controller() throws IOException, DocumentException {
        GUI gui = new GUI();
        gui.start(this);
    }

    public void createPDF(List info,List times){
        IText iText = new IText();
        try {
            iText.createPDF(info,times);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
