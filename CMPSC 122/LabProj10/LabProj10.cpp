#include <iostream>
#include "timer.h"

using namespace std;

int PathCounter (int up, int right)
{
    if (up == 0 || right == 0)
        return 1;
    
    return (PathCounter(up-1, right) + PathCounter(up, right-1));
}

int main()
{
    Timer t;
    
    char again = 'y';
    int up, right, answer;
    while (again == 'y' || again == 'Y') {
        cout << "How many points north of A is B? ";
        cin >> up;
        cout << "How many points east of A is B? ";
        cin >> right;
        t.start();
        answer = PathCounter(up, right);
        t.stop();
        cout << "There are " << answer << " northeast paths between A and B." << endl;
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
  Elapsed Time: 0.024s

Enter y or Y to continue the next example or any other letter to exit: y   
How many points north of A is B? 16
There are 601080390 northeast paths between A and B.
  Process Timer
  -------------------------------
  Elapsed Time: 1.403s

Enter y or Y to continue the next example or any other letter to exit: n
*/