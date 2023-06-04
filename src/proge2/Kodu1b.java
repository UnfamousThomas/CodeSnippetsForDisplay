package proge2;

/**
 * @author thomp (04/06/2023)
 */
public class Kodu1b {
    static void ruut(int n) {

        System.out.println("# ".repeat(n - 1) + "#");
        for (int i = 0; i < (n - 2); i++) {
            System.out.println("#" + " ".repeat((n - 2) * 2 + 1) + "#");
        }
        if(n > 1) System.out.println("# ".repeat(n - 1) + "#");
    }

    static void romb(int n) {
        System.out.println(" ".repeat(n - 1) + "#");
        int eelSpace = -1;
        for (int i = n - 2; i >= 0; i--) {
            eelSpace = eelSpace + 2;
            System.out.println(" ".repeat(i) + "#" + " ".repeat(eelSpace) + "#");
        }
        int pealeSpace = 1;
        eelSpace = eelSpace - 2;
        for (int i = n - 2; i >= 1; i--) {
            System.out.println(" ".repeat(pealeSpace) + "#" + " ".repeat(eelSpace) + "#");
            pealeSpace = pealeSpace + 1;
            eelSpace = eelSpace - 2;
        }
        if(n>1) System.out.println(" ".repeat(n - 1) + "#");
    }

    static void telk(int n) {
        int algSpaces = n * 2;
        int keskSpaces = 1;
        System.out.println(" ".repeat(algSpaces) + "#");
        algSpaces = algSpaces - 2;
        for (int i = 1; i < n; i++) {
            System.out.println(" ".repeat(algSpaces) + "#".repeat(i + 1) + " ".repeat(keskSpaces) + "#".repeat(i + 1));
            algSpaces = algSpaces - 2;
            keskSpaces = keskSpaces + 2;
        }

        int vordubKokku = n * 2 + keskSpaces + algSpaces * 2 + 2;
        System.out.println("=".repeat(vordubKokku));
    }

    static void spiraal(int n) {
//algSpaces =
    }

    public static void main(String[] args) {
        romb(1);
    }

}