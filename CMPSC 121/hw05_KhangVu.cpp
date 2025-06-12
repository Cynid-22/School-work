#include <iostream>
#include <iomanip>

using namespace std;

int main()
{
    int row = 0, column = 0;
    while (true) {
        cout << "Enter the number of rows (1 to 12 inclusive): ";   
        cin >> row;
        if (row >= 1 && row <= 12)
            break;
        else
            cout << "Invalid input, try again!\n";
    }
    while (true) {
        cout << "Enter the number of column (1 to 12 inclusive): ";   
        cin >> column;
        if (column >= 1 && column <= 12)
            break;
        else
            cout << "Invalid input, try again!\n";
    }
    cout << "    ";
    for (int i=1; i<=column; ++i)
        cout << setw(4) << i; 
    cout << "\n";
    for (int i=1; i<=row; ++i) {
        cout << setw(4) << i;
        for (int j=1; j<=column; ++j) {
            cout << setw(4) << i*j;
        }
        cout << "\n";
    }
    return 0;
}

/*
output:
Enter the number of rows (1 to 12 inclusive): 13
Invalid input, try again!
Enter the number of rows (1 to 12 inclusive): 0
Invalid input, try again!
Enter the number of rows (1 to 12 inclusive): 12
Enter the number of column (1 to 12 inclusive): 11
       1   2   3   4   5   6   7   8   9  10  11
   1   1   2   3   4   5   6   7   8   9  10  11
   2   2   4   6   8  10  12  14  16  18  20  22
   3   3   6   9  12  15  18  21  24  27  30  33
   4   4   8  12  16  20  24  28  32  36  40  44
   5   5  10  15  20  25  30  35  40  45  50  55
   6   6  12  18  24  30  36  42  48  54  60  66
   7   7  14  21  28  35  42  49  56  63  70  77
   8   8  16  24  32  40  48  56  64  72  80  88
   9   9  18  27  36  45  54  63  72  81  90  99
  10  10  20  30  40  50  60  70  80  90 100 110
  11  11  22  33  44  55  66  77  88  99 110 121
  12  12  24  36  48  60  72  84  96 108 120 132
*/