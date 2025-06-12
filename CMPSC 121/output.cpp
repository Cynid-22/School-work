#include <iostream>

using namespace std;

int main() 
{
    string first_name;
    cout << "Please enter your first name: ";
    cin >> first_name;
    cout << "Hello there, " << first_name << "!" << endl;
    cout << "Different ways of outputs:" << endl << endl;
    cout << first_name << first_name << first_name << endl;
    cout << first_name << "  " << first_name << "  " << first_name;
    cout << endl << endl;
    cout << first_name << "\n";
    cout << first_name << "\n";
    cout << first_name << "\n";
    return 0;
}

/*
Answer 1: The "cin >> first_name;" collects the input from the user through the keyboard and store it in the variable "first_name"
Answer 2: The "cout << endl << endl;" ends the line before it and creates an empty line

Output:
Please enter your first name: Khang
Hello there, Khang!
Different ways of outputs:

KhangKhangKhang
Khang  Khang  Khang

Khang
Khang
Khang
*/