#include <iostream>
#include <iomanip>
#include <cmath>

using namespace std;

int main()
{
    int matrixSize;
    cout << "Input matrix size: ";
    cin >> matrixSize;
    for (int i=1; i<=matrixSize*matrixSize; i+= matrixSize) {
        for (int j=i; j<i + matrixSize; ++j) {
            cout << setw(log10(matrixSize*matrixSize)+2) << j;
        }
        cout << "\n";
    }
    cout << "\n";
    for (int i=1; i<=matrixSize; ++i) {
        for (int j=0; j<matrixSize; ++j) {
            cout << setw(log10(matrixSize*matrixSize)+2) << i + j*matrixSize;
        }
        cout << "\n";
    }
    return 0;
}

/*
output:

Input matrix size: 7
  1  2  3  4  5  6  7
  8  9 10 11 12 13 14
 15 16 17 18 19 20 21
 22 23 24 25 26 27 28
 29 30 31 32 33 34 35
 36 37 38 39 40 41 42
 43 44 45 46 47 48 49

  1  8 15 22 29 36 43
  2  9 16 23 30 37 44
  3 10 17 24 31 38 45
  4 11 18 25 32 39 46
  5 12 19 26 33 40 47
  6 13 20 27 34 41 48
  7 14 21 28 35 42 49
*/