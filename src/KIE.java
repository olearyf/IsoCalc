import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class KIE {

    ArrayList<Double> tsH_freq, tsD_freq, rH_freq, rD_freq;
    ArrayList<Double> tsH_freqn, tsD_freqn, rH_freqn, rD_freqn;
    ArrayList<Double> tsH_eigenVals, tsD_eigenVals, rH_eigenVals, rD_eigenVals;
    ArrayList<Double> rD_div_rH, tsD_div_tsH;
    double ixyz_rH, ixyz_tsH, ixyz_rD, ixyz_tsD;
    double mmi;
    double rDrH_totalProduct, tsDtsH_totalProduct;
    double vpMMI;
    double zpeH, zpeRH, zpeD, zpeRD;
    double zpeH_div_zpeRH, zpeD_div_zpeRD;
    double zpe;
    double excH, excRH, excD, excRD;
    double excH_div_excRH, excD_div_excRD;
    double exc;
    double kie, vp_kie;

    private final double b1 = 6.6262 * Math.pow(10, -34);
    private final double b2 = 1.3807 * Math.pow(10, -23);
    private final double b3 = 2.9979 * Math.pow(10, 10);
    private final double b4 = 1.9872 * Math.pow(10, -3);
    private final double b5 = 4184.0;
    private final double b6 = 8.314;
    private final double b7 = 1.9872;
    private final double c18 = 298.15;

    public KIE(File uTS, File lTS, File uR, File lR){
        FileManager fm = new FileManager();
        try {
            this.tsH_freq = fm.getFrequencies(uTS);
            this.tsD_freq = fm.getFrequencies(lTS);
            this.rH_freq = fm.getFrequencies(uR);
            this.rD_freq = fm.getFrequencies(lR);

            this.tsH_freqn = removeNegatives(tsH_freq);
            this.tsD_freqn = removeNegatives(tsD_freq);
            this.rH_freqn = removeNegatives(rH_freq);
            this.rD_freqn = removeNegatives(rD_freq);

            this.tsH_eigenVals = fm.getXYZ(uTS);
            this.tsD_eigenVals = fm.getXYZ(lTS);
            this.rH_eigenVals = fm.getXYZ(uR);
            this.rD_eigenVals = fm.getXYZ(lR);

            this.ixyz_rH = rH_eigenVals.get(0)*rH_eigenVals.get(1)*rH_eigenVals.get(2);
            this.ixyz_tsH = tsH_eigenVals.get(0)*tsH_eigenVals.get(1)*tsH_eigenVals.get(2);
            this.ixyz_rD = rD_eigenVals.get(0)*rD_eigenVals.get(1)*rD_eigenVals.get(2);
            this.ixyz_tsD = tsD_eigenVals.get(0)*tsD_eigenVals.get(1)*tsD_eigenVals.get(2);

            this.mmi = Math.sqrt((ixyz_tsH*ixyz_rD) / (ixyz_tsD*ixyz_rH));

            this.rD_div_rH = calcDiv(rD_freq, rH_freq);
            this.tsD_div_tsH = calcDiv(tsD_freq, tsH_freq);
            this.rDrH_totalProduct = totalProduct(rD_div_rH);
            this.tsDtsH_totalProduct = totalProduct(tsD_div_tsH);

            this.vpMMI = rDrH_totalProduct / tsDtsH_totalProduct;

            this.zpeH = calcIndivZPE(tsH_freqn);
            this.zpeRH = calcIndivZPE(rH_freqn);
            this.zpeD = calcIndivZPE(tsD_freqn);
            this.zpeRD = calcIndivZPE(rD_freqn);

            this.zpeD_div_zpeRD = calcCombZPE(zpeD, zpeRD);
            this.zpeH_div_zpeRH = calcCombZPE(zpeH, zpeRH);
            this.zpe = zpeH_div_zpeRH / zpeD_div_zpeRD;

            this.excH = calcIndivEXC(tsH_freqn);
            this.excRH = calcIndivEXC(rH_freqn);
            this.excD = calcIndivEXC(tsD_freqn);
            this.excRD = calcIndivEXC(rD_freqn);

            this.excH_div_excRH = excH / excRH;
            this.excD_div_excRD = excD / excRD;
            this.exc = excH_div_excRH / excD_div_excRD;

            this.kie = mmi * zpe * exc;
            this.vp_kie = vpMMI * zpe * exc;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public double calcIndivEXC(ArrayList<Double> freqs){
        ArrayList<Double> result = new ArrayList<>();
        for(int i = 0; i<freqs.size();i++){
            result.add(1 / (1 - Math.exp(-1.0*((b1*b3*freqs.get(i)) / (b2 * c18)))));
        }
        return totalProduct(result);
    }

    public double calcCombZPE(double top, double bot){
        return (Math.exp(-0.5 * top) / Math.exp(-0.5 * bot));
    }
    public double sum(ArrayList<Double> mod){
        double sum = 0.0;
        for(int i = 0; i < mod.size(); i++){
            sum += mod.get(i);
        }
        return sum;
    }

    public double calcIndivZPE(ArrayList<Double> freqs){
        ArrayList<Double> result = new ArrayList<>();
        for(int i = 0; i<freqs.size();i++){
            result.add(((b1*b3*freqs.get(i)) / (b2 * c18)));
        }
        //System.out.println(freqs.get(0));
        //System.out.println(result.get(0));
        return sum(result);
    }

    public ArrayList<Double> removeNegatives(ArrayList<Double> mod){
        ArrayList<Double> result = new ArrayList<>();
        for(int i = 0; i < mod.size(); i++){
            if(mod.get(i) >= 0.0){
                result.add(mod.get(i));
            }
        }
        return result;
    }


    public double totalProduct(ArrayList<Double> vals){
        double prod = 1.0;
        for(int i = 0; i < vals.size(); i++){
            prod = prod*vals.get(i);
        }
        return prod;
    }

    public ArrayList<Double> calcDiv(ArrayList<Double> top, ArrayList<Double> bot){
        ArrayList<Double> result = new ArrayList<>();
        for(int i = 0; i < top.size();i++){
            result.add(top.get(i) / bot.get(i));
        }
        return result;
    }

    public double getIxyz_rH(){
        return ixyz_rH;
    }
    public double getIxyz_tsH(){
        return ixyz_tsH;
    }

    public double getIxyz_rD(){
        return ixyz_rD;
    }

    public double getIxyz_tsD(){
        return ixyz_tsD;
    }

    public double getMmi(){
        return mmi;
    }

    public double getrDrH_totalProduct(){
        return rDrH_totalProduct;
    }

    public double getTsDtsH_totalProduct(){
        return tsDtsH_totalProduct;
    }

    public double getVpMMI(){
        return vpMMI;
    }

    public double getZpeH_div_zpeRH(){
        return zpeH_div_zpeRH;
    }


    public double getZpeD_div_zpeRD(){
        return zpeD_div_zpeRD;
    }

    public double getZpe(){
        return zpe;
    }
    public double getExc(){
        return exc;
    }
    public double getKie(){
        return kie;
    }
    public double getVp_kie(){
        return vp_kie;
    }

    @Override
    public String toString() {
        String s = "KIE: " + String.format("%.5f", kie) + "\nKIE w/ VP(MMI): " + String.format("%.5f", vp_kie) + "\nMMI: " + String.format("%.5f", mmi) + "\nVP(MMI): " + String.format("%.5f", vpMMI) + "\nZPE: " + String.format("%.5f", zpe) + "\nEXC: " + String.format("%.5f", exc);
        return s;
    }
}
