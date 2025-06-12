#include <iostream>

using namespace std;

long long fac (long long n)
{
    if (n <= 1)
        return 1;
    else
        return n * fac(n-1);
}

int main()
{
    int n, answer;
    cout << "Enter a number: ";
    cin >> n;
    answer = fac(n);
    cout << "The factorial: " << answer;
    return 0;
}

/*
Output:
Enter a number: 10
The factorial: 3628800
*/