#include <iostream>

using namespace std;

long double fibonacci(int n)
{
    long double F[100000];
    F[1] = 1; F[2] = 1;
    for (int i = 3; i <= n; ++i) {
        F[i] = F[i-2] + F[i-1];
    }
    return F[n];
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