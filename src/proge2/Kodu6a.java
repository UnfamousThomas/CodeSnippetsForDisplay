package proge2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author thomp (04/06/2023)
 */
public class Kodu6a {

    public static void main(String[] args) {
        int[] a = {2,12,5,2};
        int[] b = {10,10,10};
        String[] tehted = avaldisedLõigus1(a, -15, 15);
        String[] tehted2 = avaldisedLõigus1(b,30,30);
        for (String s : tehted) {
            System.out.println(s);
        }
        for (String s : tehted2) {
            System.out.println(s);
        }
    }

    /**
     * Metood mis kutsutakse et rekursioon algaks
     * @param a Numbrid millega proovime luua tehteid
     * @param x Miinimum arv
     * @param y Maksimum arv
     * @return Tehted mis sobivad
     */
    public static String[] avaldisedLõigus1(int[] a, int x, int y) {
        //Kui pole arve tagastame tühja
        if (a.length == 0) return new String[0];
        //Leiame arraylisti avaldistest
        ArrayList<String> avaldised = avaldisedLõigusRek(a, x, y, new ArrayList<>(), String.valueOf(a[0]), 1, a[0]);
        //Sorteerime avaldised
        sorteeriAvaldised(avaldised);
        //Teeme uue järjendi avaldiste jaoks
        String[] avaldisedArr = new String[avaldised.size()];
        //Teisendame listi arraysse
        avaldised.toArray(avaldisedArr);

        return avaldisedArr;
    }

    /**
     * Rekursiivne avaldiste funktsioon
     * @param a Arvud millega proovime lua tehteid
     * @param x Miinimum väärtus
     * @param y Maksimum väärtus
     * @param tehted Hetkel salvestatud tehted
     * @param hetkelTehe Hetkelises puu "branchis" olev tehe
     * @param viimaneIndeks Viimane indeks mille võtsime a'st
     * @param hetkelVäärtus Mis on hetkelise branchi tehte väärtus
     * @return List avaldisi stringina
     */
    public static ArrayList<String> avaldisedLõigusRek(int[] a, int x, int y, ArrayList<String> tehted, String hetkelTehe, int viimaneIndeks, int hetkelVäärtus) {

        if (viimaneIndeks == a.length) {
            if (hetkelVäärtus >= x && hetkelVäärtus <= y) {
                String tehe = hetkelTehe + "=" + hetkelVäärtus;
                tehted.add(tehe);

            }
            return tehted;
        }

        tehted = avaldisedLõigusRek(a, x, y, tehted, hetkelTehe + "-" + a[viimaneIndeks],
                viimaneIndeks + 1, hetkelVäärtus - a[viimaneIndeks]);

        tehted = avaldisedLõigusRek(a, x, y, tehted, hetkelTehe + "+" + a[viimaneIndeks],
                viimaneIndeks + 1, hetkelVäärtus + a[viimaneIndeks]);


        return tehted;

    }

    /**
     * Sorteerib avaldised Collectionsi ja Comparator abil.
     * @param avaldised Avaldised mis tuleb ära sorteerida
     */
    public static void sorteeriAvaldised(ArrayList<String> avaldised) {
        Comparator<String> vordleja = (avaldis1, avaldis2) -> {
            //Tee avaldistest listid ennem ja peale võrdusmärki
            String[] avaldisOsad1 = avaldis1.split("=");
            String[] avaldisOsad2 = avaldis2.split("=");
            //Võta arvud peale võrdusmärki
            int arv1 = Integer.parseInt(avaldisOsad1[1]);
            int arv2 = Integer.parseInt(avaldisOsad2[1]);

            //Võrdle arve
            int arvudeVordlus = Integer.compare(arv1, arv2);
            if (arvudeVordlus != 0) {
                //Kui pole võrdsed returni võrdluse tulemus
                return arvudeVordlus;
            } else {
                //Muidu comparei Stringidena
                return avaldisOsad1[0].compareTo(avaldisOsad2[0]);
            }
        };

        //Sorteeri arraylist vordleja põhjal
        Collections.sort(avaldised, vordleja);
    }
}