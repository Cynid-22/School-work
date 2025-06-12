#include <iostream>

using namespace std;

void makeChange(int cents, int &dollars, int &quaters, int &dimes, int &nickels, int &pennies)
{
    dollars = cents/100; 
    cents -= dollars*100;

    quaters = cents/25; 
    cents -= quaters*25;

    dimes = cents/10; 
    cents -= dimes*10;

    nickels = cents/5; 
    cents -= nickels*5;
    
    pennies = cents;
}

int main()
{
    int cents, dollars, quarters, dimes, nickels, pennies;
    cout << "How much change to make (in cents): ";
    cin >> cents;
    makeChange(cents, dollars, quarters, dimes, nickels, pennies);
    cout << "\nYour change is:" << endl
         << dollars << " dollar(s)" << endl
         << quarters << " quarter(s)" << endl
         << dimes << " dime(s)" << endl
         << nickels << " nickel(s)" << endl
         << pennies << " pennie(s)" << endl;
#ifdef _WIN32          // _WIN32 is used by Visual C++
#if (_MSC_VER <= 1916) // check if it Visual Studio 2017 or earlier
    system("pause");
#endif
#endif
    return 0;
}

/*
Output:
How much change to make (in cents): 13274

Your change is:
132 dollar(s)
2 quarter(s)
2 dime(s)
0 nickel(s)
4 pennie(s)
Press any key to continue . . . 
*/