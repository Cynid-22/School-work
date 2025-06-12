#include <iostream>

using namespace std;

int main() 
{
    int number;
    double total;

    cout << "Today is a great day for Lab";
    cout << endl;
    cout << "Let's start off by typing a numer of your choice" << endl;
    cin >> number;

    total = number * 2.0;
    cout << total << " is twice the number you typed" << endl;

    return 0;
}

/*
output:
Today is a great day for Lab
Let's start off by typing a numer of your choice
2
4 is twice the number you typed
*/