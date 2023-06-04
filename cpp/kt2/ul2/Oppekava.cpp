#include "Oppekava.h"
#include <iostream>
void Oppekava::lisaOppekavale(Tudeng tudeng) {
    m_tudengid.push_back(tudeng);
}
void Oppekava::eemaldaOppekavalt(Tudeng tudeng) {
    for (auto it = m_tudengid.begin(); it != m_tudengid.end(); ++it) {
        if(it->getMNimi() == tudeng.getMNimi()) {
            m_tudengid.erase(it);
            break;
        }
    }
}
void Oppekava::prindiInfo() {
    std::cout << m_oppekava_nimi << endl;
    for (const auto &item: m_tudengid) {
        std::cout << item.getMNimi() << endl;
    }
    std::cout << "------";
}

const string &Oppekava::getMOppekavaNimi() const {
    return m_oppekava_nimi;
}

const vector<Tudeng> &Oppekava::getMTudengid() const {
    return m_tudengid;
}
