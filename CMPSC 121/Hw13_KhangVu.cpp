#include <iostream>

using namespace std;

int fibonacci (int n)
{
    if (n>1)
        return fibonacci(n-1) + fibonacci(n-2);
    else
        return 1;
}

int main()
{
    int n;
    cout << "Enter the ith of fibonacci number series: (string with the first fibonacci number) ";
    cin >> n;

    cout << "The fibonacci number is: " << fibonacci(n);

    return 0;
}

/*
Output:
Enter the ith of fibonacci number series: (string with the first fibonacci number) 11
The fibonacci number is: 144
*/