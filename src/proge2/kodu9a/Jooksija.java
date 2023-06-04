package proge2.kodu9a;

/**
 * @author thomp (04/06/2023)
 */
public class Jooksija {

    //Hoiab inimese nime failis
    private String nimi;

    //Esimese vahepeatuse aeg
    private Float aeg1;

    //Teise vahepeatuse aeg
    private Float aeg2;

    //Kolmanda vahepeatuse aeg
    private Float aeg3;
    //Nelja vahepeatuse aeg
    private Float aeg4;
    //Viienda vahepeatuse aeg
    private Float aeg5;
    //Kuuend vahepeatuse aeg
    private Float aeg6;
    //Lõppaeg
    private Float loppaeg;

    //Igas vahepeatuses ja lõpus kohti hoidev järjend
    private int[] kohad = new int[7];

    //Hoiab suurimat tõusu
    private int suurimTõus = 0;

    /**
     * Loob uue instancei sellest klassist
     * @param rida Rida failis kust andmeid lugeda
     */
    public Jooksija(String rida) {
        //Loob reast järjendi
        String[] soned = rida.split("\\s+");
        //Kuna nimi võib olla teoorias üks kõik kui pikk siis see loogika loob meile sb abil nime
        StringBuilder hetkelNimi = new StringBuilder();
        int indeks = 0;
        while(!kasOnNumber(soned[indeks].charAt(0))) {
            hetkelNimi.append(soned[indeks]);
            hetkelNimi.append(" ");

            indeks += 1;
        }

        //Anname muutujatele väärtused
        this.nimi = hetkelNimi.toString().trim();
        aeg1 = tagastaSekundites(soned[indeks]);
        aeg2 = tagastaSekundites(soned[indeks+1]);
        aeg3 = tagastaSekundites(soned[indeks+2]);
        aeg4 = tagastaSekundites(soned[indeks+3]);
        aeg5 = tagastaSekundites(soned[indeks+4]);
        aeg6 = tagastaSekundites(soned[indeks+5]);
        loppaeg = tagastaSekundites(soned[indeks+6]);
    }

    /**
     * Andes timeri aja, tagastab aja sekundites
     * @param aeg Timeri aeg stringina
     * @return Aeg sekundites, komakohaga.
     */
    private float tagastaSekundites(String aeg) {
        //Loome järjendi
        String[] numbrid = aeg.split(":");
        //Mitu tundi aeg kestis sekundites (tunnid * 60 * 60)
        float tunnidSek = Float.parseFloat(numbrid[0]) * 60 * 60;
        //Mitu minutit sekundites kestis (minutid *60)
        float minutSek = Float.parseFloat(numbrid[1]) * 60;

        //Summa: tunnid + minutid + sekundid
        return Float.parseFloat(numbrid[2]) + tunnidSek + minutSek;
    }

    /**
     * Kontrollime kas antud char on number. Eeldan siin et nimi ei alga veel tänapäeva maailmas numbriga (kuigi Elon Muski laps lähedal)
     * @param a char mida kontrollime
     * @return Kas on number
     */
    private boolean kasOnNumber(Character a) {
        //Suht basic java meetod, mis kasutab seda et visatakse exception kui pole number.
        try {
            Integer.parseInt(String.valueOf(a));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String getNimi() {
        return nimi;
    }


    /**
     * Meetod et ei peaks kasutama midagi reflektsiooni sarnast vaid saaks kergelt kõik ajad eraldi kätte
     * @param seeria Seeria mida vaatame
     * @return Selle seeria Jooksija aeg
     */
    public Float getAeg(int seeria) {
        //Ilmselt oleks switch mõtekam olnud
        if(seeria == 1) return aeg1;
        if(seeria == 2) return aeg2;
        if(seeria==3) return aeg3;
        if(seeria==4) return aeg4;
        if(seeria==5) return aeg5;
        if(seeria==6) return aeg6;
        else return loppaeg;
    }

    /**
     * Paneb kirja koha seerial
     * @param seeria Tegu pole tegelikult seeriaga vaid vahepausiga, aga see sõna meeldib mulle rohkem
     * @param koht Mitmendal kohal ta seal oli
     */
    public void setKoht(int seeria, int koht) {
        this.kohad[seeria-1] = koht;
        //Pole mõtet kontrollida esimesel seerial.
        if(seeria > 1) arvutaUusTõus(seeria);
    }

    /**
     * Arvutab uue suurima tõusu kui mingit kohta muudeti
     * @param seeriaMisUuendati Seeria mille kohta muudeti
     */
    private void arvutaUusTõus(int seeriaMisUuendati) {
        //Mis on seerias uus koht
        int uusKoht = getKoht(seeriaMisUuendati);
        //Kuna esimest seeriat pole mõtet kontrollida (seeriat 0 ega -1 pole olemas) siis alustan teisest
        for (int i = 1; i < seeriaMisUuendati; i++) {
            //Kontrollin kõiki eelnevaid kohti selle koha vastu ja arvutan tõusu.
            int tõus = getKoht(i)-uusKoht;
            //Kui tõus on suurem kui hetkelSuurim siis muudan.
            if(tõus > suurimTõus) suurimTõus=tõus;
        }
    }


    /**
     * Printimiseks meetod mis loob stringi
     * @return Tagastab kohtadega stringi
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getNimi());
        sb.append(" ");
        for (int i : kohad) {
            sb.append(i);
            sb.append(" ");
        }
        //Trimmime et eemaldada viimane " ".
        return sb.toString().trim();
    }

    /**
     * Meetod et saada seerias olev koht
     * @param seeria Mis seeria
     * @return Mitmes koht.
     */
    public int getKoht(int seeria) {
        return kohad[seeria-1];
    }

    public int getSuurimTõus() {
        return suurimTõus;
    }
}