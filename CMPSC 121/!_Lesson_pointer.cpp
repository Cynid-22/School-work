#include <iostream>

using namespace std;

int main()
{
    /*
    - Use to point directly to memory address (physical address) [0123456789abcdef - hex]
    */
    int x=10;
    int *ptr = NULL;

    ptr = &x;
    cout << ptr << " " << *ptr << endl; //ptr -> address of x, *ptr -> x value

    *ptr += 3; // this acts as a normal varible, changes gets applied to y
    cout << ptr << " " << *ptr << endl;

    cout << endl;

    int arr[5] = {1, 2, 3, 4, 5};
    for (int i = 0; i < 5; ++i) {
        cout << arr[i] << " "; // 1
    }
    cout << endl;
    for (int i = 0; i < 5; ++i) {
        cout << *arr+i << " "; // 2
    }
    // 1 & 2 does the same thing
    cout << endl;
    int *nptr = arr; // *nptr = arr[0]
    *nptr = 10;      // arr[0] = 10
    nptr++;          // go to arr[0+1]
    *nptr = 9;       // arr[1] = 9
    *(nptr+2) = 20;  // go to arr[1+2] and change it to 20, can use +, -, += or -=
    for (int i = 0; i < 5; ++i) {
        cout << arr[i] << " ";
    }
    cout << endl;

    cout << nptr - arr << endl; // show the INDEX in the value == i - 0

    cout << endl;

    int *aptr = new int; // Dynamic Memory Allocation
    *aptr = 25;
    cout << *aptr << endl;
    delete aptr;         // Delete the memory
    cout << *aptr << endl;


    return 0;
}

/*
Output:

*/