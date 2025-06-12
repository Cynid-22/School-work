#include <iostream>

using namespace std;

int* reverse (int num[], int n)
{
    int *changed = new int[n]; 
    for (int i = 0; i < n; ++i)
        changed[n-1-i] = num[i];

    return changed; // This is for my note. Return the address of array change
}

int main()
{
    int arr[] = {1, 2, 4, 8}, size = 4;
    int *ptr; // assign a box to hold pointer

    ptr = reverse(arr, size); // address of num[]

    cout << "The output: ";
    for (int i = 0; i < size; ++i)
        cout << ptr[i] << " ";

    delete[] ptr;
    return 0;
}

/*
Output:
The output: 8 4 2 1
*/