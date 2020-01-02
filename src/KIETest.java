import java.io.File;
import java.util.ArrayList;

public class KIETest {

    public static void main(String[] args) {
        File one = new File("C:\\Users\\fraol\\IdeaProjects\\IsoCalc\\Formamide_TS1unlabeled_Structure.log");
        File two = new File("C:\\Users\\fraol\\IdeaProjects\\IsoCalc\\Formamide_TS1labeled_Structure.log");
        File three = new File("C:\\Users\\fraol\\IdeaProjects\\IsoCalc\\Formamide_GSunlabeled_Structure.log");
        File four = new File("C:\\Users\\fraol\\IdeaProjects\\IsoCalc\\Formamide_GSlabeled_Structure.log");
        KIE kie = new KIE(one, two, three, four);
        /*System.out.println(kie.getIxyz_rH());
        System.out.println(kie.getIxyz_tsH());
        System.out.println(kie.getIxyz_rD());
        System.out.println(kie.getIxyz_tsD());
        System.out.println(kie.getMmi());*/
        //System.out.println(kie.getrDrH_totalProduct());
        //System.out.println(kie.getTsDtsH_totalProduct());
        /*System.out.println(kie.tsD_div_tsH.get(0));
        System.out.println(kie.tsD_div_tsH.get(kie.tsD_div_tsH.size()-1));

        System.out.println("tsd");
        System.out.println(kie.tsD_freq.get(0));
        System.out.println(kie.tsD_freq.get(kie.tsD_freq.size()-1));
        System.out.println("tsh");
        System.out.println(kie.tsH_freq.get(0));
        System.out.println(kie.tsH_freq.get(kie.tsH_freq.size()-1));*/

        //System.out.println(kie.getrDrH_totalProduct());
        //System.out.println(kie.getTsDtsH_totalProduct());
        //System.out.println(kie.getVpMMI());


        //System.out.println(6.6262 * Math.pow(10, -34));

        /*System.out.println(kie.zpeH);
        System.out.println(kie.zpeRH);
        System.out.println(kie.zpeD);
        System.out.println(kie.zpeRD);
        System.out.println(kie.getZpeH_div_zpeRH());
        System.out.println(kie.getZpeD_div_zpeRD());
        System.out.println(kie.getZpe());*/
        /*ArrayList<Double> myXYZ = kie.tsH_freqn;
        for (Double bd:myXYZ) {
            System.out.println(bd);
        }*/
        /*System.out.println(kie.excH);
        System.out.println(kie.excRH);
        System.out.println(kie.excD);
        System.out.println(kie.excRD);
        System.out.println(kie.excH_div_excRH);
        System.out.println(kie.excD_div_excRD);
        System.out.println(kie.exc);*/
        /*System.out.println(kie.getKie());
        System.out.println(kie.getVp_kie());*/
        //System.out.println(kie);
        /*System.out.println(kie.getrH_zpe());
        System.out.println(kie.getTsH_zpe());
        System.out.println(kie.getrD_zpe());
        System.out.println(kie.getTsD_zpe());
        System.out.println(kie.getEnthalpy_zpe());*/
        //System.out.println(kie.getEnthalpy_vib());
        //System.out.println(kie.getEntropy_vib());
        //System.out.println(kie.getEntropy_rot());
        //System.out.println(kie.getEntropy_vib_plus_rot());
        System.out.println(kie);
    }
}
