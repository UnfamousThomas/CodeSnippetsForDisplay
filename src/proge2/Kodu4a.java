package proge2;

import java.util.Arrays;

/**
 * @author thomp (04/06/2023)
 */
public class Kodu4a {
    public static void main(String[] args) {
        int[][] a = {
                {1,  2,  3},
                { 4,  5,  6},
                {7,  8,  9},
                {10, 11, 12},
                {13, 14, 15},
                {16, 17, 18},
                {19, 20, 21},
                {22, 23, 24}

        };

        boolean[][] b = {
                {true, true, true},
                {true, false, true},
                {true, true, true},
        };

        int[] c = pikendatudDiagonaal(a);
        //...
        long start = System.currentTimeMillis(); //AEG KÄIMA

        int parimN = ruutÜhtedest(b);
        long stop = System.currentTimeMillis();//AEG KINNI
        System.out.println("Aega kulus " + (stop - start)
                + " milliskundit");
        System.out.println("Parim n=" + parimN);
    }//main


    //Pikendatud diagonaalide leidmis meetod
    //Tagastab järjendi täis diagonaalide arve
    //Võtab kahemõõtmelise järjendi ehk maatriksi
    public static int[] pikendatudDiagonaal(int[][] a) {
        //Loon uue järjendi. Algne suurus on 2 korda järjendi read.
        int[] tulemus = new int[a.length*2];
        //Kas olen puudutanud vasakut äärt
        boolean vasakÄärPuuduta = false;
        //Kas liigun vasakult paremale
        boolean vasakultParemale = true;
        //Kas liigun alt üles
        boolean altÜles = false;
        //Mis oli eelmine veerg
        int eelmineVeerg = 0;
        //Mitu korda olen liigutanud
        int kordi = 0;
        //Millisel real olen
        int rida = 0;
        //Töötab kuni puudutan vasakut rida
        while(!vasakÄärPuuduta) {
            //Esimene kord pole mõtet muuta andmeid
            if(kordi != 0) {
                //Arvutan uue veeru
                eelmineVeerg = arvutaUusVeerg(eelmineVeerg, vasakultParemale);
                //Arvutan uue rea
                rida = arvutaUusRida(rida, altÜles);
            }

            //Kui veerg on võrdne viimasega, siis hakkan liikuma paremalt vasakule
            if(eelmineVeerg == a[0].length-1) {
                vasakultParemale = false;
            }

            //Kui veerg on võrdne esimesega ja ei ole esimene kord, siis liigun vasakult paremale.
            if(kordi != 0 && eelmineVeerg==0) {
                vasakultParemale = true;
            }

            //Kui on viimane rida, hakkan liikuma alt üles.
            if(rida == a.length-1) {
                altÜles = true;
            }

            //Kui pole esimene kord ja rida on 0 siis hakkan liikuma ülalt alla.
            if (kordi != 0 && rida==0) {
                altÜles = false;
            }

            //Kui tulemuse pikkus on väiksem kui kordi, suurendan seda 5 võrra.
            if(tulemus.length-1 < kordi) {
                tulemus = Arrays.copyOf(tulemus, kordi+5);
            }

            //Lisan tulemustesse elemendi
            tulemus[kordi] = a[rida][eelmineVeerg];

            //Kui kordi on 0 ja veerg on 0 siis puudutasin vasakut äört.
            if(kordi != 0 && eelmineVeerg==0) {
                vasakÄärPuuduta = true;
            }

            //Suurendan kordi muutujat
            kordi = kordi + 1;
        }

        //Tagastan tulemuset järjendi suurusega mis on vaja.
        return teeLühemaks(tulemus, kordi);
    }

    //Tagastab järgmise veeru
    //Võtab eelmise veeru ja selle et kas liikub vasakult paremale
    private static int arvutaUusVeerg(int eelmineVeerg, boolean vasakultParemale) {
        //Kui liigub vasakult paremale, siis järgmine veerg on eelmine +1, muidu -1,
        if(vasakultParemale) {
            return eelmineVeerg + 1;
        } else {
            return eelmineVeerg - 1;
        }
    }

    //Sarnane eelmisega
    //Tagastab järgmise rea
    //Võtab eelmise rea ja kas liigub alt üles
    private static int arvutaUusRida(int eelmineRida, boolean altÜles) {
        //Kui liigub alt üles siis on järgmine rida hetkel-1, muidu +1
        if(altÜles) {
            return eelmineRida-1;
        } else {
            return eelmineRida+1;
        }
    }

    //Tagastab järjendi antud suurusel.
    //Võtab hetkel järjendi ja viimase indeksi.
    private static int[] teeLühemaks(int[] hetkelArr, int viimaneIndeks) {
        return Arrays.copyOfRange(hetkelArr, 0,viimaneIndeks);
    }

    //Ruutühtedest meetod
    //Tagastab numbri palju nxn suurim maatriks oli.
    //Võtab 2D järjendi
    public static int ruutÜhtedest(boolean[][] a) {
        //Hoiab suurimat arvu
        int suurim = -1;
        //Käib läbi kõikidest ridadest
        for (int rida = 0; rida < a.length; rida++) {
            //Käib läbi kõikidest veergudest
            for (int veerg = 0; veerg < a[rida].length; veerg++) {
                if(!a[rida][veerg]) continue; //Ignoreeri kui on false
                int pikkus; //Ajutine muutujate et salvestada pikkust
                int vasakultÜlevaltParemale = 1; //Kui pikk on vasakultülevalt paremaleülesse.
                int kordi = 0; //Mitu korda on koodi jooksutatud
                for (int i = veerg; i < a[0].length ; i++) {
                    if(a[rida][i]) { //Kas on 1
                        if(kordi != 0) vasakultÜlevaltParemale +=1; //Esimene kord ignoreeritakse
                        kordi += 1;
                    } else {
                        break; //Kui ei ole lõpeta lugemine
                    }
                }

                pikkus = vasakultÜlevaltParemale; //Pikkus on palju oli kokku.
                int vasakultÜlevaltAlla = 1; //Hoiab palju on 1tesi vasakult ülevalt alla

                int viimaneRida = arvutaViimaneRida(rida,pikkus); //Arvutab viimase rea, rida ja pikkuse abil

                if(viimaneRida > a.length-1) {
                    viimaneRida = a.length - 1; //Vajadusel seab rea max maatriksi reaks.
                }
                for (int i = rida; i < viimaneRida; i++) { //Käib kõik read kui viimaseni läbi.
                    if(a[i][veerg]) { //Kui on true
                        vasakultÜlevaltAlla += 1; //Lisab 1
                    } else {
                        break; //Muidu lõpetab lugemise
                    }
                }

                if(vasakultÜlevaltAlla < pikkus) { //Kui on väiksem kui pikkus seab selle uueks pikkuseks
                    pikkus = vasakultÜlevaltAlla;
                    viimaneRida = arvutaViimaneRida(rida, pikkus); //Arvutab uue rea
                }

                int vasakultAltParemale = 1; //Hoiab palju on vasakult alt paremale alla.

                int paremalVeerg = arvutaViimaneVeerg(veerg, pikkus); //Arvutab max veeru

                if(paremalVeerg > a[0].length-1) { //Vajadusel seab max veeruks.
                    paremalVeerg = a[0].length - 1;
                }
                for (int i = veerg; i < paremalVeerg; i++) { //käib kõik veerud kuni max läbi
                    if(a[viimaneRida][i]) { //Kui on true
                        vasakultAltParemale = vasakultAltParemale + 1; //Lisa 1
                    } else {
                        break; //Muidu lõpetab lugemise
                    }
                }
                if(vasakultAltParemale < pikkus) { //Kui on väiksem kui pikkus seab uueks pikkuseks
                    pikkus = vasakultAltParemale;
                    viimaneRida = arvutaViimaneRida(rida, pikkus); //Arvutab uue rea
                    paremalVeerg = arvutaViimaneVeerg(veerg, pikkus); //Arvutab uue veeru

                }

                int paremaltÜlevaltAlla = 1;  //Palju on paremalt ülevalt alla
                for (int i = rida; i < viimaneRida; i++) { //Käib kõik read läbi
                    if(a[i][paremalVeerg]) { //Kui on true
                        paremaltÜlevaltAlla +=1; //Lisa 1
                    } else {
                        break; //Muidu lõpeta lugemine
                    }
                }

                if(paremaltÜlevaltAlla < pikkus) pikkus = paremaltÜlevaltAlla; //Kui väiksem teeb pikkuseks

                if(suurim < pikkus) suurim = pikkus; //Kui suurim on väiksem kui pikkus siis seab uue suurima



            }
        }

        //Tagastab suurima
        return suurim;
    }


    //Meetod testimiseks, teeb kõik bitimaatriksid booleaniteks.
    private static boolean[][] intToBoolean(int[][] a) {
        boolean[][] tulemus = new boolean[a.length][a[0].length];

        for (int rida = 0; rida < a.length; rida++) {
            for (int veerg = 0; veerg < a[rida].length; veerg++) {
                int number = a[rida][veerg];
                if(number == 1) {
                    tulemus[rida][veerg] = true;
                } else {
                    tulemus[rida][veerg] = false;
                }
            }
        }

        return tulemus;
    }

    //Arvutab viimane rida, ehk siis hetkel rida + pikkus - 1 (sest et pikkuse sees hetkene element ka)
    //Tagastab pikkuse
    //Võttab rea + pikkuse
    private static int arvutaViimaneRida(int algusRida, int pikkus) {
        return algusRida+(pikkus-1);
    }

    //Arvutab viimane veeru, ehk siis hetkel veerg + pikkus - 1 (sest et pikkuse sees hetkene element ka)
    //Tagastab pikkuse
    //Võttab veeru + pikkuse
    //TODO: need meetodid saaks üheks teha, aga jätsin hetkel eraldi.
    private static int arvutaViimaneVeerg(int algusVeerg, int pikkus) {
        return algusVeerg+pikkus-1;
    }




}
