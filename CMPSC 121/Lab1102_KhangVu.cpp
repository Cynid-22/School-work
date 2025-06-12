#include <iostream>

using namespace std;

void swap (int *a, int *b) 
{
    int temp = *a;
    *a = *b;
    *b = temp;
}

int main()
{
    int arr[3], length=3;

    cout << "Enter the elements in the array: \n";
    for (int i = 0; i < length; ++i) {
        cin >> arr[i];
    }

    for (int i = 0; i < length; ++i) {
        for (int j = 0; j < length-1; ++j) {
            if (arr[j]>arr[j+1]) {
                swap(arr[j], arr[j+1]);
            }
        }
    }

    for (int i = 0; i < length; ++i) {
        cout << arr[i] << " ";
    }

    return 0;
}

/*
Output:
Enter the elements in the array: 
2
1
3
1 2 3
*/