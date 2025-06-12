#include <iostream>

using namespace std;

int main()
{
    const int SIZE = 3;
    int a[SIZE][SIZE] = {{1,2,3},
                        {4,5,6},
                        {7,8,9}};
    int b[SIZE][SIZE] = {{1,0,0},
                        {0,1,0},
                        {0,0,1}};
    int answer[SIZE][SIZE];


    for (int i=0; i<SIZE; ++i) {
        for (int j=0; j<SIZE; ++j) {
            answer[i][j] = 0;
            for (int k=0; k<SIZE; ++k)
                answer[i][j] += a[i][k] * b[k][j];
        }
    }

    for (int i=0; i<SIZE; ++i) {
            for (int j=0; j<SIZE; ++j)
                cout << answer[i][j] << " ";
            cout << "\n";
    }
    return 0;
}

/*
Output:
1 2 3 
4 5 6 
7 8 9
*/