#include "Oppekava.h"
#include "Oppekavad.h"
#include "Tudeng.h"

int main() {
    Tudeng tudeng2 = Tudeng("Peeter", 2.0, "Majandus");
    Oppekava oppekava = Oppekava("Majandus");
    Oppekavad oppekavad = Oppekavad();
    oppekavad.lisaOppekava(oppekava);
    oppekavad.lisaTudeng(tudeng2); //Rohkem ei jõua kahjuks, aga vähemalt loogika peaks okei olema.
    return 1;
}