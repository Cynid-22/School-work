#include <iostream>
#include <iomanip>
// #include <algorithm>

using namespace std;

int main()
{
    string ItemName;
    double Cost, Paid, Tax, TotalCost, Change;
    int padding;

    cout << "What is the item? ";
    getline(cin,ItemName);
    cout << "How much did it cost? ";
    cin >> Cost;
    cout << "How much was paid? ";
    cin >> Paid;

    Tax = Cost * 0.06;
    TotalCost = Cost + Tax;
    Change = Paid - TotalCost;

    padding = max(int(ItemName.length()+1), 15);

    cout << fixed << showpoint << setprecision(2);
    cout << "\nReceipt\n";
    cout << ItemName << ": "   << string(padding - ItemName.length(), ' ') << "$" << Cost;
    cout << "\nTax (6%): "     << string(padding - 8, ' ')  << "$" << Tax;
    cout << "\nTotal Cost: "   << string(padding - 10, ' ') << "$" << TotalCost;
    cout << "\nAmount given: " << string(padding - 12, ' ') << "$" << Paid;
    cout << "\nChange: "       << string(padding - 6, ' ')  << "$" << Change;
    return 0;

}


/*
output:
What is the item? Test Item
How much did it cost? 5.30
How much was paid? 100.00

Receipt
Test Item:       $5.30
Tax (6%):        $0.32
Total Cost:      $5.62
Amount given:    $100.00
Change:          $94.38
*/