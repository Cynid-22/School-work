#include <iostream>

using namespace std;

int main()
{
    int treeHeight, treeStump;
    cout << "Enter number of rows: ";
    cin >> treeHeight;
    for (int i=1; i<=treeHeight; ++i) {
        for (int j=0; j<i; ++j)
            cout << "* ";
        cout << "\n";
    }
    treeStump = treeHeight/2;
    for (int i=0; i<treeStump; ++i)
        cout << "*\n";
    return 0;
}

/*
output:
Enter number of rows: 6
*
* *
* * *
* * * * 
* * * * *
* * * * * *
*
*
*
*/