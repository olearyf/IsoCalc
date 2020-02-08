import java.io.*;
import java.util.ArrayList;

/**
 * FileManager
 *
 * Extracts information from log/txt files
 *
 *
 * @author Frances O'Leary
 * @version 01-07-2020
 *
 */

public class FileManager {

    public FileManager() {
    }

    public ArrayList<Double> getFrequencies(File f, double scale) throws FileNotFoundException{
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
                        double newf = Double.valueOf(s.substring(i)) * scale;
                        freqs.add(newf);
                    } else {
                        double newf = Double.valueOf(s.substring(i, s.indexOf(' ', i + 1)))*scale;
                        freqs.add(newf);
                    }
                    count++;
                }
                if(Character.isDigit(c) && s.charAt(i-1) == '-'){
                    if(count == 2){
                        double newf = Double.valueOf(s.substring(i))*scale;
                        freqs.add(newf);
                    } else {
                        double newf = Double.valueOf(s.substring(i-1, s.indexOf(' ', i + 1)))*scale;
                        freqs.add(newf);
                    }
                    count++;
                }
            }
        }
        return freqs;
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

    public int getRotSym(File f) throws FileNotFoundException {
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
            if (line.contains("Rotational symmetry number")) {
                contents.add(line);
                n++;
            }
            if(n != 0){
                break;
            }
        }
        for (String s : contents) {
            int count = 0;
            for(int i = 0; i<s.length();i++){
                char c = s.charAt(i);
                if(Character.isDigit(c)){
                    return Integer.valueOf(c + "");
                }
            }
        }
        return 0;
    }

    public ArrayList<Double> getRotTemps(File f) throws FileNotFoundException{
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
            if (line.contains("Rotational temperatures (Kelvin)")) {
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
        boolean blank = false;
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
                if(c == '*'){
                    blank = true;
                    break;
                }
            }
        }
        ArrayList<Double> values_3 = new ArrayList<>();
        if(blank) {
            //divide each rotational temp by this constant and return
            ArrayList<Double> temps = getRotTemps(f);
            for(Double d : temps){
                values_3.add(1804.707 / d);
            }
        } else {

            for (int i = 0; i < 3; i++) {
                values_3.add(xyz.get(i));
            }
        }
        return values_3;
    }


}
