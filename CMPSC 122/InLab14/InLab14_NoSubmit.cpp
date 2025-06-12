#include <iostream>
#include <iomanip>

using namespace std;

int main()
{
    string str[9] = {"EXAM", "SHARE", "NOT", "GOOD", "HELP", "TEXT", "AND", "ANY", "BAD"};

    for (int i = 0; i < 9; ++i) {
        int hash_val = 0, j = 0;
        while(str[i][j] != '\0') {
            hash_val += str[i][j] - 'A' + 1;
            ++j;
        }
        cout << left << setw(6) << str[i] << ": " << hash_val%11 << endl;
    }

    return 0;
}

/*
Output:

*/