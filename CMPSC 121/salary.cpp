#include <iostream>

using namespace std;

int main()
{
    string name;
    int experience, tenure, salary;

    cout << "What is the person's name: ";
    getline(cin, name);
    cout << "How many years of experience do they have? ";
    cin >> experience;
    cout << "How long (in years) is their tenure: ";
    cin >> tenure;
    salary = 2000*experience + 1000*tenure + 50000;

    cout << "\n" << name << "'s salary is $" << salary;
    return 0;
}


/*
output:
What is the person's name: Khang Vu
How many years of experience do they have? 12
How long (in years) is their tenure: 7

Khang Vu's salary is $81000
*/