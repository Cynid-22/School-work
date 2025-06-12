#include <iostream>

using namespace std;

int sumByRecursion(int x)
{
    if (x <= 1)
        return x;
    else
        return x + sumByRecursion(x-1);
}

int main()
{
    cout << sumByRecursion(5); // sum from 1 to 5

    return 0;
}

/*
Output:

*/