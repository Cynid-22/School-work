#include <iostream>

using namespace std;

void sumbyref(int &sum, int x)
{
    for (int i=1; i<=x; ++i) {
        sum += i;
    }

}

int main()
{
    int sum = 0, number = 0;
    cout << "Enter a number: ";
    cin >> number;

    sumbyref(sum, number);

    cout << "\nThe cumulative sum: " << sum;
    return 0;
}

/*
Output:
Enter a number: 10

The cumulative sum: 55
*/