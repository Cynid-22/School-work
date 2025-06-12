#include <iostream>
#include <cmath>
#include <iomanip>

using namespace std;

int main()
{
    int size;
    cout << "Enter the matrix size: ";
    cin >> size;

    int matrix[size][size], answer[size][size], padding = log10(size*size)+2, counter=1;

    for (int i=0; i<size; ++i) {
        for (int j=0; j<size; ++j) {
            matrix[i][j] = counter++;
            cout << setw(padding) << matrix[i][j];
        }
        cout << "\n";
    }

    cout << "\n";

    for (int i=0; i<size; ++i) {
        for (int j=0; j<size; ++j) {
            answer[i][j] = matrix[j][i];
            cout << setw(padding) << answer[i][j];
        }
        cout << "\n";
    }

    return 0;
}

/*
Output:
Enter the matrix size: 5
  1  2  3  4  5
  6  7  8  9 10
 11 12 13 14 15
 16 17 18 19 20
 21 22 23 24 25

  1  6 11 16 21
  2  7 12 17 22
  3  8 13 18 23
  4  9 14 19 24
  5 10 15 20 25
*/