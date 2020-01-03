import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

//add in javadoc
//look into running several tasks at once to speed it up
//valid file types = txt or log files or out

//add in input for temperature, rotational sym numbers
//change output text area to being immutable
//add in export to file button
//possibly partitiion results some how
//learn gridbag layout

public class GUI4 extends JFrame{

    ArrayList<File> files = new ArrayList<>();
    KIE kie;
    String filePath;

    GUI4(){
        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel panel1 = new JPanel(false);
        JLabel filler = new JLabel("KIE (4 files)");
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel1.setLayout(new GridLayout(1, 1));
        panel1.add(filler);
        tabbedPane.addTab("KIE", null, panel1,
                "4 Files");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        JPanel panel2 = new JPanel(false);
        JLabel filler2 = new JLabel("EIE");
        filler2.setHorizontalAlignment(JLabel.CENTER);
        panel2.setLayout(new GridLayout(1, 1));
        panel2.add(filler);
        tabbedPane.addTab("EIE", null, panel2,
                "2 Files");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setTitle("IsoCalc");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int h = (int)screenSize.getHeight();
        int w = (int)screenSize.getWidth();
        setLocation(w/2-400, h/2-300);
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

        JButton clearFiles = new JButton("Clear Files");
        clearFiles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openedFiles.setText("Current Files: \n");
                files = new ArrayList<>();
            }
        });


        /*Box addingFiles = Box.createVerticalBox();
        addingFiles.add(Box.createVerticalStrut(30));
        Box inside = Box.createHorizontalBox();
        inside.add(Box.createHorizontalStrut(30));
        inside.add(openedFiles);
        inside.add(Box.createHorizontalStrut(30));
        addingFiles.add(inside);
        addingFiles.add(Box.createVerticalStrut(30));
        addingFiles.add(center);
        addingFiles.add(Box.createVerticalStrut(30));*/


        JLabel uTS = new JLabel("Select Unlabeled TS File");
        JLabel lTS = new JLabel("Select Labeled TS File");
        JLabel uR = new JLabel("Select Unlabeled R File");
        JLabel lR = new JLabel("Select Labeled R File");

        //make simpler with lists
        JButton openFile1 = new JButton("Add File");
        openFile1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnValue = jfc.showOpenDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = jfc.getSelectedFile();
                    files.add(0, new File(selectedFile.getAbsolutePath()));
                    uTS.setText("UTS File: " + files.get(0).getName());
                    int n = files.get(0).getName().length();
                    filePath = selectedFile.getAbsolutePath().substring(0, selectedFile.getAbsolutePath().length()-n);
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

        GridLayout experimentLayout1 = new GridLayout(4,3);
        experimentLayout1.setHgap(10);
        experimentLayout1.setVgap(10);
        JPanel entryStuff = new JPanel();
        JLabel set1 = new JLabel("Set UTS RSN: ");
        JLabel set2 = new JLabel("Set LTS RSN: ");
        JLabel set3 = new JLabel("Set UR RSN: ");
        JLabel set4 = new JLabel("Set LR RSN: ");
        JTextField text1 = new JTextField("1");
        JTextField text2 = new JTextField("1");
        JTextField text3 = new JTextField("1");
        JTextField text4 = new JTextField("1");
        entryStuff.setLayout(experimentLayout1);
        entryStuff.add(set1);
        entryStuff.add(text1);
        entryStuff.add(openFile1);
        entryStuff.add(set2);
        entryStuff.add(text2);
        entryStuff.add(openFile2);
        entryStuff.add(set3);
        entryStuff.add(text3);
        entryStuff.add(openFile3);
        entryStuff.add(set4);
        entryStuff.add(text4);
        entryStuff.add(openFile4);

        GridLayout experimentLayout2 = new GridLayout(4,1);
        experimentLayout2.setHgap(10);
        experimentLayout2.setVgap(10);
        JPanel fileLabels = new JPanel();
        fileLabels.setLayout(experimentLayout2);
        fileLabels.add(uTS);
        fileLabels.add(lTS);
        fileLabels.add(uR);
        fileLabels.add(lR);

        GridLayout experimentLayout = new GridLayout(1,2);
        experimentLayout.setHgap(10);
        experimentLayout.setVgap(10);
        JPanel fileStuff = new JPanel();
        fileStuff.setLayout(experimentLayout);
        fileStuff.add(fileLabels);
        fileStuff.add(entryStuff);

        JTextArea results = new JTextArea(12,5);
        JLabel title3 = new JLabel("Results:");
        JButton compute = new JButton("Compute Isotope Effects");
        Box top = Box.createHorizontalBox();
        top.add(Box.createHorizontalStrut(20));
        top.add(compute);
        top.add(Box.createHorizontalStrut(20));

        Box lasttitle = Box.createHorizontalBox();
        lasttitle.add(title3);
        lasttitle.add(Box.createHorizontalGlue());

        Box resultWrap = Box.createHorizontalBox();
        resultWrap.add(Box.createHorizontalStrut(30));
        resultWrap.add(results);
        resultWrap.add(Box.createHorizontalStrut(30));

        Box center1 = Box.createHorizontalBox();
        center1.add(Box.createVerticalStrut(30));
        center1.add(clearFiles);
        center1.add(Box.createVerticalStrut(30));
        center1.add(compute);
        center1.add(Box.createVerticalStrut(30));

        JLabel setTemp = new JLabel("Set Temperature(K): ");
        JTextField temp = new JTextField("298.15");

        Box center2 = Box.createHorizontalBox();
        center2.add(Box.createHorizontalStrut(275));
        center2.add(setTemp);
        center2.add(Box.createHorizontalStrut(10));
        center2.add(temp);
        center2.add(Box.createHorizontalStrut(300));

        GridLayout experimentLayout5 = new GridLayout(2,1);
        experimentLayout5.setHgap(10);
        experimentLayout5.setVgap(10);
        JPanel center = new JPanel();
        center.setLayout(experimentLayout5);
        //Box center = Box.createHorizontalBox();
        //center.add(Box.createVerticalStrut(30));
        center.add(center2);
        center.add(center1);
        //center.add(Box.createVerticalStrut(30));

        JButton export = new JButton("Export as File");
        Box center3 = Box.createHorizontalBox();
        center3.add(Box.createHorizontalStrut(30));
        center3.add(export);
        center3.add(Box.createHorizontalStrut(30));

        //needs work
        export.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    String m = JOptionPane.showInputDialog(null, "Please enter the new file name(with '.txt'):",
                            "Export File Destination", JOptionPane.QUESTION_MESSAGE);
                    File save = new File(filePath + m);
                    if(kie == null || filePath == null){
                        JOptionPane.showMessageDialog(null,
                                "Please generate your results first.",
                                "KIE not initialized.",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(save, true);
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }
                    PrintWriter pw = new PrintWriter(fos);
                    pw.println(kie);
                    pw.close();
                    try {
                        fos.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.open(save);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        });


        Box main = Box.createVerticalBox();
        main.setBorder(new EmptyBorder(10, 10, 10, 10));
        main.add(fileStuff);
        main.add(Box.createVerticalStrut(10));
        main.add(center);
        main.add(lasttitle);
        main.add(Box.createVerticalStrut(15));
        main.add(resultWrap);
        main.add(Box.createVerticalStrut(15));
        main.add(center3);
        main.add(Box.createVerticalStrut(10));

        panel1.add(main);
        add(tabbedPane);
        compute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(files.size() != 4){
                    JOptionPane.showMessageDialog(null,
                            "Please upload exactly 4 files.",
                            "Invalid File Number",
                            JOptionPane.ERROR_MESSAGE);
                } else{

                    if(isValidRSN(text1.getText())){
                        //kie.setRsn_tsh(getRSN(text1.getText()));
                        if(isValidRSN(text2.getText())){
                            //kie.setRsn_tsd(getRSN(text2.getText()));
                            if(isValidRSN(text3.getText())){
                                //kie.setRsn_rh(getRSN(text3.getText()));
                                if(isValidRSN(text4.getText())){
                                    //kie.setRsn_rd(getRSN(text4.getText()));
                                    if(isValidTemp(temp.getText())){
                                        kie = new KIE(files.get(0), files.get(1), files.get(2), files.get(3), getRSN(text1.getText()), getRSN(text2.getText()), getRSN(text3.getText()), getRSN(text4.getText()), getTemp(temp.getText()));
                                        results.setText(kie.toString());
                                        results.setEditable(false);
                                    } else{
                                        JOptionPane.showMessageDialog(null,
                                                "Please upload a valid temperature.",
                                                "Invalid Temperature",
                                                JOptionPane.ERROR_MESSAGE);
                                    }

                                } else {
                                    JOptionPane.showMessageDialog(null,
                                            "Please upload a valid LR RSN.",
                                            "Invalid RSN",
                                            JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        "Please upload a valid UR RSN.",
                                        "Invalid RSN",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "Please upload a valid LTS RSN.",
                                    "Invalid RSN",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Please upload a valid UTS RSN.",
                                "Invalid RSN",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    //KIE kie = new KIE(files.get(0), files.get(1), files.get(2), files.get(3));

                    /*uTS.setText("Select Unlabeled TS File");
                    lTS.setText("Select Labeled TS File");
                    uR.setText("Select Unlabeled R File");
                    lR.setText("Select Labeled R File");
                    files = new ArrayList<>();*/

                }
            }
        });

    }
    public boolean isValidRSN(String s){
        s = s.strip();
        //System.out.println(s);
        try{
            int n = Integer.valueOf(s);
            //System.out.println(n);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
    public boolean isValidTemp(String s){
        s = s.strip();
        //System.out.println(s);
        try{
            double n = Double.valueOf(s);
            if(n < 0.0){
                return false;
            }
            //System.out.println(n);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

    public int getRSN(String s){
        s = s.strip();
        int n = Integer.valueOf(s);
        return n;
    }

    public double getTemp(String s){
        s = s.strip();
        double n = Double.valueOf(s);
        return n;
    }

    protected Component makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }
}

