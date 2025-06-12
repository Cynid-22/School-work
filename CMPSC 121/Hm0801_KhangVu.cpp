#include <iostream>

using namespace std;

int main()
{
    int size;
    cout << "Enter the size of the sqaure: ";
    cin >> size;

    string array[size][size];

    for (int i=0; i<size; ++i) {
        for (int j=0; j<size; ++j) {
            array[i][j] = "*";
        }
    }

    for (int i=0; i<size; ++i) {
        for (int j=0; j<size; ++j) {
            cout << array[i][j] << " ";
        }
        cout << "\n";
    }

    return 0;
}

/*
Output:
Enter the size of the sqaure: 5
* * * * *
* * * * *
* * * * *
* * * * *
* * * * * 
*/