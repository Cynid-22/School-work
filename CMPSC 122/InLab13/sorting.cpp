#include <iostream>
#include <string>

using namespace std;

template <typename T>
int InsertionSort(T arr[], int size) {
	int i, j;
	T temp;
	int comparisonCount = 0;
	for (i = 1; i < size; i++) {
		temp = arr[i];
		for (j = i - 1; j >= 0; j--) {
			// The following if statement compare temp and arr[j], both are array elements
			// Should increase the comparison count.
			comparisonCount += 1;
			if (temp < arr[j]) arr[j + 1] = arr[j];
			else break;
		}
		arr[j + 1] = temp;
	}

	return comparisonCount;
}

template <typename T>
int partition(T x[], int first, int last)
{
	T pivot = x[last];    // pick the last as pivot element
	int j = first - 1;    // use x[first], x[first+1]...x[j]
						  // to store smaller than pivot values
	for (int i = first; i < last; i++) {
		// The following if state makes a comparision of x[i] and pivot. Both are array elements.
		if (x[i] < pivot) {
			j += 1;
			swap(x[j], x[i]);
		}
	}
	swap(x[last], x[j + 1]);
	return j + 1;
}

// The function you need to modify
template <typename T>
int QuickSort(T x[], int first, int last) {

	int comparisonCount = 0;

	if (first < last) {
		int pi = partition(x, first, last);
		comparisonCount += last - first; // The partition process made first-last comparisons in its for loop
		comparisonCount += QuickSort(x, first, pi - 1);
		comparisonCount += QuickSort(x, pi + 1, last);
	}

	return comparisonCount;
}

// A function used by the testing program
// Do not modify
void testCase(int testArr[], int size) {
	int* arrCopyA = new int[size];
	int* arrCopyB = new int[size];
	memcpy(arrCopyA, testArr, size * sizeof(int));
	memcpy(arrCopyB, testArr, size * sizeof(int));
	int numOfCompsInsertion = InsertionSort<int>(arrCopyA, size);
	int numOfCompsQuick = QuickSort<int>(arrCopyB, 0, size-1);

	cout << "Given array: " << endl;

	for (int i = 0; i < size; i++) {
		cout << testArr[i] << " ";
	}
	cout << endl;

	cout << "The insertion sort algorithm takes " << numOfCompsInsertion << " comparisons to sort the array." << endl;
	cout << "The quicksort algorithm takes " << numOfCompsQuick << " comparisons to sort the array." << endl;

}

// The main function.
int main() {

	// Task 1:
	int testArray1[20] = {
		1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
		11, 12, 13, 14, 15, 16, 17, 18, 20, 19
	};

	cout << "Task 1: \n";
	testCase(testArray1, 20);

	//Task 2:
	int testArray2[20] = {
		20, 14,  2, 17, 12,  5, 18, 15, 13, 10,
        11,  4,  7, 19,  8, 16,  1,  9,  3,  6
	};

	cout << "\nTask 2: \n";
	testCase(testArray2, 20);
}

/*
Output:
Task 1: 
Given array: 
1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 20 19 
The insertion sort algorithm takes 20 comparisons to sort the array.
The quicksort algorithm takes 172 comparisons to sort the array.

Task 2: 
Given array: 
20 14 2 17 12 5 18 15 13 10 11 4 7 19 8 16 1 9 3 6 
The insertion sort algorithm takes 141 comparisons to sort the array.
The quicksort algorithm takes 61 comparisons to sort the array.
*/