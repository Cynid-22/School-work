#include <iostream>
#include <cmath>

using namespace std;

int main() 
{
    int Sum=0, Product=1, Input, temp;
    int AmountOfDigit;
    cout << "Please enter a four-digit number: ";
    cin >> Input;
    AmountOfDigit = log10(Input) + 1;
    cout << "Sum of the digits: ";
    for (int i=0; i<AmountOfDigit; ++i) {
        cout << int(Input/pow(10,(AmountOfDigit-i-1)))%10;
        cout << (AmountOfDigit-1-i ? " + " : " = ");
        Sum += int(Input/pow(10,(AmountOfDigit-i-1)))%10;
    }
    cout << Sum;
    
    cout << "\nMultiplication of the digits: ";
    for (int i=0; i<AmountOfDigit; ++i) {
        cout << int(Input/pow(10,(AmountOfDigit-i-1)))%10;
        cout << (AmountOfDigit-1-i ? " * " : " = ");
        Product *= int(Input/pow(10,(AmountOfDigit-i-1)))%10;
    }

    cout << Product;
    return 0;
}

/*
output:
Please enter a four-digit number: 5789
Sum of the digits: 5 + 7 + 8 + 9 = 29
Multiplication of the digits: 5 * 7 * 8 * 9 = 2520
*/