#include <iostream>
#include <iomanip>
#include <cmath>

using namespace std;

int main()
{
    int row = 13, col = 13;
    int padding = log10(row*col)+2;

    cout << setw(padding) << " ";
    for (int i = 1; i <= col; i++)
    {
        cout << setw(padding) << i;
    }
    cout << "\n";

    for (int i = 1; i <= row; ++i) {
        cout << setw(padding) << i;
        for (int j = i; j <= i*col; j+=i) {
            cout << setw(padding) << j;
        }
        cout << "\n";
    }
    

    return 0;
}

/*
Output:

*/