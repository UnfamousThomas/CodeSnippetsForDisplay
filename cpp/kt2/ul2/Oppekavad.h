#include <map>
#include <string>
#include <vector>
#include "Tudeng.h"
#include <memory>
#include "Oppekava.h"

using namespace std;

class Oppekavad {

private:
    std::map<string, shared_ptr<vector<Tudeng>>> oppekavad;

public:
    void lisaTudeng(Tudeng tudeng);
    void muudaKeskmist(Tudeng tudeng, float uus_kesk);
    void eemaldaOppekavad(Oppekava& oppekava);
    void lisaOppekava(Oppekava& oppekava);
    void kuvaEkraanile();
};

