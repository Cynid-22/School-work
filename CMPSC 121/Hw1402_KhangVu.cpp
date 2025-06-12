#include <iostream>

using namespace std;

int sum (int n)
{
    if (n <= 1)
        return n;
    else    
        return n + sum(n-1);
}

int main()
{
    int n, answer;
    cout << "Enter a number: ";
    cin >> n;
    answer = sum(n);
    cout << endl << "The sum: " << answer;

    return 0;
}

/*
Output:
Enter a number: 50

The sum: 1275
*/