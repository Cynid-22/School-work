// Three functions for finding the maximum sum of a subarray of a given array

#include <iostream>
#include <cmath>
#include <cstdlib>
#include "MaxSubList.h"

using namespace std;

///////////////////////////////////////////////////////////////////////////////
// Algorithm 1: Brute force
// Running time (Big-O): ??
///////////////////////////////////////////////////////////////////////////////

int MaxSublistSum_Blue(int array[], int size) {
	int currentMax = 0;
	for (int i = 0; i < size; i++) {
		int curSum = 0;
		for (int j = 0; j < size - i; j++) {
			curSum += array[i+j];
			if (curSum > currentMax) {
				currentMax = curSum;
			}
		}
	}

	return currentMax;
}


///////////////////////////////////////////////////////////////////////////////
// Algorithm 2: Divide and conquer, recursion
// Running time (Big-O): ??
///////////////////////////////////////////////////////////////////////////////

int MaxSublistSum_DnC_Rec(int array[], int start, int end);

int MaxSublistSum_Green(int array[], int size) {
	return MaxSublistSum_DnC_Rec(array, 0, size-1);
}

int MaxSublistSum_DnC_Rec(int array[], int start, int end) {
	if (start > end) {
		return 0;
	}
	else if (start == end) {
		return max(0, array[start]);
	}
	else {
		int mid = (start + end) / 2;
		int leftMax = MaxSublistSum_DnC_Rec(array, start, mid);
		int rightMax = MaxSublistSum_DnC_Rec(array, mid+1, end);

		int inBetweenMax = 0;
		int leftCurSum = 0;
		for (int i = mid; i >= start; i--) {
			leftCurSum += array[i];
			if (leftCurSum > inBetweenMax) {
				inBetweenMax = leftCurSum;
			}
		}
		int rightCurSum = inBetweenMax;
		for (int i = mid + 1; i <= end; i++) {
			rightCurSum += array[i];
			if (rightCurSum > inBetweenMax) {
				inBetweenMax = rightCurSum;
			}
		}

		return max(max(leftMax, rightMax), inBetweenMax);
	}
}

///////////////////////////////////////////////////////////////////////////////
// Algorithm 3: Linear scan, dynamic programming
// Running time (Big-O): ??
///////////////////////////////////////////////////////////////////////////////

int MaxSublistSum_Red(int array[], int size) {
	int* maxSumEndingAt = new int[size];

	maxSumEndingAt[0] = array[0];
	int currentMax = max(0, maxSumEndingAt[0]);
	for (int i = 1; i < size; i++) {
		if (maxSumEndingAt[i - 1] <= 0) {
			maxSumEndingAt[i] = array[i];
		}
		else {
			maxSumEndingAt[i] = maxSumEndingAt[i - 1] + array[i];
		}

		if (maxSumEndingAt[i] > currentMax) {
			currentMax = maxSumEndingAt[i];
		}
	}

	delete[] maxSumEndingAt;

	return currentMax;
}


///////////////////////////////////////////////////////////////////////////////
// A simple testing program
///////////////////////////////////////////////////////////////////////////////
/*
int main() {
	int testingArray[] = { 10, -5, -15, 18, -7, -2, 9, 6, -2, 7, -10 };

	cout << MaxSublistSum_BruteForce(testingArray, 11) << endl;
	cout << MaxSublistSum_DnC(testingArray, 11) << endl;
	cout << MaxSublistSum_Scan(testingArray, 11) << endl;
}
*/