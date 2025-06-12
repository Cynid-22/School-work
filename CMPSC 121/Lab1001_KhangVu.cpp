#include <iostream>
#include <cstring>

using namespace std;

int main()
{
    char arr[5][100];
    char answer[5][100];

    for (int i = 0; i < 5; ++i) {
        int j=0;
        string temp;
        cout << "Enter No." << i+1 << " element: ";
        cin >> temp;
        while(!isblank(temp[j])) {
            arr[i][j] = temp[j];
            ++j;
        }
    }

    for (int i = 0; i < 5; ++i)
        for (int j = 0; j < sizeof(arr[i]); ++j) {
            strcpy(&answer[i][j], &arr[4-i][j]);
        }

    cout << "\nThe output: ";
    for (int i = 0; i < 5; ++i)
        cout << "\nEnter No." << i+1 << " element: " << answer[i];

    return 0;
}

/*
Output:
Enter No.1 element: apple
Enter No.2 element: banana
Enter No.3 element: chicken
Enter No.4 element: dragon   
Enter No.5 element: eletricity

The output: 
Enter No.1 element: eletricity
Enter No.2 element: dragon
Enter No.3 element: chicken
Enter No.4 element: banana
Enter No.5 element: apple
*/