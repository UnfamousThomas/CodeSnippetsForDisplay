#include "Tudeng.h"

void Tudeng::setMKeskHinne(float mKeskHinne) {
    m_kesk_hinne = mKeskHinne;
}

void Tudeng::setMOppekavaNimi(const string &mOppekavaNimi) {
    m_oppekava_nimi = mOppekavaNimi;
}

ostream &operator<<(ostream &os, const Tudeng &tudeng) {
    os << "m_nimi: " << tudeng.m_nimi << " m_kesk_hinne: " << tudeng.m_kesk_hinne << " m_oppekava_nimi: "
       << tudeng.m_oppekava_nimi;
    return os;
}

const string &Tudeng::getMNimi() const {
    return m_nimi;
}

const string &Tudeng::getMOppekavaNimi() const {
    return m_oppekava_nimi;
}
