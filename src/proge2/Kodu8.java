package proge2;

/**
 * @author thomp (04/06/2023)
 */
public class Kodu8 {

    /**
     * eemaldaKordusgrupid meetod mis eemaldab sõnest kordusgrupid
     *
     * @param a Mis sõnest eemaldada grupid
     * @return Ilma gruppideta sõne
     */
    public static String eemaldaKordusgrupid(String a) {
        return eemaldaKordusgrupidRek(a, 0, new StringBuilder());
    }

    /**
     * Kordusgruppide eemaldamise (v mitte lisamise) rekursiooni abimeetod
     *
     * @param src           Kust kordusgruppe vaatame
     * @param hetkelIndeks  Mis indeksil hetkel tegeleme
     * @param hetkel        StringBuilder kuhu lisame jooksvalt strnge
     * @return src ilma kordusteta
     */
    public static String eemaldaKordusgrupidRek(String src, int hetkelIndeks, StringBuilder hetkel) {
        if(src.length() == 0) return src;
        if (!kasEelmineVJargmineKordus(src, hetkelIndeks)) {
            hetkel.append(src.charAt(hetkelIndeks));
        }

        if (hetkelIndeks == src.length() - 1) {
            System.out.println(hetkel.toString());
            if (src.length() != hetkel.length()) return eemaldaKordusgrupidRek(hetkel.toString(), 0, new StringBuilder());
            else return hetkel.toString();
        } else return eemaldaKordusgrupidRek(src, hetkelIndeks + 1, hetkel);

    }

    /**
     * Abimeetod et kontrollida kas eelmine v viimane on praeguse kordus
     *
     * @param src    Sõne mida kontrollida
     * @param indeks Indeks mida vaadata
     * @return Tõeväärtus kas eelmine v järgmine on prgusega võrdne
     */
    public static boolean kasEelmineVJargmineKordus(String src, int indeks) {
        if (indeks < src.length() - 1 && src.charAt(indeks) == src.charAt(indeks + 1)) {
            return true;
        }
        return indeks > 0 && src.charAt(indeks) == src.charAt(indeks - 1);
    }

    /**
     * Kaashääliku algne meetod
     *
     * @param a Sõne mida kontrollime
     * @return Mitu kaashäälikut leidsime
     */
    public static int kaashäälikuÜhendeid(String a) {
        return kaashäälikuÜhendeidRek(a, 0, 0, false);
    }

    /**
     * Kaashääliku ühendite countimise rekursiooni abimeetod
     *
     * @param src          Algne sõne kust countime kaashäälikuid
     * @param jooksevCount Mitu ühendit oleme leidnud
     * @param indeks       Hetkel indeks
     * @param lisatud      Kas hetkel käiv kaashäälikute rida on lisatud counti
     * @return Mitu kaashäälikut leidsime sõnest
     */
    public static int kaashäälikuÜhendeidRek(String src, int jooksevCount, int indeks, boolean lisatud) {
        if (indeks == src.length()) return jooksevCount;

        if (kasOnKaasHäälik(src.charAt(indeks))) {
            if (!lisatud) {
                if (indeks > 0 && kasOnKaasHäälik(src.charAt(indeks - 1)) && (src.toLowerCase().charAt(indeks) != src.toLowerCase().charAt(indeks-1))) {
                    jooksevCount += 1;
                    lisatud = true;
                } else if (indeks < src.length() - 1 && kasOnKaasHäälik(src.charAt(indeks + 1)) && (src.toLowerCase().charAt(indeks) != src.toLowerCase().charAt(indeks+1))) {
                    jooksevCount += 1;
                    lisatud = true;
                }
            }
        } else lisatud = false;

        return kaashäälikuÜhendeidRek(src, jooksevCount, indeks + 1, lisatud);
    }

    /**
     * Kontrollib kas sisestatud char on kaashäälik
     *
     * @param sisse Char mida kontrollida
     * @return Tõeväärtus, true = on kaashäälik
     */
    public static boolean kasOnKaasHäälik(Character sisse) {
        String haalikud = "BbCcDdFfGgHhJjKkLlMmNnPpQqRrSsŠšZzŽžTtVvWwXxYy";
        return haalikud.indexOf(sisse) != -1;
    }

    //BOONUSÜLESANNE
    public static String[] kõikTulemused(String s){
        throw new UnsupportedOperationException("Kirjuta oma lahendus selle rea asemele. Juhul kui ülesanne jääb lahendamata jäta see rida alles.");
    }


    public static void main(String[] args) {
        System.out.println(eemaldaKordusgrupid("ccbbaaaaaaaaccddbbddccbbbbbbddaabbaaaddaaaddbbdddddbbbcc"));
        System.out.println(eemaldaKordusgrupid("abbbaaacabbbbaccc"));
        System.out.println(kaashäälikuÜhendeid("Mait Malmsten kehastus kortsnapühkijaks."));
        System.out.println(kaashäälikuÜhendeid("MaitMalmstenkehastuskorstnapühkijaks"));
    }
}
