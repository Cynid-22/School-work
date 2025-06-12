// driver for project 5
#include <iostream>
using namespace std;

int CountDigits(int n){
    if (n / 10 == 0)
        return 1;
    else
        return 1 + CountDigits(n/10);
}

void TestCountDigits(int n) {
    cout << "The number " << n << " has " << CountDigits(n) << " digit(s)" << endl;
}

int main()
{
    TestCountDigits(12345);
    TestCountDigits(3);
    TestCountDigits(857);
    TestCountDigits(1985);

    return 0;
}

/*
Output:

The number 12345 has 5 digit(s)
The number 3 has 1 digit(s)
The number 857 has 3 digit(s)
The number 1985 has 4 digit(s)
*/

