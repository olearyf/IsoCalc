import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class KIE {

    ArrayList<Double> tsH_freq, tsD_freq, rH_freq, rD_freq;
    ArrayList<Double> tsH_freqn, tsD_freqn, rH_freqn, rD_freqn;
    ArrayList<Double> tsH_eigenVals, tsD_eigenVals, rH_eigenVals, rD_eigenVals;
    ArrayList<Double> rD_div_rH, tsD_div_tsH;
    ArrayList<Double> rH_HZPEs, tsH_HZPEs, rD_HZPEs, tsD_HPZEs;
    ArrayList<Double> rh_rotTemps, tsh_rotTemps, rd_rotTemps, tsd_rotTemps;
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
    double rH_zpe, tsH_zpe, rD_zpe, tsD_zpe;
    double enthalpy_zpe;
    double enthalpy_vib;
    double thermal_enthalpy;
    double entropy_vib;
    double entropy_rot;
    double entropy_vib_plus_rot;


    private final double b1 = 6.6262 * Math.pow(10, -34);
    private final double b2 = 1.3807 * Math.pow(10, -23);
    private final double b3 = 2.9979 * Math.pow(10, 10);
    private final double b4 = 1.9872 * Math.pow(10, -3);
    private final double b5 = 4184.0;
    private final double b6 = 8.314;
    private final double b7 = 1.9872;
    private final double c18 = 298.15;
    private final double rsn_rh = 1;
    private final double rsn_tsh = 1;
    private final double rsn_rd = 1;
    private final double rsn_tsd = 1;

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

            this.rD_HZPEs = calcIndivHZPE(rD_freqn);
            this.tsD_HPZEs = calcIndivHZPE(tsD_freqn);
            this.rH_HZPEs = calcIndivHZPE(rH_freqn);
            this.tsH_HZPEs = calcIndivHZPE(tsH_freqn);

            this.rH_zpe = sum(rH_HZPEs);
            this.tsH_zpe = sum(tsH_HZPEs);
            this.rD_zpe = sum(rD_HZPEs);
            this.tsD_zpe = sum(tsD_HPZEs);

            this.enthalpy_zpe = (tsD_zpe - rD_zpe) - (tsH_zpe - rH_zpe);

            this.enthalpy_vib = calcEnthalpyVib(rH_freqn, tsH_freqn, rD_freqn, tsD_freqn);

            this.entropy_vib = calcTotalEntropyVib(rH_freqn, tsH_freqn, rD_freqn, tsD_freqn);

            this.rh_rotTemps = fm.getRotTemps(uR);
            this.tsh_rotTemps = fm.getRotTemps(uTS);
            this.rd_rotTemps = fm.getRotTemps(lR);
            this.tsd_rotTemps = fm.getRotTemps(lTS);

            this.entropy_rot = calcTotalEntropyRot(rh_rotTemps, tsh_rotTemps, rd_rotTemps, tsd_rotTemps);

            this.entropy_vib_plus_rot = entropy_rot + entropy_vib;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public double calcTotalEntropyRot(ArrayList<Double> rh, ArrayList<Double> tsh, ArrayList<Double> rd, ArrayList<Double> tsd){
        return (calcIndivEntropyRot(tsh, rsn_tsh) - calcIndivEntropyRot(rh, rsn_rh)) - (calcIndivEntropyRot(tsd, rsn_tsd) - calcIndivEntropyRot(rd, rsn_rd));
    }

    public double calcIndivEntropyRot(ArrayList<Double> temps, double rsn){
        System.out.println(b7 * (Math.log((Math.sqrt(Math.PI)) / rsn)*((Math.pow(c18, 1.5))/(Math.sqrt(temps.get(0) * temps.get(1) * temps.get(2))))+ 1.5));
        return b7 * (Math.log((Math.sqrt(Math.PI)) / rsn)*(Math.pow(c18, 1.5)/(Math.sqrt((temps.get(0) * temps.get(1) * temps.get(2)))))+ 1.5);
    }

    public double calcTotalEntropyVib(ArrayList<Double> rh, ArrayList<Double> tsh, ArrayList<Double> rd, ArrayList<Double> tsd){
        return (calcIndivEntropyVib(tsh) - calcIndivEntropyVib(rh)) - (calcIndivEntropyVib(tsd) - calcIndivEntropyVib(rd));
    }
    public double calcIndivEntropyVib(ArrayList<Double> freqs){
        ArrayList<Double> result = new ArrayList<>();
        for(int i = 0; i<freqs.size();i++){
            result.add(((b1*b3*freqs.get(i)) / (b2 * c18)));
        }
        ArrayList<Double> newRes = new ArrayList<>();
        for(Double r : result){
            newRes.add(b7 * (((r / (Math.exp(r) - 1))) - (Math.log(1 - Math.exp(-r)))));
        }
        return sum(newRes);
    }

    public double calcEnthalpyVib(ArrayList<Double> rh, ArrayList<Double> tsh, ArrayList<Double> rd, ArrayList<Double> tsd){
        return (calcIndivEnthalpyVib(tsd) - calcIndivEnthalpyVib(rd)) - (calcIndivEnthalpyVib(tsh) - calcIndivEnthalpyVib(rh));
    }

    public double calcIndivEnthalpyVib(ArrayList<Double> freqs){
        ArrayList<Double> result = new ArrayList<>();
        for(Double d : freqs){
            result.add(b4*((b1 * b3 * d) / (b2)) / (Math.exp(((b1 * b3 * d ) / (b2)) / c18)-1));
        }
        return sum(result);
    }

    public ArrayList<Double> calcIndivHZPE(ArrayList<Double> freqs){
        ArrayList<Double> result = new ArrayList<>();
        for( Double d: freqs){
            result.add(0.5*(b1 * b3 * d * b6) / (b2 * b5));
        }
        return result;
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

    public double getrD_zpe(){
        return rD_zpe;
    }

    public double getrH_zpe(){
        return rH_zpe;
    }

    public double getTsH_zpe(){
        return tsH_zpe;
    }

    public double getTsD_zpe(){
        return tsD_zpe;
    }

    public double getEnthalpy_zpe(){
        return enthalpy_zpe;
    }

    public double getEnthalpy_vib(){
        return enthalpy_vib;
    }

    public double getEntropy_vib(){
        return entropy_vib;
    }

    public double getEntropy_rot(){
        return entropy_rot;
    }

    public double getEntropy_vib_plus_rot(){
        return entropy_vib_plus_rot;
    }

    @Override
    public String toString() {
        String s = "KIE: " + String.format("%.5f", kie) + "\nKIE w/ VP(MMI): " + String.format("%.5f", vp_kie) + "\nMMI: " + String.format("%.5f", mmi) + "\nVP(MMI): " + String.format("%.5f", vpMMI) + "\nZPE: " + String.format("%.5f", zpe) + "\nEXC: " + String.format("%.5f", exc);
        return s;
    }
}
