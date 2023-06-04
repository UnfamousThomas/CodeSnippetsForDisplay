package proge2;


import java.util.ArrayList;

public class Kodu5b {

/**
 * Hoiab variante mida kasutame summade arvutamiseks
 */
private static final String[] variandid = {"2", "4", "6"};

/**
 * Summa meetod mis kutsutakse
 *
 * @param n Mis summat otsime
 * @return Kahemõõtmeline summade järjend
 */
static int[][] summad(int n) {
        if (n == 0) {
        return new int[0][0];
        }
        ArrayList<ArrayList<Integer>> num = summadRek(n, "", new ArrayList<ArrayList<Integer>>());
        return muudaJarjendiks(num);
        }

/**
 * Sarnane summad meetodile, aga rekursiivne ehk hoiab asju enda signatuuris
 *
 * @param summa   Summa mida otsime
 * @param hetkel  Mis on hetkel summade string
 * @param viimati Mis on kõik summad hetkel
 * @return List mis hoiab summade numbrite liste.
 */
static ArrayList<ArrayList<Integer>> summadRek(int summa, String hetkel, ArrayList<ArrayList<Integer>> viimati) {
        int kokku = arvutaInt(hetkel);
        if (summa == kokku) {
        viimati.add(teeList(hetkel));
        return viimati;
        }
        if (kokku > summa) {
        return viimati;
        }

        if (!hetkel.equals("")) {
        String viimaneHetkel = String.valueOf(hetkel.charAt(hetkel.length() - 1));

        for (int i = 0; i < variandid.length; i++) {
        String variant = variandid[i];
        if (!variant.equals(viimaneHetkel)) {
        viimati = summadRek(summa, hetkel + variant, viimati);
        }
        }
        } else {
        for (String variant : variandid) {
        viimati = summadRek(summa, hetkel + variant, viimati);
        }

        }

        return viimati;
        }

/**
 * Arvutab iga stringi chari (eeldades et numbrid) summa
 *
 * @param number String numbrina, e.g "1234"
 * @return Tagastab intina nende numbrite summa, e.g "1234" = 10
 */
private static int arvutaInt(String number) {
        int kokku = 0;
        if (!number.equals("")) {
        for (int i = 0; i < number.length(); i++) {
        kokku = kokku + Integer.parseInt(String.valueOf(number.charAt(i)));
        }
        } else {
        return 0;
        }

        return kokku;
        }

/**
 * Teeb Stringi numbritest listi
 *
 * @param hetkel Numbrid stringina, e.g  "1234"
 * @return Tagastab numbrid listina, e.g [1,2,3,4]
 */

private static ArrayList<Integer> teeList(String hetkel) {
        ArrayList<Integer> numbrid = new ArrayList<>();
        for (int i = 0; i < hetkel.length(); i++) {
        numbrid.add(Integer.parseInt(String.valueOf(hetkel.charAt(i))));
        }

        return numbrid;

        }

/**
 * Muudab arraylisti (mis koosneb Listidest) kahemõõtmeliseks järjendiks.
 *
 * @param array List mida muuta
 * @return Kahemõõtmeline järjend
 */
public static int[][] muudaJarjendiks(ArrayList<ArrayList<Integer>> array) {
        int[][] jarjend = new int[array.size()][];

        for (int i = 0; i < array.size(); i++) {
        ArrayList<Integer> list = array.get(i);
        int[] rida = new int[list.size()];
        for (int j = 0; j < list.size(); j++) {
        rida[j] = list.get(j);
        }

        jarjend[i] = rida;
        }
        return jarjend;
        }

/**
 * Sõnapõime originaalne meetod. Sisemiselt teeb listi arrayks.
 *
 * @param a Esimene järjend sõnadest
 * @param b Teine järjend sõnadest
 * @return Järjend variantidest.
 */
static String[] sonePoime(String[] a, String[] b) {
        ArrayList<String> sonaPoim = sonaPoimeRek(a, b, "", -1, -1, new ArrayList<String>());
        String[] jarjend = new String[sonaPoim.size()];
        return sonaPoim.toArray(jarjend);
        }

/**
 * sonaPoime rekursiivne abimeetod.
 *
 * @param a          Esimene järjend s��nadest
 * @param b          Teine järjend sõnadest
 * @param hetkel     Mis on hetkel sõne
 * @param viimaneA   Mis on viimaneA indekjs
 * @param viimaneB   Mis on viimaneB indeks
 * @param hetkelKõik Mis on võimalikud sõnapõime variandid hetkel
 * @return Kõik sõnapõime variandid listina
 */
static ArrayList<String> sonaPoimeRek(String[] a, String[] b, String hetkel, int viimaneA, int viimaneB, ArrayList<String> hetkelKõik) {
        int maxPikkus = a.length + b.length;
        if (hetkel.split(" ").length - 1 == maxPikkus) {
        hetkelKõik.add(hetkel.trim());
        return hetkelKõik;
        }

        if (viimaneA + 1 < a.length)
        hetkelKõik = sonaPoimeRek(a, b, hetkel + " " + a[viimaneA + 1], viimaneA + 1, viimaneB, hetkelKõik);

        if (viimaneB + 1 < b.length)
        hetkelKõik = sonaPoimeRek(a, b, hetkel + " " + b[viimaneB + 1], viimaneA, viimaneB + 1, hetkelKõik);

        return hetkelKõik;
        }

public static void main(String[] args) {
//        String[] esimene = {"kas", "mina"};
//        String[] teine = {"olen", "siin"};
//        String[] poim = sonePoime(esimene, teine);
//		System.out.println(Arrays.asList(poim));
        }//peameetod


        }//Kodu5b
