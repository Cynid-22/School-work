#include <iostream>

using namespace std;

int factorial (int n)
{
    if (n>=1) 
        return n * factorial(n-1);
    else
        return 1;
}

int main()
{
    int n, fac = 0;
    cout << "Enter a number: ";
    cin >> n;
    fac = factorial(n);
    cout << "The factorial: " << fac << endl;
    return 0;
}

/*
Output:
Enter a number: 10
The factorial: 3628800
*/