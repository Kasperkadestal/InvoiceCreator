package main.model;

public class Unicode {
    public String convert(String text){
        text.replaceAll("å","\u00E5").replaceAll("Å","\u212B").replaceAll("ä","\u00E4").replaceAll("Ä","\u00C4").replaceAll("ö","\u00F6").replaceAll("Ö","\u00D6");
        System.out.println(text+ "  Hello");
        return text;
    }
}
