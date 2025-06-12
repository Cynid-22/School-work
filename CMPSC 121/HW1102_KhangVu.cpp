#include <iostream>

using namespace std;

void swap (int num[], int i, int j) 
{
    int temp = num[i];
    num[i] = num[j];
    num[j] = temp;
}

void bubbleSort(int num[], int size)
{
    for (int i = 0; i < size; ++i) {
        for (int j = 0; j < size-1; ++j) {
            if (num[j]>num[j+1]) {
                swap(num[j], num[j+1]);
            }
        }
    }
}

int main()
{
    int arr[3], length = 3;
    bool not_finished = true;

    cout << "Enter the elements in the array: \n";
    for (int i = 0; i < length; ++i) {
        cin >> arr[i];
    }

    bubbleSort(arr, length);

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