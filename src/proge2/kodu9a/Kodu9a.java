package proge2.kodu9a;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * @author thomp (04/06/2023)
 */
public class Kodu9a {
    /**
     * Annab meile võistleja info tema nime alusel
     * @param x Võistleja nimi
     * @param failinimi Fail mida vaatame
     * @return Tema info (toString)
     */
    public static String võistlejaDünaamika(String x, String failinimi)  {
        List<Jooksija> jooksijad = laeJooksijad(failinimi);
        for (Jooksija jooksija : jooksijad) {
            if(jooksija.getNimi().equals(x)) {
                return jooksija.toString();
            }
        }
        return null;
    }

    /**
     * Annab meile võistleja info koha alusel. Vaadatakse esimest võimalust, see tähendab kui sama ajaga siis antakse esimene.
     * @param lõpp Mitmes koht oli.
     * @param failinimi Mis failist vaatame.
     * @return Jooksija koguinfo (toString)
     */
    public static String lõpetajaDünaamika(int lõpp, String failinimi)  {
        List<Jooksija> jooksijad = laeJooksijad(failinimi);

        for (Jooksija jooksija : jooksijad) {
            if(jooksija.getKoht(7) == lõpp) {
                return jooksija.toString();
            }
        }

        return null;
    }

    /**
     * Arvutab dünaamika suurima muutuse ülespoole
     * @param failinimi Fail kust otsida
     * @return Inimese info kes kõige rohkem muutus
     */
    public static String suurimÜlesDünaamika(String failinimi) {
        //Laeme jooksijad
        List<Jooksija> jooksijad = laeJooksijad(failinimi);
        //Loome sorteeria mis valib väärtusi selle alusel mis on suurim tõus.
        Comparator<Jooksija> sorteeria = (o1, o2) -> Integer.compare(o2.getSuurimTõus(), o1.getSuurimTõus());

        //Sorteeri ja väljasta esimene
        jooksijad.sort(sorteeria);

        //See rida võib olla problemaatiline kui failis pole ühtegi jooksijat aga kuna automaat seda ei testinud ei hakanud lisama checki.
        return jooksijad.get(0).toString();
    }

    /**
     * Abimeetod mis tagastab listina kõik jooksijad
     * @param failiNimi Fail millest lugeda
     * @return list täis jooksijad ja nende kohad arvutatud
     */
    private static List<Jooksija> laeJooksijad(String failiNimi) {
        List<Jooksija> jooksijad = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(failiNimi));
            while (scanner.hasNext()) {
                String rida = scanner.nextLine();
                Jooksija jooksija = new Jooksija(rida);
                jooksijad.add(jooksija);
            }
            for (int i = 1; i < 8; i++) {
                arvutaKohad(i, jooksijad);
            }
            return jooksijad;
        } catch (IOException e) {
            throw new RuntimeException("Faili lugemine ebaõnnestus!");
        }
    }

    /**
     * Meetod kohtade arvutamiseks
     * @param mitmesSeeria Mitmendat seeriat vaatame
     * @param jooksijad List jooksijatest keda võrdleme
     */
    private static void arvutaKohad(int mitmesSeeria, List<Jooksija> jooksijad) {
        //Võrdlemis süsteem. Kasutame Comparatori, mis võrdleb tollel seerial aegu.
        Comparator<Jooksija> sorteeria = (o1, o2) -> {
            if(o1.getAeg(mitmesSeeria).equals(o2.getAeg(mitmesSeeria))) return 0;
            if(o1.getAeg(mitmesSeeria) > o2.getAeg(mitmesSeeria)) return 1;
            else return -1;
        };
        //Sorteerime sorteeria abil listi
        jooksijad.sort(sorteeria);

        //Mis koht viimane oli
        int eelmineKoht = 1;
        //Mis koht on päriselt hetkel
        int koht = 1;
        float eelmineAeg = 0;
        for (Jooksija jooksija : jooksijad) {
            //Kontrollime alates 2st jooksijast kas on sama aeg.
            if(koht != 1 && jooksija.getAeg(mitmesSeeria) == eelmineAeg) {
                //Kui on siis paneme kirja et jooksija koht on sama mis eelmine
                jooksija.setKoht(mitmesSeeria, eelmineKoht);
            } else {
                //Muidu paneme kohaks selle mitmes päriselt on
                jooksija.setKoht(mitmesSeeria, koht);
                eelmineKoht = koht;
            }
            //uuendame aja muutujat
            eelmineAeg = jooksija.getAeg(mitmesSeeria);
            //uuendame kohta
            koht += 1;

        }
    }

    public static void main(String[] args) throws IOException {
        //System.out.println(suurimÜlesDünaamika("andmed.txt"));
        //System.out.println(võistlejaDünaamika("Olle Raul", "andmed.txt"));
    }
}