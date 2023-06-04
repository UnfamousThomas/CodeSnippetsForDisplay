#include <exception>
#include <string>

using namespace std;
class VigaseSümboliErind : public std::exception {
public:
    VigaseSümboliErind(string sõnum) : m_sõnum{sõnum}{}
    string getSõnum() {
        return m_sõnum;
    }
private:
    string m_sõnum;
};