#include "MinuMasiiv.h"
#include <bits/stdc++.h>

template <typename T, bool kasKasvavalt>
void MinuMasiiv<T, kasKasvavalt>::sorteeri() {
    std::sort(this->elemendid);
    if(!kasKasvavalt) {
        std::reverse(this->elemendid);
    }
}
template <typename T, bool kasKasvavalt>
void MinuMasiiv<T,kasKasvavalt>::lisaElement(T element){
    this->elemendid.push_back(element);
}
template <typename T, bool kasKasvavalt>
bool MinuMasiiv<T,kasKasvavalt>::onMasiivis(T element){
    for (const auto &item: this->elemendid) {
        if(item == element) return true;
    }
    return false;
}
template <typename T, bool kasKasvavalt>
void MinuMasiiv<T,kasKasvavalt>::lisaTeisest(MinuMasiiv objektid){
    for (const auto &item: objektid.elemendid) {
        this->elemendid.push_back(item);
    }
}