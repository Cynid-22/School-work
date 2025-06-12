#include <iostream>
#include <iomanip>
#include "timer.h"

using namespace std;

long double factorial(int n) {
    long double result = 1;
    for(int i = 2; i <= n; ++i) {
        result *= i;
    }
    return result;
}

int main()
{
    Timer t;
    
    char again = 'y';
    int up, right;
    long double answer;
    while (again == 'y' || again == 'Y') {
        cout << "How many points north of A is B? ";
        cin >> up;
        cout << "How many points east of A is B? ";
        cin >> right;
        t.start();
        answer = factorial(up + right) / (factorial(up) * factorial(right));
        t.stop();
        cout << fixed << setprecision(0) << "There are " << answer << " northeast paths between A and B." << endl;
        cout << setprecision(3);
        t.show();

        cout << "\nEnter y or Y to continue the next example or any other letter to exit: ";
        cin >> again;
    }


    return 0;
}

/*
Output:

How many points north of A is B? 2
How many points east of A is B? 3
There are 10 northeast paths between A and B.
  Process Timer
  -------------------------------
  Elapsed Time: 0.001s

Enter y or Y to continue the next example or any other letter to exit: y
How many points north of A is B? 12
How many points east of A is B? 14
There are 9657700 northeast paths between A and B.
  Process Timer
  -------------------------------
  Elapsed Time: 0.001s

Enter y or Y to continue the next example or any other letter to exit: y
How many points north of A is B? 16
How many points east of A is B? 16
There are 601080390 northeast paths between A and B.
  Process Timer
  -------------------------------
  Elapsed Time: 0.001s

Enter y or Y to continue the next example or any other letter to exit: y
How many points north of A is B? 50
How many points east of A is B? 50
There are 100891344545564193309981671424 northeast paths between A and B.
  Process Timer
  -------------------------------
  Elapsed Time: 0.001s

Enter y or Y to continue the next example or any other letter to exit: n
*/