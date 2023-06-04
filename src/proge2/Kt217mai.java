package proge2;

import java.util.ArrayList;

/**
 * @author thomp (04/06/2023)
 */
public class Kt217mai {
    /**
     * Arvutab fibonaccid
     * @param n Millest peab arv suurem olema
     * @return Fibonacci arv
     */
    public static int fiboX(int n){
        return fiboXRek(n, 0,1);
    }

    /**
     * Fibonacci rekursiivne funktsioon
     * @param n Millest peab suurem olema
     * @param kaksEnnem 2 ennem praegust arvu
     * @param uksEnnem Üks ennem praegust arvu
     * @return Fibonacci arv
     */
    private static int fiboXRek(int n, int kaksEnnem, int uksEnnem) {
        int nuud = kaksEnnem + uksEnnem;
        if(nuud > n) return nuud;
        return fiboXRek(n, uksEnnem, nuud);
    }

    /**
     * Arvutab mitu korda Pythoni funktsioon lahutab
     * @param n Phytoni sisend
     * @return Arv mitu lahutustehet sooritati selle raames
     */
    public static int g(int n) {
        return gRek(n);
    }

    /**
     * gRek rekursiivne funktsioon
     * @param n Sisend
     * @return Mitu tehet sooritati
     */
    private static int gRek(int n) {
        if(n < 1) return 0; //Null tehet
        if(n < 6) return 1 + gRek(n-1); //Lisame ühe tehte + mis iganes tagastatakse
        return
                5+
                        gRek(n-1)+
                        gRek(n-2)+
                        gRek(n-2)+
                        gRek(n-3); //Lisame 5 (4 sees ja üks nende vahel) tehet + mis tagastatakse
    }

    /**
     * Leiame kas on olemas mittekahanevad osa järjendid
     * @param a Järjend millega võrdleme
     * @return Tõeväärtus, kas on olemas
     */
    public static boolean mitteKahanevadOsajärjendid(int[] a){
        return mitteKahanevadOsajärjendidRek(a, new ArrayList<>(), new ArrayList<>(), 0);
    }

    /**
     * Rekursiivne abimeetod osajärjendite jaoks
     * @param a Algne järjend
     * @param b List mis hoiab ühte osajärjendit
     * @param c List mis hoiab teist osajärjendit
     * @param aIndeks Indeks kus oleme algses järjendis
     * @return Tõeväärtus kas on olemas osajärjendid
     */
    private static boolean mitteKahanevadOsajärjendidRek(int[] a, ArrayList<Integer> b, ArrayList<Integer> c, int aIndeks) {
        if(aIndeks == a.length) {
            return c.size() + b.size() == a.length; //Kui on sama palju elemente kokku siis on sorteeritud õigesti.
        }
        boolean esimene = false; //Algselt eeldame et ei leidu osajärjendeid
        boolean teine = false;

        //Üpris loll ülesehitus, aga töötab.
        //Kui on tühi v eelmine on väiksem võrdne praegusega
        if(b.isEmpty() || b.get(b.size()-1) <= a[aIndeks]) {
            b.add(a[aIndeks]); //Lisa praegune
            esimene = mitteKahanevadOsajärjendidRek(a, b, c, aIndeks+1); //Liigu edasi
            b.remove(b.size()-1); //Eemalda praegune
        } else { //Muidu
            esimene = mitteKahanevadOsajärjendidRek(a, b, c, aIndeks+1); //Liigu edasi ilma muutmata
        }

        if(c.isEmpty() || c.get(c.size()-1) <= a[aIndeks]) { //Sama asi kui ülevalpool aga cga.
            c.add(a[aIndeks]);
            teine = mitteKahanevadOsajärjendidRek(a,b,c,aIndeks+1);
            c.remove(c.size()-1);
        } else {
            teine = mitteKahanevadOsajärjendidRek(a,b,c,aIndeks+1);
        }

        return teine || esimene; //Kui esimene v teine tagastab true

    }

    public static void main(String[] args) {
        int[] jarjend = new int[]{12,11,10,17,15,17};
        System.out.println(mitteKahanevadOsajärjendid(jarjend));
    }
}
