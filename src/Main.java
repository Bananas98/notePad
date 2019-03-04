import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    private final String NAME = "new file";
    private JFileChooser f = new JFileChooser();
    private JTabbedPane tabs = new JTabbedPane();
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                new Main();
            }
        });
    }
    public Main(){

        JMenuBar menu = new JMenuBar();

        JMenu file = new JMenu("File");

        JMenuItem newfile = new JMenuItem("new file");
        JMenuItem openfile = new JMenuItem("open file");
        JMenuItem savefile = new JMenuItem("save file");

        file.add(newfile);
        file.add(openfile);
        file.add(savefile);

        menu.add(file);


        JFrame window = new JFrame("Notepad");
        window.setSize(888,666);

        window.setJMenuBar(menu);

        window.add(tabs);


        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        newfile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextArea text = new JTextArea();

                Scrol scrol = new Scrol(text,false,"");

                tabs.addTab(NAME,scrol);
            }
        });
        savefile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Scrol text = (Scrol) tabs.getSelectedComponent();
                String output = text.getText();



                if (tabs.countComponents() != 0) {
                    if (text.isOpen()) {
                        try {
                            FileOutputStream writer = new FileOutputStream(text.getPath());
                            writer.write(output.getBytes());
                        } catch (IOException eq) { eq.printStackTrace();}

                    } else {
                        f.showSaveDialog(null);
                        File file = f.getSelectedFile();
                        try {
                            FileOutputStream writer = new FileOutputStream(file);
                            writer.write(output.getBytes());
                        } catch (IOException eq) {
                            eq.printStackTrace();
                        }
                    }
                }
            }});

        openfile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    f.showOpenDialog(null);
                    File file1 = f.getSelectedFile();

                    String input = new String(Files.readAllBytes(Paths.get(file1.getAbsolutePath())));
                    JTextArea text = new JTextArea(input);

                    Scrol scrol = new Scrol(text,true,file1.getAbsolutePath());

                    tabs.addTab(file.getName(),scrol);

                }catch (IOException eq){eq.printStackTrace();}
            }
        });


    }

}
