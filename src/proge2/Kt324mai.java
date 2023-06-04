package proge2;

/**
 * @author thomp (04/06/2023)
 */
public class Kt324mai {

    /**
     * Meetod mille abil tõlkida lause, ehk eemaldada vajalikud märgid.
     *
     * @param s Algne sõne.
     * @return Sõne mille märgid on eemaldatud.
     */
    public static String tõlgiLause(String s) {
        String[] sonad = s.split(" ");
        StringBuilder uus = new StringBuilder();
        for (String eraldi : sonad) {
            String ajutine = eraldi;
            String mrk = leiaViimaneMärk(eraldi);
            //Replaceime kõik märgid... Saaks ilmselt ilusamalt regexiga aga jah.
            ajutine = ajutine.replaceAll("\\.", "").replaceAll(",", "").replaceAll("!", "")
                    .replaceAll("\\?", "").replaceAll("\\*", "");
            uus.append(ajutine).append(mrk).append(" ");
        }
        return uus.toString().trim();
    }

    /**
     * Kas antud char on . , ! v ?
     *
     * @param a Mis charri kontrollida
     * @return Tõeväärtus
     */
    private static boolean kasOnMark(char a) {
        return a == '.' || a == ',' || a == '!' || a == '?';
    }

    /**
     * Leiab Stringi viimase märgi, kui pole jätab tühjaks.
     *
     * @param s Mis sõne kontrollida
     * @return Viimane mark.
     */
    private static String leiaViimaneMärk(String s) {
        String mark = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            if (Character.isLetter(s.charAt(i))) {
                break;
            }
            if (kasOnMark(s.charAt(i))) {
                mark = String.valueOf(s.charAt(i));
                break;
            }
        }
        return mark;
    }

    /**
     * Meetod et tagastada lause sõnade pikim ühine suffix
     *
     * @param s Algne lause
     * @return Järjend pikimate suffixidega sõnadega.
     */
    public static String[] pikimÜhissufiks(String s) {
        //Eemalda kirjavahemärgid
        s = s.replaceAll("\\.", "").replaceAll(",", "").replaceAll("!", "")
                .replaceAll("\\?", "").replaceAll(";", "").replaceAll(":", "");
        //Loo järjend
        String[] paar = new String[2];
        //Algne pikkus
        int hetkelPimimPikkus = 0;
        //Saa kätte sõnad
        String[] sonad = s.split(" ");
        //Siin saaks ilmselt midagi targemat teha, kuna hetkel sisuliselt 3 for loopi, aga töötab.
        for (int esimeneSonaInd = 0; esimeneSonaInd < sonad.length; esimeneSonaInd++) {
            for (int teineSonadInd = 0; teineSonadInd < sonad.length; teineSonadInd++) {
                if (esimeneSonaInd == teineSonadInd) continue;
                int pikkus = tagastaPikimSonadeUhineSuffix(sonad[esimeneSonaInd], sonad[teineSonadInd]);
                if (pikkus > hetkelPimimPikkus) {
                    paar[0] = sonad[esimeneSonaInd]; //Kui on pikim kui eelmine asendame
                    paar[1] = sonad[teineSonadInd];
                    hetkelPimimPikkus = pikkus;
                }
            }
        }
        if (paar[0] != null) return paar;
        return null;
    }

    /**
     * Abimeetod mis leiab kahe sõne ühise suffixi
     * @param a Esimene sõne
     * @param b Teine sõne
     * @return int pikimast suffixist
     */
    public static int tagastaPikimSonadeUhineSuffix(String a, String b) {
        int uhine = 0;
        char[] aTahed = a.toCharArray();
        char[] bTahed = b.toCharArray();
        for (int i = 0; i < aTahed.length; i++) {
            int aindeks = aTahed.length - 1 - i;
            int bindeks = bTahed.length - 1 - i;
            if (bindeks < 0) break;
            if (aTahed[aindeks] != bTahed[bindeks]) break;
            else uhine += 1;
        }
        return uhine;
    }

    /**
     * Rekursiooni meetod mis kaotab tähepaarid sõnest
     * @param s Sõne kust kaotada tähepaarid
     * @return Tähepaarideta sõne
     */
    public static String kaotaTähepaarid(String s) {
        if(s.length() == 1) return s;
        return kaotaTähepaaridRek(s, 0, "", "", false);
    }

    /**
     * Rekursiooni abimeetod tähepaaride kaotamiseks
     * @param s Sõne mida muudame
     * @param indeks Mis indeksit vaatame
     * @param hetkel Teekond
     * @param jooksev Kus oleme hetkel
     * @param viimaneKontrollTehtud Kas teeme viimast kontrolli
     * @return Eemaldatud tähepaaridega sõne
     */
    private static String kaotaTähepaaridRek(String s, int indeks, String hetkel, String jooksev, boolean viimaneKontrollTehtud) {
        if (indeks == 0 && hetkel.isBlank()) hetkel = s;
        else if (indeks == 0 && !viimaneKontrollTehtud) {
            if(jooksev.isBlank() || jooksev.isEmpty()) jooksev = " ";
            hetkel += "," + jooksev;
            jooksev = "";
        }
        if (indeks >= s.length()) {
            if(viimaneKontrollTehtud) return hetkel;
            else return kaotaTähepaaridRek(s,0,hetkel,jooksev,true);
        }

        if(indeks+1 <= s.length()-1) {
            //kontrollime charre
            char hetkelC = s.charAt(indeks);
            char jargC = s.charAt(indeks+1);
            if(hetkelC != jargC) jooksev += s.charAt(indeks);
            else {
                //Vaatame edasist sõne
                for (int i = indeks+1; i < s.length()-2; i++) {
                    char hetkelEdasi = s.charAt(i);
                    char jargEdasi = s.charAt(i+1);
                    //Siin, hetkel (kirjutamise ajal) probs loogika viga. Ei märka seda aga ilmselt charide võrdlemise loogikas midagi  valesti
                    if(hetkelEdasi == jargEdasi && hetkelEdasi < hetkelC) {
                        //Paneme jooksva võrduma eemaldatud striga
                        jooksev = s.substring(0, i) + s.substring(i+2);
                        //Algusesse tagasi
                        return kaotaTähepaaridRek(jooksev,0,hetkel,jooksev, false);
                    }
                }
                //Jooksev on eemaldatud str
                jooksev = s.substring(0,indeks) + s.substring(indeks+2);
                if(jooksev.equals("")) jooksev = " "; //Tühik kui pole midagi alles
                return kaotaTähepaaridRek(jooksev,0,hetkel,jooksev, false); //Algusesse tagasi
            }
        }
        return kaotaTähepaaridRek(s, indeks+1, hetkel, jooksev, viimaneKontrollTehtud); //Liigume edasi

    }

    public static void main(String[] args) {
        // Siin võib vabas vormis oma lahendust testida.
        // Automaattestid tõid hinnates main meetodit ei käivita. (Samas peab siiski kompileeruma!)

        String[] sisendid1 = {
                "t*!?",
                "ku**ra!t",
                "Tere. ku*.ra!t!!",
                "kuk.u.pra**t*",
                "Tere lammas, mis sa va**hid?*"
        };

        String[] sisendid2 = {
                "Meil naabriõues kasvas üks kuusk ja teine kask."
        };


        String[] sisendid3 = {
                "uu"

        };

        for (String s : sisendid3) {
            System.out.println(kaotaTähepaarid(s));
        }

    }
}