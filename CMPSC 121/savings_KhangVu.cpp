#include <iostream>
#include <cmath>

using namespace std;

int main() 
{
    double OriginalAmount, InterestRate, Output;
    int CompoundNumber;
    cout << "Please enter the principal amount: ";
    cin >> OriginalAmount;
    cout << "Please enter the interest rate: ";
    cin >> InterestRate;
    cout << "Please enter the number of times compounded: ";
    cin >> CompoundNumber;


    Output = OriginalAmount * pow((1 + ((InterestRate/100)/CompoundNumber)),CompoundNumber);

    cout.precision(2);
    cout << showpoint << fixed;
    cout << "\nInterest Rate:     " << InterestRate << "%";
    cout << noshowpoint;
    cout << "\nTimes compounded:  " << CompoundNumber;
    cout << showpoint;
    cout << "\nPrincipal:         $" << OriginalAmount;
    cout << "\nInterest:          $" << Output - OriginalAmount;
    cout << "\nAmount in Savings: $" << Output;
    return 0;
}

/*
Output:
Please enter the principal amount: 1000.00
Please enter the interest rate: 4.25
Please enter the number of times compounded: 12

Interest Rate:     4.25%
Times compounded:  12
Principal:         $1000.00
Interest:          $43.34
Amount in Savings: $1043.34
*/