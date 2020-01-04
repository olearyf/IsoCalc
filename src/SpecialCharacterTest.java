import javax.swing.*;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class SpecialCharacterTest {
    public static void main(String[] args) {
        PrintWriter printWriter = new PrintWriter(System.out,true);
        char aa = '\u03C3';
        printWriter.println("aa = " + aa);
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JLabel label = new JLabel("σ");


    }
}
