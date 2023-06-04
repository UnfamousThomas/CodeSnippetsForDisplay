#include <vector>
#include <ostream>

template <typename T, bool kasKasvavalt>

class MinuMasiiv {
private:
    std::vector<T> elemendid;

public:
    void sorteeri();
    void lisaElement(T element);
    bool onMasiivis(T element);
    void lisaTeisest(MinuMasiiv objektid);

    friend std::ostream &operator<<(std::ostream &os, const MinuMasiiv &masiiv) {
        os << "elemendid: " << masiiv.elemendid;
        return os;
    }

    T& operator[](const int i) {
        return elemendid.at(i);
    }
};

