import java.io.File;
import java.util.ArrayList;

public class EIE {

    ArrayList<Double> rH_freq, rD_freq;
    ArrayList<Double> rH_freqn, rD_freqn;
    ArrayList<Double> trH_eigenVals, rD_eigenVals;
    ArrayList<Double> rD_div_rH;
    ArrayList<Double> rH_HZPEs, rD_HZPEs;
    ArrayList<Double> rh_rotTemps,  rd_rotTemps;
    double ixyz_rH, ixyz_rD;
    double mmi;
    double rDrH_totalProduct;
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
    private double c18;
    private int rsn_rh;
    private int rsn_rd;
    private double scalefactor;
    public EIE(File usg, File lgs, int rsn_ugs, int rsn_lgs, double temp, double scalefactor){

    }

}
