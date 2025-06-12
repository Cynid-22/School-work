#include <iostream>
// #include <string>    

using namespace std;

int main ()
{
    string FullName, Address1, Address2, PhoneNumber;

    cout << "Please type in your full name: ";
    getline(cin, FullName);
    cout << "Please type the 1st line of your address: ";
    getline(cin, Address1);
    cout << "Please type the 2nd line of your address: ";
    getline(cin, Address2);
    cout << "Please type in your phone number: ";
    getline(cin, PhoneNumber);
    cout << "********************************\n";
    
    cout << "Programmer:  ";
    cout << FullName << "\n";
    cout << "             " << Address1 << "\n";
    cout << "             " << Address2 << "\n\n";
    cout << " Telephone:  " << PhoneNumber;

    return 0;
}

/*
output:
Please type in your full name: Khang N. Vu
Please type the 1st line of your address: 723 Null Hall 
Please type the 2nd line of your address: Erie, Pa 16565
Please type in your phone number: 123-456-7890
********************************
Programmer:  Khang N. Vu
             723 Null Hall
             Erie, Pa 16565

 Telephone:  123-456-7890
*/