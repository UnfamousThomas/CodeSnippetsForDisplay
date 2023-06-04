#include <string>
#include <fstream>
#include <iostream>
#include "VigaseSumboliErind.h"
#include "VigaseReaErind.h"
#include <map>
#include <list>

using namespace std;
int vabuKohti(string failinimi) {
    ifstream sisend(failinimi);
    int vabu = 0;
    std::map<int, std::list<int>> vabadKohad;
    if(!sisend.good()) {
        //fail ilmselt ei eksisteeri
        throw runtime_error("Faili nimega: " + failinimi + " ei eksisteeri.");
    }
    int rida = 1;
    string line;
    int mituEsimeses;
    while (std::getline(sisend, line)) {
        int mituSelles;
        int mitmes = 1;
        for(char c: line) {
            if(c != 'R' && c != 'V' && c != ' ') throw VigaseSümboliErind(c + " ei ole lubatud sümbol!");
            if(c == 'R' || c == 'V') {
                mituSelles += 1;
            }
            if(c == 'V') {
                vabadKohad[rida].push_back(mitmes);
                vabu += 1;
            }
            mitmes += 1;
        }
        if(rida == 1) {
            mituEsimeses = mituSelles;
        } else {
            try {
                if (mituSelles > mituEsimeses) throw VigaseReaErind("Selles reas on erinev arv kohti kui esimeses");
            } catch (VigaseReaErind e) {
                cout << e.getSõnum();
                continue;
            }

        }

        rida += 1;
    }

    cout << "Bussis on vabad järgmised kohad: " << endl;
    for (const auto &item: vabadKohad) {
        cout << "rida " << item.first;
        for (const auto &item2: item.second) {
            cout << " koht " << item2;
        }
        cout << endl;
    }
    cout << "Bussis on " << vabu << " kohti";
    return vabu;
}

int main() {
    vabuKohti("buss.txt"); //Ei jõua testida, aga enam vähem peaks loogika okei olema
    return 0;
}
