#include <iostream>
#include <ctime>
#include <cstdlib>

using namespace std;

int main()
{
    srand(time(0));
    int diceA, diceB;

    diceA = rand()%6 + 1;
    diceB = rand()%6 + 1;

    cout << "No. 1 of rolling a dice: " << diceA << endl;
    cout << "No. 2 of rolling a dice: " << diceB << endl;

    return 0;
}

/*
Output:
No. 1 of rolling a dice: 3
No. 2 of rolling a dice: 5
*/