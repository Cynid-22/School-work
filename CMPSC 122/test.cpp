#include <iostream>
#include <iomanip>
#include <string>
using namespace std;

int main()
{
    string  item1, item2, item3, item4;
    double price1, price2, price3, price4, total;


    cout << "Thank you for your business!\n";
    cout << endl << endl << endl;
    cout << fixed;
    cout << setprecision(2);
    cout << "Sub-total:" << setw(11) << right   << "$" << endl;
    cout << "+Sales Tax:" << setw(10)  << "$" << endl;
    cout << "-Discount:" << setw(11)   << "$" << endl;
    cout << "+Shipping:" << setw(11)   << "$" << endl;
    cout << "=Total:" << setw(14)      << "$" << endl;

    return 0;
}