import javax.swing.*;

public class Scrol extends JScrollPane {
    private final String path;
    private final boolean isOpen;
   private final JTextArea text;
    public Scrol(JTextArea text,boolean isOpen,String path){
        super(text);
        this.text = text;
        this.isOpen = isOpen;
        this.path = path;

    }
    public String getText(){
        return text.getText();
    }

    public boolean isOpen(){
        return isOpen;
    }

    public String getPath(){ return path; }

}
