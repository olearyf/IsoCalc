import java.io.*;
import java.util.ArrayList;

public class FileManager {

    public FileManager() {
    }

    public ArrayList<Double> getFrequencies(File f) throws FileNotFoundException{
        if (!f.exists()) {
            throw new FileNotFoundException();
        }
        FileReader frI = null;
        BufferedReader brI = null;
        try {
            frI = new FileReader(f);
            brI = new BufferedReader(frI);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<String> contents = new ArrayList<String>();
        while (true) {
            String line = null;
            try {
                line = brI.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (line == null) {
                break;
            }
            if (line.contains("Frequencies")) {
                contents.add(line);
            }
        }
        ArrayList<Double> freqs = new ArrayList<>();
        for (String s : contents) {
            int count = 0;
            for(int i = 0; i<s.length();i++){
                char c = s.charAt(i);
                if(Character.isDigit(c) && s.charAt(i-1) == ' '){
                    if(count == 2){
                        double newf = Double.valueOf(s.substring(i));
                        freqs.add(newf);
                    } else {
                        double newf = Double.valueOf(s.substring(i, s.indexOf(' ', i + 1)));
                        freqs.add(newf);
                    }
                    count++;
                }
                if(Character.isDigit(c) && s.charAt(i-1) == '-'){
                    if(count == 2){
                        double newf = Double.valueOf(s.substring(i));
                        freqs.add(newf);
                    } else {
                        double newf = Double.valueOf(s.substring(i-1, s.indexOf(' ', i + 1)));
                        freqs.add(newf);
                    }
                    count++;
                }
            }
        }
        return freqs;
    }

    public ArrayList<Double> getXYZ(File f) throws FileNotFoundException{
        if (!f.exists()) {
            throw new FileNotFoundException();
        }
        FileReader frI = null;
        BufferedReader brI = null;
        try {
            frI = new FileReader(f);
            brI = new BufferedReader(frI);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int n = 0;
        ArrayList<String> contents = new ArrayList<String>();
        while (true) {
            String line = null;
            try {
                line = brI.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (line == null) {
                break;
            }
            if (line.contains("Eigenvalues --")) {
                contents.add(line);
                n++;
            }
            if(n != 0){
                break;
            }
        }
        ArrayList<Double> xyz = new ArrayList<>();
        for (String s : contents) {
            int count = 0;
            for(int i = 0; i<s.length();i++){
                char c = s.charAt(i);
                if(Character.isDigit(c) && s.charAt(i-1) == ' '){
                    if(count == 2){
                        double newf = Double.valueOf(s.substring(i));
                        xyz.add(newf);
                    } else {
                        double newf = Double.valueOf(s.substring(i, s.indexOf(' ', i + 1)));
                        xyz.add(newf);
                    }
                    count++;
                }
            }
        }
        ArrayList<Double> values_3 = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            values_3.add(xyz.get(i));
        }
        return values_3;
    }

}
