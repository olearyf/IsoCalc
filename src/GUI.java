import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GUI extends JFrame{

    String[] options = {"--Select One--", "EE (2 files)", "KIE (4 files)"};
    JTextField input, output;
    JButton compute;
    JComboBox optionList, optionList2;
    JComboBox optionMenu;
    FileManager fm = new FileManager();
    ArrayList<File> files = new ArrayList<>();

    GUI(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        setTitle("IsoCalc");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int h = (int)screenSize.getHeight();
        int w = (int)screenSize.getWidth();
        setLocation(w/2-200, h/2-300);
        setResizable(false);
        ImageIcon icon = new ImageIcon("C:\\Users\\fraol\\IdeaProjects\\IsoCalc\\science.png");
        setIconImage(icon.getImage());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        JButton openFile = new JButton("Add File");
        JLabel filesLabel = new JLabel("Current Files:");
        JTextArea openedFiles = new JTextArea(6, 5);
        openedFiles.setText("Current Files: \n");
        openedFiles.setEditable(false);
        openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnValue = jfc.showOpenDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = jfc.getSelectedFile();
                    files.add(new File(selectedFile.getAbsolutePath()));
                    String newText = "Current Files: \n";
                    for(File f: files){
                        newText = newText + f.getName() + "\n";
                    }
                    openedFiles.setText(newText);
                }
            }
        });

        optionMenu = new JComboBox(options);
        JLabel title1 = new JLabel("Please select your expression type:");
        JLabel title2 =new JLabel("Please add your files below:");

        /*optionList = new JComboBox(options);
        optionList2 = new JComboBox(options);
        input = new JTextField(20);
        output = new JTextField(20);
        compute = new JButton("Convert");*/
        //compute.addActionListener(this);

        Box expressions = Box.createHorizontalBox();
        expressions.add(Box.createVerticalStrut(20));
        expressions.add(title1);
        expressions.add(Box.createVerticalStrut(20));
        expressions.add(optionMenu);
        expressions.add(Box.createVerticalStrut(20));

        JButton clearFiles = new JButton("Clear Files");
        clearFiles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openedFiles.setText("Current Files: \n");
                files = new ArrayList<>();
            }
        });
        Box center = Box.createHorizontalBox();
        center.add(Box.createVerticalStrut(30));
        center.add(clearFiles);
        center.add(Box.createVerticalStrut(30));
        center.add(openFile);
        center.add(Box.createVerticalStrut(30));

        Box addingFiles = Box.createVerticalBox();
        //addingFiles.add(title2);
        //addingFiles.add(filesLabel);
        addingFiles.add(Box.createVerticalStrut(30));
        Box inside = Box.createHorizontalBox();
        inside.add(Box.createHorizontalStrut(30));
        inside.add(openedFiles);
        inside.add(Box.createHorizontalStrut(30));
        addingFiles.add(inside);
        addingFiles.add(Box.createVerticalStrut(30));
        addingFiles.add(center);
        addingFiles.add(Box.createVerticalStrut(30));

        JTextArea results = new JTextArea(12,5);
        JLabel title3 = new JLabel("Results:");
        JButton compute = new JButton("Compute Isotope Effects");
        Box top = Box.createHorizontalBox();
        top.add(Box.createHorizontalStrut(20));
        /*top.add(left);
        top.add(new JLabel("      =      "));
        top.add(right);*/
        top.add(compute);
        top.add(Box.createHorizontalStrut(20));

        Box lasttitle = Box.createHorizontalBox();
        //lasttitle .add(Box.createHorizontalStrut(30));
        lasttitle.add(title3);
        lasttitle.add(Box.createHorizontalStrut(300));

        Box resultWrap = Box.createHorizontalBox();
        resultWrap.add(Box.createHorizontalStrut(30));
        resultWrap.add(results);
        resultWrap.add(Box.createHorizontalStrut(30));

        Box main = Box.createVerticalBox();
        main.add(Box.createVerticalStrut(30));
        //main.add(Box.createVerticalStrut(80));
        main.add(expressions);
        //main.add(Box.createVerticalStrut(60));
        main.add(addingFiles);
        main.add(top);
        main.add(Box.createVerticalStrut(30));
        main.add(lasttitle);
        main.add(Box.createVerticalStrut(30));
        main.add(resultWrap);
        main.add(Box.createVerticalStrut(30));
        //main.add(Box.createVerticalStrut(40));

        panel.add(main);
        add(panel);
        compute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String expressionType = (String) optionMenu.getSelectedItem();

                if(expressionType.equals("EE (2 files)")){
                    if(files.size() != 2){
                        JOptionPane.showMessageDialog(null,
                                "Please upload exactly 2 files.",
                                "Invalid File Number",
                                JOptionPane.ERROR_MESSAGE);
                    } else{
                        //calculations here
                        openedFiles.setText("Current Files: \n");
                        files = new ArrayList<>();
                    }
                } else if (expressionType.equals("KIE (4 files)")) {
                    if(files.size() != 4){
                        JOptionPane.showMessageDialog(null,
                                "Please upload exactly 4 files.",
                                "Invalid File Number",
                                JOptionPane.ERROR_MESSAGE);
                    } else{
                        //calculations here
                        openedFiles.setText("Current Files: \n");
                        files = new ArrayList<>();

                    }
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Please select an expression type.",
                            "Invalid Expression Type",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

    }
}

