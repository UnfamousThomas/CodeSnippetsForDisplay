package proge2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author thomp (04/06/2023)
 */
public class Kodu10a {
    /**
     * Meetod lause transponeerimseks
     * @param lause Lause mida transponeerida
     * @return Transponeeritud lause
     */
    public static String transponeeri(String lause) {
        if(lause.equals("")) return "";
        String[] osad = lause.split(" ");
        return transponeeriRek(osad, osad.length-1, "").trim();
    }

    /**
     * Rekursiivne abi meetod transponeerimiseks
     * @param lauseOsad Lauseosad mida transponeerime
     * @param indeks Indeks kus oleme
     * @param hetkel Mis on hetkel lause
     * @return Transponeeritud lause
     */
    public static String transponeeriRek(String[] lauseOsad, int indeks, String hetkel) {
        if(indeks == -1) return hetkel;
        hetkel += " " + lauseOsad[indeks];
        return transponeeriRek(lauseOsad,indeks-1,hetkel);
    }

    /**
     * Transponeeri lause, jättes märgid samadesse kohtadesse
     * @param lause Lause mida transponeerida
     * @return Transponeeritud lause
     */
    public static String transponeeriMärkidega(String lause) {
        if(lause.equals("")) return "";
        //Regex mis splitib lause nii et kõik kirjavahemärgid on eraldi. NOTE: Regex genetud chatgpt abil
        String[] osad = lause.split("(?<=\\s)|(?=\\s)|(?=[.,!?:])|(?<=[.,!?:])");
        return transponeeriMärkRek(osad, 0, osad.length-1);
    }

    /**
     * Märkidega transponeerimis rekursiivne abimeetod
     * @param osad järjend osadest mida transponeerime
     * @param vasak Vasakult poolt indeks
     * @param parem Paremalt poolt indeks
     * @return Tagastatud lause.
     */
    private static String transponeeriMärkRek(String[] osad, int vasak, int parem) {
        //Kui oleme liikunud üle parema siis oleme töö lõpetanud
        if (vasak >= parem) {
            return String.join("", osad);
        }

        // Jätta vahele kõik kirjavahe märgid
        if (!Character.isLetterOrDigit(osad[vasak].charAt(0))) {
            return transponeeriMärkRek(osad, vasak + 1, parem);
        }
        if (!Character.isLetterOrDigit(osad[parem].charAt(0))) {
            return transponeeriMärkRek(osad, vasak, parem - 1);
        }

        // Vaheta sõnad ära (kirjavahe märgid ignotakse)
        String temp = osad[vasak];
        osad[vasak] = osad[parem];
        osad[parem] = temp;

        // Kutsub uuesti välja et liikuda
        return transponeeriMärkRek(osad, vasak + 1, parem - 1);
    }


    /**
     * Arvuta meetod. Loodud koos 17.mai praktikumis
     * @param avaldis Avaldis mida arvutada
     * @return Vastus
     */
    public static int arvuta(String avaldis){
        Queue<Character> sumbolid = new LinkedList<>();
        for (char c : avaldis.toCharArray()) {
            sumbolid.add(c);
        }
        return arvutaRek(sumbolid);
    }

    /**
     * Arvutamis rekursiivne abimeetod. Loodud koos 17. mai praktikumis.
     * @param sumbolid Sumbolid mis olid algses avaldises
     * @return Vastus
     */
    private static int arvutaRek(Queue<Character> sumbolid) {
        int tulemus = 0;
        while(true) {
            Character sym = sumbolid.poll();
            if(sym == null || sym == ')') return tulemus;
            else if(Character.isDigit(sym)) tulemus += Integer.parseInt("" + sym);
            else if (sym == '(') {
                int suluTul = arvutaRek(sumbolid);
                tulemus += suluTul*suluTul;
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(transponeeriMärkidega("Tere, ma olen Thomas!"));
    }
}