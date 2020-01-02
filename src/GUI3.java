import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;

//add in javadoc
//look into running several tasks at once to speed it up
//valid file types = txt or log files or out

//add in input for temperature, rotational sym numbers
//change output text area to being immutable
//add in export to file button
//possibly partitiion results some how
//learn gridbag layout

public class GUI3 extends JFrame{

    ArrayList<File> files = new ArrayList<>();

    GUI3(){
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

        GridLayout experimentLayout = new GridLayout(4,3);
        experimentLayout.setHgap(10);
        experimentLayout.setVgap(10);
        JPanel fileStuff = new JPanel();
        fileStuff.setLayout(experimentLayout);
        fileStuff.add(uTS);
        fileStuff.add(openFile1);
        fileStuff.add(lTS);
        fileStuff.add(openFile2);
        fileStuff.add(uR);
        fileStuff.add(openFile3);
        fileStuff.add(lR);
        fileStuff.add(openFile4);

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

        Box main = Box.createVerticalBox();
        main.setBorder(new EmptyBorder(10, 10, 10, 10));
        main.add(fileStuff);
        main.add(Box.createVerticalStrut(30));
        main.add(top);
        main.add(lasttitle);
        main.add(Box.createVerticalStrut(30));
        main.add(resultWrap);
        main.add(Box.createVerticalStrut(30));

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
                    KIE kie = new KIE(files.get(0), files.get(1), files.get(2), files.get(3));
                    results.setText(kie.toString());
                    uTS.setText("Select Unlabeled TS File");
                    lTS.setText("Select Labeled TS File");
                    uR.setText("Select Unlabeled R File");
                    lR.setText("Select Labeled R File");
                    files = new ArrayList<>();

                }
            }
        });

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

