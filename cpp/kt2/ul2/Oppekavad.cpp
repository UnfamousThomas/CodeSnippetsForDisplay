#include "Oppekavad.h"
#include <iostream>
using namespace std;

void Oppekavad::lisaTudeng(Tudeng tudeng) {
    if(tudeng.getMOppekavaNimi() == "") return;

    for (auto it = this->oppekavad.begin(); it != oppekavad.end(); ++it) {
        if(it->first == tudeng.getMOppekavaNimi()) {
            std::vector<Tudeng> tudengid = *it->second;
            tudengid.push_back(tudeng);
        }
    }
}
void Oppekavad::muudaKeskmist(Tudeng tudeng, float uus_kesk) {
    tudeng.setMKeskHinne(uus_kesk);
}
void Oppekavad::lisaOppekava(Oppekava &oppekava) {
    string nimi = oppekava.getMOppekavaNimi();
    std::shared_ptr<std::vector<Tudeng>> vektor = std::make_shared<std::vector<Tudeng>>(oppekava.getMTudengid());
    this->oppekavad[nimi] = vektor;
}
void Oppekavad::eemaldaOppekavad(Oppekava &oppekava) {
    string nimi = oppekava.getMOppekavaNimi();
    for (auto it = this->oppekavad.begin(); it != oppekavad.end(); ++it) {
        if(it->first == oppekava.getMOppekavaNimi()) {
            oppekavad.erase(it);
            break;
        }
    }
}

void Oppekavad::kuvaEkraanile() {
    for (auto it = this->oppekavad.begin(); it != oppekavad.end(); ++it) {
        cout << it->first << endl;
        std::vector<Tudeng> tudengid = *it->second;
        for (const auto &item: tudengid) {
            cout << item;
        }
    }
}

