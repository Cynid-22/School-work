#include <iostream>

using namespace std;

int main() 
{
    double Sum = 0;
    double UserAnswer;
    for (int i=1; i<=5; i++) {
        cout << "Please enter number " << i << ": ";
        cin >> UserAnswer;
        Sum += UserAnswer;
    }
    double Average = Sum/5;
    cout << "\nThe sum of the 5 numbers = " << Sum << "\n";
    cout << "The average of the 5 numbers = " << Average;

    return 0;
}

/*
output:
Please enter number 1: 3
Please enter number 2: 7
Please enter number 3: 12
Please enter number 4: 73
Please enter number 5: 12.5

The sum of the 5 numbers = 107.5
The average of the 5 numbers = 21.5
*/