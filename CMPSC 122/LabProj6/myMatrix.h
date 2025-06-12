#pragma once

#include <iostream>

using namespace std;

class myMatrix {
public:
	///////////////////////////////////////////////////////////////////////////
	// Member functions you need to implement:
	///////////////////////////////////////////////////////////////////////////

	// 1. The default constructor. 
	// Initialize the matrix to a 1x1 matrix with entry 0
	
	myMatrix();

	// 2. The Second constructor. 
    // Initialize the matrix of given height and width with elements from a given array

	myMatrix(unsigned heightVal, unsigned WidthVal, int * sourceArray);
	
	// 3. Concatenate function.
	// Concatenates a matrix of the same height to the right of the caller matrix

	void Concatenate(myMatrix & matrixToConcatenate);

	// 4. Overloading of the += operator. Suggest using internal method.

	void operator+= (myMatrix & matrixToAdd);


	// 5,6,7: The "big three"

	~myMatrix();
	myMatrix(const myMatrix &origMatrix);
	myMatrix &operator= (const myMatrix &origMatrix);


	///////////////////////////////////////////////////////////////////////////
	// Member functions I have already implemented. Do not touch.
	///////////////////////////////////////////////////////////////////////////

	unsigned GetHeight();
	unsigned GetWidth();
	void PrintMatrix(ostream & out);
	void SetEntry(unsigned i, unsigned j, int valueToSet);

private:
	unsigned height;
	unsigned width;
	int* myArrayPtr;
};

// Overloading the << operator for easy output
ostream& operator<<(ostream& out, myMatrix& matrix);