import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class EIEGUI extends JPanel {

    ArrayList<File> files = new ArrayList<>();
    EIE eie;
    String filePath;

    public EIEGUI(){

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
        JLabel uR = new JLabel("Select Unlabeled Ground State File");
        JLabel lR = new JLabel("Select Labeled Ground State File");

        JButton clearFiles = new JButton("Clear Files");
        clearFiles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                uR.setText("Select Unlabeled Ground State File");
                lR.setText("Select Labeled Ground State File");
                files = new ArrayList<>();
            }
        });
        JButton openFile3 = new JButton("Add File");
        openFile3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnValue = jfc.showOpenDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = jfc.getSelectedFile();
                    files.add(0, new File(selectedFile.getAbsolutePath()));
                    uR.setText("UGS File: " + files.get(2).getName());
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
                    files.add(1, new File(selectedFile.getAbsolutePath()));
                    lR.setText("LGS File: " + files.get(3).getName());
                }
            }
        });

        GridLayout experimentLayout1 = new GridLayout(2,2);
        experimentLayout1.setHgap(10);
        experimentLayout1.setVgap(10);
        JPanel entryStuff = new JPanel();
        JLabel set3 = new JLabel("Sym. # σ: ");
        JLabel set4 = new JLabel("Sym. # σ: ");
        JButton viewFile3 = new JButton("View File");
        JButton viewFile4 = new JButton("View File");

        Desktop desktop1 = Desktop.getDesktop();

        viewFile3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!(files.size() >= 3)){
                    JOptionPane.showMessageDialog(null,
                            "Please upload a file first.",
                            "File Not Uploaded",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        desktop1.open(files.get(2));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        viewFile4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!(files.size() >= 4)){
                    JOptionPane.showMessageDialog(null,
                            "Please upload a file first.",
                            "File Not Uploaded",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        desktop1.open(files.get(3));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        JTextField text3 = new JTextField("1");
        JTextField text4 = new JTextField("1");
        entryStuff.setLayout(experimentLayout1);
        entryStuff.add(set3);
        entryStuff.add(text3);
        entryStuff.add(openFile3);
        entryStuff.add(viewFile3);
        entryStuff.add(set4);
        entryStuff.add(text4);
        entryStuff.add(openFile4);
        entryStuff.add(viewFile4);

        GridLayout experimentLayout4 = new GridLayout(2,1);
        experimentLayout4.setHgap(10);
        experimentLayout4.setVgap(10);
        JPanel setSets = new JPanel();

        GridLayout experimentLayout6 = new GridLayout(1,2);
        experimentLayout6.setHgap(10);
        experimentLayout6.setVgap(10);
        JPanel entryStuff1 = new JPanel();
        entryStuff1.setLayout(experimentLayout6);
        entryStuff1.add(setSets);
        entryStuff1.add(entryStuff);

        GridLayout experimentLayout2 = new GridLayout(2,1);
        experimentLayout2.setHgap(10);
        experimentLayout2.setVgap(10);
        JPanel fileLabels = new JPanel();
        fileLabels.setLayout(experimentLayout2);
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
        results.setEditable(false);
        JTextArea results2 = new JTextArea(12,5);
        results2.setEditable(false);
        JLabel title3 = new JLabel("Results:");
        JButton compute = new JButton("Compute Isotope Effects");
        Box top = Box.createHorizontalBox();
        top.add(Box.createHorizontalStrut(20));
        top.add(compute);
        top.add(Box.createHorizontalStrut(20));


        Box lasttitle = Box.createHorizontalBox();
        lasttitle.add(title3);
        lasttitle.add(Box.createHorizontalGlue());

        Box result1Pan = Box.createVerticalBox();
        JLabel bg = new JLabel("Bigeleisen-Mayer EIE Calculation:");
        Box r1 = Box.createHorizontalBox();
        r1.add(bg);
        r1.add(Box.createHorizontalGlue());
        result1Pan.add(r1);
        result1Pan.add(Box.createVerticalStrut(10));
        result1Pan.add(results);

        Box result2Pan = Box.createVerticalBox();
        JLabel ee = new JLabel("Enthalpy-Entropy EIE Calculation:");
        Box r2 = Box.createHorizontalBox();
        r2.add(ee);
        r2.add(Box.createHorizontalGlue());
        result2Pan.add(r2);
        result2Pan.add(Box.createVerticalStrut(10));
        result2Pan.add(results2);

        Box resultWrap = Box.createHorizontalBox();
        resultWrap.add(Box.createHorizontalStrut(10));
        resultWrap.add(result1Pan);
        resultWrap.add(Box.createHorizontalStrut(10));
        resultWrap.add(result2Pan);
        resultWrap.add(Box.createHorizontalStrut(10));

        Box center1 = Box.createHorizontalBox();
        center1.add(Box.createVerticalStrut(30));
        center1.add(clearFiles);
        center1.add(Box.createVerticalStrut(30));
        center1.add(compute);
        center1.add(Box.createVerticalStrut(30));

        JLabel setTemp = new JLabel("Set Temperature(K): ");
        JTextField temp = new JTextField("298.15");

        JLabel setScale = new JLabel("Set Scaling Factor: ");
        JTextField scalefactor = new JTextField("1.0");


        Box center2 = Box.createHorizontalBox();
        center2.add(Box.createHorizontalStrut(150));
        center2.add(setScale);
        center2.add(Box.createHorizontalStrut(20));
        center2.add(scalefactor);
        center2.add(Box.createHorizontalStrut(50));
        center2.add(setTemp);
        center2.add(Box.createHorizontalStrut(20));
        center2.add(temp);
        center2.add(Box.createHorizontalStrut(200));

        GridLayout experimentLayout5 = new GridLayout(2,1);
        experimentLayout5.setHgap(10);
        experimentLayout5.setVgap(10);
        JPanel center = new JPanel();
        center.setLayout(experimentLayout5);
        center.add(center2);
        center.add(center1);

        JButton export = new JButton("Export as File");
        JButton exportTempRange = new JButton("Export with Temperature Range");
        Box center3 = Box.createHorizontalBox();
        center3.add(Box.createHorizontalStrut(30));
        center3.add(export);
        center3.add(Box.createHorizontalStrut(30));
        center3.add(exportTempRange);
        center3.add(Box.createHorizontalStrut(30));

        export.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(eie == null || filePath == null){
                    JOptionPane.showMessageDialog(null,
                            "Please generate your results first.",
                            "EIE not initialized.",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    String m = JOptionPane.showInputDialog(null, "Please enter the new file name(with '.txt'):",
                            "Export File Destination", JOptionPane.QUESTION_MESSAGE);
                    File save = new File(filePath + m);
                    FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(save, true);
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }
                    PrintWriter pw = new PrintWriter(fos);
                    pw.println(eie);
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
            }

        });


        //do
        exportTempRange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(eie == null || filePath == null){
                    JOptionPane.showMessageDialog(null,
                            "Please generate your results first.",
                            "EIE not initialized.",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    String m = JOptionPane.showInputDialog(null, "Please enter the new file name(with '.txt'): ",
                            "Export File Destination", JOptionPane.QUESTION_MESSAGE);
                    String beginTemp = JOptionPane.showInputDialog(null, "Please enter the starting temperature(K): ",
                            "Export File Destination", JOptionPane.QUESTION_MESSAGE);
                    String endTemp = JOptionPane.showInputDialog(null, "Please enter the ending temperature(K): ",
                            "Export File Destination", JOptionPane.QUESTION_MESSAGE);
                    String tempInterval = JOptionPane.showInputDialog(null, "Please enter the temperature interval: ",
                            "Export File Destination", JOptionPane.QUESTION_MESSAGE);
                    double btem = getTemp(beginTemp);
                    double etem = getTemp(endTemp);
                    double ti = getTemp(tempInterval);
                    ArrayList<EIE> eies = new ArrayList<>();
                    File save = new File(filePath + m);
                    FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(save, true);
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }
                    PrintWriter pw = new PrintWriter(fos);
                    for(EIE n : eies){
                        pw.println(n);
                    }
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
            }
        });

        TitledBorder title1 = BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Upload Components: ");
        TitledBorder title2 = BorderFactory.createTitledBorder(BorderFactory.createLoweredBevelBorder(), "Results: ");
        title1.setTitlePosition(TitledBorder.ABOVE_TOP);
        title2.setTitlePosition(TitledBorder.ABOVE_TOP);

        Box main = Box.createVerticalBox();
        main.setBorder(new EmptyBorder(10, 10, 10, 10));
        main.add(fileStuff);
        main.add(Box.createVerticalStrut(10));
        main.add(center);
        main.setBorder(title1);

        Box main2 = Box.createVerticalBox();
        main2.setBorder(new EmptyBorder(10, 10, 10, 10));
        main2.add(Box.createVerticalStrut(15));
        main2.add(resultWrap);
        main2.add(Box.createVerticalStrut(15));
        main2.add(center3);
        main2.add(Box.createVerticalStrut(10));
        main2.setBorder(title2);
        Box main3 = Box.createVerticalBox();
        main3.setBorder(new EmptyBorder(5, 5, 5, 5));
        main3.add(main);
        main3.add(Box.createVerticalStrut(10));
        main3.add(main2);

        //setBorder(new EmptyBorder(5, 5, 5, 5));
        add(main3);
        compute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(files.size() != 2){
                    JOptionPane.showMessageDialog(null,
                            "Please upload exactly 2 files.",
                            "Invalid File Number",
                            JOptionPane.ERROR_MESSAGE);
                } else{
                            if(isValidRSN(text3.getText())){
                                //kie.setRsn_rh(getRSN(text3.getText()));
                                if(isValidRSN(text4.getText())){
                                    //kie.setRsn_rd(getRSN(text4.getText()));
                                    if(isValidTemp(temp.getText())){
                                        if(isValidTemp(scalefactor.getText())) {
                                            //kie = new KIE(files.get(0), files.get(1), files.get(2), files.get(3), getRSN(text1.getText()), getRSN(text2.getText()), getRSN(text3.getText()), getRSN(text4.getText()), getTemp(temp.getText()), getTemp(scalefactor.getText()));
                                            eie = new EIE(files.get(0), files.get(1), getRSN(text3.getText()), getRSN(text4.getText()), getTemp(temp.getText()), getTemp(scalefactor.getText()));
                                            //results.setText(kie.getBM());
                                            //results.setEditable(false);
                                            //results2.setText(kie.getEE());
                                            //results2.setEditable(false);
                                        } else {
                                            JOptionPane.showMessageDialog(null,
                                                    "Please upload a valid scale factor.",
                                                    "Invalid Scale Factor",
                                                    JOptionPane.ERROR_MESSAGE);
                                        }
                                    } else{
                                        JOptionPane.showMessageDialog(null,
                                                "Please upload a valid temperature.",
                                                "Invalid Temperature",
                                                JOptionPane.ERROR_MESSAGE);
                                    }

                                } else {
                                    JOptionPane.showMessageDialog(null,
                                            "Please upload a valid LGS RSN.",
                                            "Invalid RSN",
                                            JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        "Please upload a valid UGS RSN.",
                                        "Invalid RSN",
                                        JOptionPane.ERROR_MESSAGE);
                            }
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

}
