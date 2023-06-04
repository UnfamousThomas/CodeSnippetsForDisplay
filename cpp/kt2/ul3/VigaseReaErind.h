#include <exception>
#include <string>

using namespace std;
class VigaseReaErind : public std::exception {
public:
    VigaseReaErind(string sõnum) : m_sõnum{sõnum}{}
    string getSõnum() {
        return m_sõnum;
    }
private:
    string m_sõnum;
};