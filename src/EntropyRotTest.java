import java.util.ArrayList;

public class EntropyRotTest {

    private final double b1 = 6.6262 * Math.pow(10, -34);
    private final double b2 = 1.3807 * Math.pow(10, -23);
    private final double b3 = 2.9979 * Math.pow(10, 10);
    private final double b4 = 1.9872 * Math.pow(10, -3);
    private final double b5 = 4184.0;
    private final double b6 = 8.314;
    private final double b7 = 1.9872;
    private final double c18 = 298.15;
    private final int rsn_rh = 1;
    private final int rsn_tsh = 1;
    private final int rsn_rd = 1;
    private final int rsn_tsd = 1;

    public double calcTotalEntropyRot(ArrayList<Double> rh, ArrayList<Double> tsh, ArrayList<Double> rd, ArrayList<Double> tsd){
        return (calcIndivEntropyRot(tsh, rsn_tsh) - calcIndivEntropyRot(rh, rsn_rh)) - (calcIndivEntropyRot(tsd, rsn_tsd) - calcIndivEntropyRot(rd, rsn_rd));
    }

    public double calcIndivEntropyRot(ArrayList<Double> temps, double rsn){
        System.out.println(b7 * (Math.log((Math.sqrt(Math.PI)) / rsn)*((Math.pow(c18, 1.5))/(Math.sqrt(temps.get(0) * temps.get(1) * temps.get(2))))+ 1.5));
        return b7 * (Math.log((Math.sqrt(Math.PI)) / rsn)*(Math.pow(c18, 1.5)/(Math.sqrt((temps.get(0) * temps.get(1) * temps.get(2)))))+ 1.5);
    }
    public static void main(String[] args) {

    }
}
