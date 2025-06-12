#include <iostream>
#include <iomanip>

using namespace std;

int main() 
{
    string Item[4];
    double Price[4], Sum = 0;
    cout << "Enter names of 4 one-word (maximum 10 letters) items to purchase: \n";
    for (int i=0; i<4; ++i)
        cin >> Item[i];
    cout << "Enter their prices in US Dollars (<= $1,000): \n";
    for (int i=0; i<4; ++i) {
        cin >> Price[i];
        Sum += Price[i];
    }
    
    cout << "++------------ ----------++\n";
    cout << "|Receipt                  |\n";
    cout << "++------------ ----------++\n";
    cout.precision(2);
    cout << showpoint << fixed;
    for (int i=0; i<4; ++i){
        cout << "|";
        cout << setw(13) << left << string(Item[i]) + "|$";
        cout << setw(12) << right << Price[i] << "|\n";
    }
    cout << "++------------ ----------++\n";
    cout << setw(13) << left << "|TOTAL: $";
    cout << setw(13) << right << Sum << "|\n";
    cout << "++------------ ----------++\n";
    return 0;
}

/*
Output:
Enter names of 4 one-word (maximum 10 letters) items to purchase: 
Yogurt Apple Butter Bread
Enter their prices in US Dollars (<= $1,000):
0.75
8.00
12.99
2.85
++------------ ----------++
|Receipt                  |
++------------ ----------++
|Yogurt|$             0.75|
|Apple|$              8.00|
|Butter|$            12.99|
|Bread|$              2.85|
++------------ ----------++
|TOTAL: $            24.59|
++------------ ----------++

*/