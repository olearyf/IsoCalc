import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class FileTest {
    public static void main(String[] args) {
        FileManager fm = new FileManager();
        try {
            ArrayList<Double> myFrequencies = fm.getFrequencies(new File("C:\\Users\\fraol\\IdeaProjects\\IsoCalc\\Formamide_TS1labeled_Structure.log"));
            for (Double bd:myFrequencies) {
                System.out.println(bd);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
