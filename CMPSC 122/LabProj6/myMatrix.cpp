#include <iostream>
#include <iomanip>
#include "myMatrix.h"

using namespace std;


///////////////////////////////////////////////////////////////////////////////
// Functions that are already implemented by myself. Do not modify.
///////////////////////////////////////////////////////////////////////////////

void myMatrix::PrintMatrix(ostream& out) {
	int index = 0;
	for (unsigned i = 0; i < this->height; i++) {
		for (unsigned j = 0; j < this->width; j++) {
			out << setw(6) << this->myArrayPtr[index];
			index++;
		}
		out << endl;
	}
}

ostream& operator<<(ostream& out, myMatrix& matrix){
	matrix.PrintMatrix(out);
	return out;
}

unsigned myMatrix::GetHeight() {
	return this->height;
}

unsigned myMatrix::GetWidth() {
	return this->width;
}


void myMatrix::SetEntry(unsigned i, unsigned j, int valueToSet) {
	this->myArrayPtr[(i * this->width) + j] = valueToSet;
}




///////////////////////////////////////////////////////////////////////////////
// Implement required member functions below
///////////////////////////////////////////////////////////////////////////////

myMatrix::myMatrix() {
    height = 1;
	width = 1;
	myArrayPtr = new int[1];
	myArrayPtr[0] = 0;
}

myMatrix::myMatrix(unsigned heightVal, unsigned widthVal, int* sourceArray) {
    height = heightVal;
	width = widthVal;
	myArrayPtr = new int[widthVal*heightVal];
	for (int i = 0; i < width*height; ++i) {
		myArrayPtr[i] = sourceArray[i];
	}
}

void myMatrix::Concatenate(myMatrix& matrixToConcatenate) {
	int newWidth = this->width+matrixToConcatenate.width, index = 0;
    int *newArray = new int[newWidth*this->height];
	
	for (int i = 0; i < height; ++i) {
		for (int j = 0; j < width; ++j) {
			newArray[index++] = myArrayPtr[j + this->width * i];
		}
		for (int j = 0; j < matrixToConcatenate.width; j++)
		{
			newArray[index++] = matrixToConcatenate.myArrayPtr[j + matrixToConcatenate.width * i];
		}	
	}

	width = newWidth;
	delete []myArrayPtr;
	myArrayPtr = newArray;
}

///////////////////////////////////////////////////////////////////////////////
// Implement the overloading function for += below
///////////////////////////////////////////////////////////////////////////////

void myMatrix::operator+= (myMatrix & matrixToAdd)
{
	if (matrixToAdd.width == this->width && matrixToAdd.height == this->height) {
		int* newArray = new int[width*height];
		for (int i = 0; i < height*width; ++i) {
			newArray[i] = myArrayPtr[i]+matrixToAdd.myArrayPtr[i];
		}
		delete [] myArrayPtr;
		myArrayPtr = newArray;
	}
	else 
		cout << "Error: imcompatible matrixes for += operation!" << endl;
}

///////////////////////////////////////////////////////////////////////////////
// Implement the "big three" below
///////////////////////////////////////////////////////////////////////////////

myMatrix::~myMatrix()
{
	delete []myArrayPtr;
}

myMatrix::myMatrix(const myMatrix &origMatrix)
{
	height = origMatrix.height;
	width = origMatrix.width;
	myArrayPtr = new int[width*height];
	for (int i = 0; i < width*height; ++i) {
		myArrayPtr[i] = origMatrix.myArrayPtr[i];
	}
}

myMatrix & myMatrix::operator= (const myMatrix &origMatrix)
{
	if (this != & origMatrix) {
		delete[] myArrayPtr;
		height = origMatrix.height;
		width = origMatrix.width;
		int* myArrayPtr = new int[width*height];
		for (int i = 0; i < width*height; ++i) {
			myArrayPtr[i] = origMatrix.myArrayPtr[i];
		}
	}
	return *this;
}

/*
Output:

The following shows a matrix created using the default constructor: 

     0


The following shows a matrix created using the second constructor: 

     2     3     4     5     6
     3     4     5     6     2
     1     3     5     7     9
     2     4     6     8    10


Trying to concatenate two matrices. Matrix A: 

     2     3     4     5     6
     3     4     5     6     2
     1     3     5     7     9
     2     4     6     8    10


Matrix B:

     0     1
     1     0
     1     1
     0     0


After concatenating B to the right of A. A becomes:

     2     3     4     5     6     0     1
     3     4     5     6     2     1     0
     1     3     5     7     9     1     1
     2     4     6     8    10     0     0


Testing the copy constructor.
The following shows a Matrix C, created from copying the Matrix B above:

     0     1
     1     0
     1     1
     0     0


Now, we make some changes to matrix C. It becomes:

     0     5
     1     0
     4     1
     0     0


We print matrix B in the following. Note if the implementation is correct,
changing matrix C should not affact matrix B!

     0     1
     1     0
     1     1
     0     0


Testing the assignment operator.
The following shows a Matrix D, which is assigned with the Matrix B above:

     0     1
     1     0
     1     1
     0     0


Now, we make some changes to matrix D. It becomes:

     0     1
     1    10
     1     1
    28     0


We print matrix B in the following. Note if the implementation is correct,
changing matrix D should not affact matrix B!

     0     1
     1     0
     1     1
     0     0


Adding matrix D to matrix B using the += operator.
After this, the matrix B becomes:
     0     2
     2    10
     2     2
    28     0


Testing compatibility issue using += operator
Matrix A dimension: 4 x 7 and Matrix B dimension: 4 x 2
Perform A += B and failed!
Error: imcompatible matrixes for += operation!
Matrix A remains the same after the failed operation
     2     3     4     5     6     0     1
     3     4     5     6     2     1     0
     1     3     5     7     9     1     1
     2     4     6     8    10     0     0
*/