#include <iostream>

using namespace std;

int* append(int* arrayA, int sizeA, int* arrayB, int sizeB)
{
    int* arraytemp = new int[sizeA + sizeB];
    for (int i = 0; i < sizeA; ++i) 
        arraytemp[i] = arrayA[i];
    for (int i = 0; i < sizeB; ++i)
        arraytemp[sizeA+i] = arrayB[i];
    return arraytemp;
}

int* merge(int* arrayA, int sizeA, int* arrayB, int sizeB)
{
    int* arraytemp = new int[sizeA + sizeB];
    int i=0, j=0;
    while (i < sizeA && j < sizeB)
    {
        if (arrayA[i] <= arrayB[j]) {
            arraytemp[i+j] = arrayA[i];
            i++;
        }
        else {
            arraytemp[i+j] = arrayB[j];
            ++j;
        }
    }
    if (i < sizeA)
        while(i < sizeA) {
            arraytemp[i+j] = arrayA[i];
            ++i;
        }    
    else
        while (j < sizeB) {
            arraytemp[i+j] = arrayB[j];
            ++j;
        }
    return arraytemp;
}

void print(int* array, int size, const char * lable)
{
    cout << lable;
    for (int i = 0; i < size; ++i)
        cout << array[i] << " ";
    cout << endl;
}

int main()
{
    // int arrayA[] = {11,33,55,77,99}, sizeA = 5;
    // int arrayB[] = {22,44,66,88}, sizeB = 4;
    int arrayA[] = {11,22,33,55,77,99}, sizeA = 6;
    int arrayB[] = {22,44,66,88}, sizeB = 4;
    // int arrayA[] = {22,44,66,88}, sizeA = 4;
    // int arrayB[] = {11,33,55,77,99}, sizeB = 5;
    // int arrayA[] = {22,44,66,88}, sizeA = 4;
    // int arrayB[] = {11,33,55,77}, sizeB = 4;

    print(arrayA, sizeA, "Sorted array A: ");
    print(arrayB, sizeB, "Sorted array B: ");
    int* arrayC = append(arrayA,sizeA,arrayB,sizeB);
    print(arrayC, sizeA + sizeB, "Append B to A:  ");
    int* arrayD = merge(arrayA,sizeA,arrayB,sizeB);
    print(arrayD, sizeA + sizeB, "Merge A to B:   ");
    delete [] arrayC;
    delete [] arrayD;

#ifdef _WIN32
#if (_MSC_VER <= 1916)
  system("pause");
#endif
#endif

    return 0;
}

/*
Output:
Sorted array A: 11 22 33 55 77 99 
Sorted array B: 22 44 66 88
Append B to A:  11 22 33 55 77 99 22 44 66 88
Merge A to B:   11 22 22 33 44 55 66 77 88 99
Press any key to continue . . . 
*/