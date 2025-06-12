#include <iostream>
#include <string>

using namespace std;

int main()
{
    string name, uni;
    string answer;

    cout << "Enter your name: ";
    cin >> name;
    cout << "Enter your university: ";
    cin >> uni;

    answer = "Hello " + name + ", Welcome to " + uni;

    cout << "\nThe output: \n";
    cout << answer;

    return 0;
}

/*
Output:
Enter your name: Khang
Enter your university: PSB

The output:
Hello Khang, Welcome to PSB
*/