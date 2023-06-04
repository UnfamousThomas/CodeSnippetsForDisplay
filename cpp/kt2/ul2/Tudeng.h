#include <string>
#include <ostream>

using namespace std;

class Tudeng {
private:
    float m_kesk_hinne;
    string m_oppekava_nimi;
public:
    Tudeng(string nimi, float m_kesk, string m_oppekava) {
        this->m_nimi=nimi;
        this->m_kesk_hinne=m_kesk;
        this->m_oppekava_nimi=m_oppekava;
    }
    string m_nimi;
    const string &getMNimi() const;

    const string &getMOppekavaNimi() const;

    void setMKeskHinne(float mKeskHinne);
    void setMOppekavaNimi(const string &mOppekavaNimi);

    friend ostream &operator<<(ostream &os, const Tudeng &tudeng);
};
