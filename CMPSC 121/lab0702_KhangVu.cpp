#include <iostream>

using namespace std;

void swap(int &a, int &b)
{
    int temp;
    temp = a;
    a = b;
    b = temp;
}

int main()
{
    int a, b, c;
    cout << "Enter the 1st number: ";
    cin >> a;
    cout << "Enter the 2nd number: ";
    cin >> b;
    cout << "Enter the 3rd number: ";
    cin >> c;

    if (a<b)
        swap(a,b);
    if (b<c)
        swap(b,c);
    if (a<b)
        swap(a,b);

    cout << "\nThe num (from biggest to smallest): " << a << " " << b << " " << c;

    return 0;
}

/*
Output:
Enter the 1st number: 4
Enter the 2nd number: 10
Enter the 3rd number: 7

The num (from biggest to smallest): 10 7 4
*/