package proge2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

/**
 * @author thomp (04/06/2023)
 */
public class Kodu2a {

    // Kolm massiivi, mis täidetakse failist loetud andmetega
    static String[] kuupäev;
    static String[] kellaaeg;
    static double[] temperatuur;


    public static void loeAndmed(String failitee) {
        //loeb andmed failist nimega "failitee"
        try {
            // Loeme failist kõik read, eeldame et faili kodeering on UTF-8
            List<String> read = Files.readAllLines(Path.of(failitee), StandardCharsets.UTF_8);

            // Määrame massiivide pikkuse
            kuupäev = new String[read.size()];
            kellaaeg = new String[read.size()];
            temperatuur = new double[read.size()];
            for (int i = 0; i < read.size(); i++) {
                // Hakime read tühikute põhjal
                String[] jupid = read.get(i).split(" ");
                // Määrame hakitud jupid vastavatesse massiividesse
                kuupäev[i] = jupid[0];
                kellaaeg[i] = jupid[1];
                temperatuur[i] = Double.parseDouble(jupid[2]);
            }
        } catch (IOException e) {
            // Faili ei leitud või lugemisel esines viga - viskame erindi ja lõpetame töö
            throw new RuntimeException("Faili " + failitee + " lugemisel tekkis viga", e);
        }
    }//loeAndmed

    /**
     * Näidismeetod (mitte kasutamiseks): leiab antud ajal mõõdetud temperatuuri.
     *
     * @param päev Kuupäev kujul aaaa-kk-pp.
     * @param kell Kellaaeg kujul tt:mm:ss.
     * @return Temperatuur kuupäeval <b>päev</b> ajal <b>kell</b>, NaN kui andmetest puudu.
     */
    static double temperatuurValitudPäevalJaKellal(String päev, String kell) {
        for (int i = 0; i < temperatuur.length; i++) {
            if (kuupäev[i].equals(päev) && kellaaeg[i].equals(kell))
                return temperatuur[i];
        }
        return Double.NaN;
    }

    public static void main(String[] args) {
        // Näidis
        // Failitee jooksvas kaustas. IntelliJ puhul otsitakse faili eelkõige PROJEKTI kaustast, mitte SRC kaustast
        // Kui fail asetsetb src kaustas siis peaks failitee olema "src/ilmAegTemp_2022.txt"
        loeAndmed("src/kodu/ilmAegTemp_2022.txt");//loeb andmed etteantud failist
        System.out.println("Kodutöö nr 2a");
        System.out.println();

        System.out.println("Kontrolliks, massiivide algused:");
        System.out.printf("%s\t%s\t %s%n", "kuupäev[]", "kellaaeg[]", "temperatuur[]");
        for (int i = 0; i < 10; i++)
            System.out.printf("%s\t%s\t%.11f%n", kuupäev[i], kellaaeg[i], temperatuur[i]);

        System.out.println("   ...   \t".repeat(3) + "\n");

        String sünnipäev = "2022-08-29";
        String kell = "12:00:00";
        double temp = temperatuurValitudPäevalJaKellal(sünnipäev, kell);

        System.out.printf("Minu sünnipäeval aastal 2022 (%s) oli keskpäevane temperatuur: ", sünnipäev);
        if (Double.isNaN(temp))
            System.out.printf("- %nAntud aega: %s %s ei ole andmestikus!%n%n", sünnipäev, kell);
        else
            System.out.printf("%.1f kraadi.%n%n", temp);

        //System.out.println(Arrays.toString(kuudeKeskmised()));

        //System.out.println(Arrays.toString(aastaMinMax()));

        //System.out.println(aastaKesk());

        System.out.println(Arrays.toString(pikimKasvavKahanev()));
    }

    // Õpilase poolt teostatavad meetodid:

    public static double aastaKesk() {
        double kokku = 0;
        for (double temp : temperatuur) {
            kokku = kokku + temp;
        }

        return (kokku / temperatuur.length);
    }

    public static double[] aastaMinMax() {
        double min = temperatuur[0];
        double max = temperatuur[0];
        for (double temp : temperatuur) {
            if (temp > max) max = temp;
            if (temp < min) min = temp;
        }
        return new double[]{min, max};
    }

    public static String[] pikimKasvavKahanev() {

        int suurimKahanev = 0;
        int suurimKasvav = 0;
        String suurimKahanevAlgus = "";
        String suurimKahanevLõpp = "";
        String suurimKasvavAlgus = "";
        String suurimKasvavLõpp = "";

        boolean kasvas = false;
        boolean kahanes = false;

        int hetkelKahanev = 0;
        int hetkelKasvav = 0;

        double eelmineTemp = 0;
        String algusKasv = "";
        String algusKahanev = "";

        String loppKasv = "";
        String loppKahanev = "";

        for (int i = 0; i < temperatuur.length; i++) {
            if (i == 0) eelmineTemp = temperatuur[i];

            if (temperatuur[i] > eelmineTemp) {
                //kasvas
                if (kasvas) {
                    hetkelKasvav = hetkelKasvav + 1;
                    loppKasv = kuupäev[i] + " " + kellaaeg[i];
                } else {
                    kasvas = true;
                    kahanes = false;
                    hetkelKasvav = 1;
                    loppKasv = kuupäev[i] + " " + kellaaeg[i];
                    algusKasv = kuupäev[i-1] + " " + kellaaeg[i-1];

                }

                if (hetkelKasvav > suurimKasvav) {
                    suurimKasvav = hetkelKasvav;
                    suurimKasvavLõpp = loppKasv;
                    suurimKasvavAlgus = algusKasv;
                }

            }

            if (temperatuur[i] < eelmineTemp) {
                //kahanes
                if (kahanes) {
                    hetkelKahanev = hetkelKahanev + 1;
                    loppKahanev = kuupäev[i] + " " + kellaaeg[i];
                } else {
                    kasvas = false;
                    kahanes = true;
                    hetkelKahanev = 1;
                    loppKahanev = kuupäev[i] + " " + kellaaeg[i];
                    algusKahanev = kuupäev[i-1] + " " + kellaaeg[i-1];
                }

                if (hetkelKahanev > suurimKahanev) {
                    suurimKahanev = hetkelKahanev;
                    suurimKahanevLõpp = loppKahanev;
                    suurimKahanevAlgus = algusKahanev;
                }
            }

            eelmineTemp = temperatuur[i];
        }

        String[] suurimad = new String[2];

        if (suurimKasvav > suurimKahanev) {
            System.out.println("Kasvav");
            suurimad[0] = suurimKasvavAlgus;
            suurimad[1] = suurimKasvavLõpp;
        } else {
            System.out.println("Kahanev");
            suurimad[0] = suurimKahanevAlgus;
            suurimad[1] = suurimKahanevLõpp;
        }
        return suurimad;
    }


    public static double[] kuudeKeskmised() {
        // Tagastada 12-elemendiline järjend kus
        // 0. kohal on jaanuari keskmine temperatuur
        // 1. kohal on veebruari keskmine temperatuur
        // ...
        // Tagastuse näide: [-3.23534509, ..., 1.4567456]

        double kokku = 0;
        int mituTempi = 0;

        double[] keskmine = new double[12];


        for (int i = 0; i < kuupäev.length; i++) {
            String kuupäevHetkel = kuupäev[i];

            int kuu = Integer.parseInt(kuupäevHetkel.split("-")[1]);

            if(i+1 < kuupäev.length) {
                //pole viimane
                int jargKuu = Integer.parseInt(kuupäev[i+1].split("-")[1]);
                if(jargKuu == kuu) {
                    kokku = kokku + temperatuur[i];
                    mituTempi = mituTempi + 1;
                } else {
                    kokku = kokku + temperatuur[i];
                    double eelmineKesk = kokku / (mituTempi +1);
                    keskmine[kuu-1] = eelmineKesk;

                    System.out.println("Kuu: " + (kuu - 1) + ". Keskmine: " + eelmineKesk);
                    mituTempi = 0;
                    kokku = 0;
                }
            } else {
                kokku = kokku + temperatuur[i];

                double eelmineKeskmine = kokku / (mituTempi+1);
                keskmine[kuu - 1] = eelmineKeskmine;
            }
        }

        return keskmine;
    }
}
