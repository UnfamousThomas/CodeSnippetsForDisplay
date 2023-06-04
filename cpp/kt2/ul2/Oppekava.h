#include <string>
#include "Tudeng.h"
#include <vector>

using namespace std;

class Oppekava {
private:
    string m_oppekava_nimi;
    vector<Tudeng> m_tudengid;
public:
    Oppekava(string nimi) {
        this->m_oppekava_nimi = nimi;
    }
    const string &getMOppekavaNimi() const;

    const vector<Tudeng> &getMTudengid() const;

    void lisaOppekavale(Tudeng tudeng);
    void eemaldaOppekavalt(Tudeng tudeng);
    void prindiInfo();
};
