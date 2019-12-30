import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GUI extends JFrame{

    String[] options = {"--Select One--", "EE (2 files)", "KIE (4 files)"};
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
        addingFiles.add(Box.createVerticalStrut(30));
        Box inside = Box.createHorizontalBox();
        inside.add(Box.createHorizontalStrut(30));
        inside.add(openedFiles);
        inside.add(Box.createHorizontalStrut(30));
        addingFiles.add(inside);
        addingFiles.add(Box.createVerticalStrut(30));
        addingFiles.add(center);
        addingFiles.add(Box.createVerticalStrut(30));


        JLabel uTS = new JLabel("Select Unlabeled TS File");
        JLabel lTS = new JLabel("Select Labeled TS File");
        JLabel uR = new JLabel("Select Unlabeled R File");
        JLabel lR = new JLabel("Select Labeled R File");

        JButton openFile1 = new JButton("Add File");
        openFile1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnValue = jfc.showOpenDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = jfc.getSelectedFile();
                    files.add(0, new File(selectedFile.getAbsolutePath()));
                    uTS.setText("UTS File: " + files.get(0).getName());
                }
            }
        });
        JButton openFile2 = new JButton("Add File");
        openFile2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnValue = jfc.showOpenDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = jfc.getSelectedFile();
                    files.add(1, new File(selectedFile.getAbsolutePath()));
                    lTS.setText("LTS File: " + files.get(1).getName());
                }
            }
        });
        JButton openFile3 = new JButton("Add File");
        openFile3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnValue = jfc.showOpenDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = jfc.getSelectedFile();
                    files.add(2, new File(selectedFile.getAbsolutePath()));
                    uR.setText("UR File: " + files.get(2).getName());
                }
            }
        });
        JButton openFile4 = new JButton("Add File");
        openFile4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnValue = jfc.showOpenDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = jfc.getSelectedFile();
                    files.add(3, new File(selectedFile.getAbsolutePath()));
                    lR.setText("LR File: " + files.get(3).getName());
                }
            }
        });



        Box file1 = Box.createHorizontalBox();
        file1.add(Box.createHorizontalGlue());
        file1.add(Box.createHorizontalStrut(30));
        file1.add(uTS);
        file1.add(Box.createHorizontalStrut(30));
        file1.add(openFile1);
        file1.add(Box.createHorizontalStrut(70));
        //file1.add(Box.createHorizontalGlue());

        Box file2 = Box.createHorizontalBox();
        file2.add(Box.createHorizontalGlue());
        file2.add(Box.createHorizontalStrut(30));
        file2.add(lTS);
        file2.add(Box.createHorizontalStrut(30));
        file2.add(openFile2);
        file2.add(Box.createHorizontalStrut(70));
        //file2.add(Box.createHorizontalGlue());

        Box file3 = Box.createHorizontalBox();
        file3.add(Box.createHorizontalGlue());
        file3.add(Box.createHorizontalStrut(30));
        file3.add(uR);
        file3.add(Box.createHorizontalStrut(30));
        file3.add(openFile3);
        file3.add(Box.createHorizontalStrut(70));
        //file3.add(Box.createHorizontalGlue());

        Box file4 = Box.createHorizontalBox();
        file4.add(Box.createHorizontalGlue());
        file4.add(Box.createHorizontalStrut(30));
        file4.add(lR);
        file4.add(Box.createHorizontalStrut(30));
        file4.add(openFile4);
        file4.add(Box.createHorizontalStrut(70));
        //file4.add(Box.createHorizontalGlue());

        Box fileStuff = Box.createVerticalBox();
        fileStuff.add(Box.createVerticalStrut(30));
        fileStuff.add(file1);
        fileStuff.add(Box.createVerticalStrut(10));
        fileStuff.add(file2);
        fileStuff.add(Box.createVerticalStrut(10));
        fileStuff.add(file3);
        fileStuff.add(Box.createVerticalStrut(10));
        fileStuff.add(file4);
        fileStuff.add(Box.createVerticalStrut(30));

        /*Box fileStuff = Box.createHorizontalBox();
        fileStuff.add(fileLabels);
        //fileStuff.add(Box.createHorizontalStrut(50));
        fileStuff.add(fileButtons);*/

        JTextArea results = new JTextArea(12,5);
        JLabel title3 = new JLabel("Results:");
        JButton compute = new JButton("Compute Isotope Effects");
        Box top = Box.createHorizontalBox();
        top.add(Box.createHorizontalStrut(20));
        top.add(compute);
        top.add(Box.createHorizontalStrut(20));

        Box lasttitle = Box.createHorizontalBox();
        lasttitle.add(title3);
        lasttitle.add(Box.createHorizontalStrut(300));

        Box resultWrap = Box.createHorizontalBox();
        resultWrap.add(Box.createHorizontalStrut(30));
        resultWrap.add(results);
        resultWrap.add(Box.createHorizontalStrut(30));

        Box main = Box.createVerticalBox();
        main.add(Box.createVerticalStrut(30));
        main.add(expressions);
        //main.add(addingFiles);
        main.add(fileStuff);
        main.add(top);
        main.add(Box.createVerticalStrut(30));
        main.add(lasttitle);
        main.add(Box.createVerticalStrut(30));
        main.add(resultWrap);
        main.add(Box.createVerticalStrut(30));

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
                        uTS.setText("Select Unlabeled TS File");
                        lTS.setText("Select Labeled TS File");
                        uR.setText("Select Unlabeled R File");
                        lR.setText("Select Labeled R File");
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
                        KIE kie = new KIE(files.get(0), files.get(1), files.get(2), files.get(3));
                        results.setText(kie.toString());
                        uTS.setText("Select Unlabeled TS File");
                        lTS.setText("Select Labeled TS File");
                        uR.setText("Select Unlabeled R File");
                        lR.setText("Select Labeled R File");
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

