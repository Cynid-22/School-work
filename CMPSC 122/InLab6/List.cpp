#include <iostream>
#include <cstdlib> 
#include "List.h"

using namespace std;
typedef int ElementType;

void List::insert(ElementType item, int pos)
{
    if (mySize == myCapacity)  exit(1);
    if (pos < 0 || pos > mySize)  return;
    // shift array elements right to make room for item
    for(int i = mySize; i > pos; i--)
        myArrayPtr[i] = myArrayPtr[i - 1];
    // insert item at pos and increase list size  
    myArrayPtr[pos] = item;
    mySize++;  // don't forget this!
}

void List::erase(int pos)
{
    if (pos < 0 || pos >= mySize)  return;
    // shift array elements left
    for(int i = pos; i < mySize-1; i++)
       myArrayPtr[i] = myArrayPtr[i + 1];
    mySize--;  // don't forget this!
}

List::List(int maxSize){
    mySize = 0;
    myCapacity = maxSize;
    myArrayPtr = new ElementType[maxSize];
}

List::List(ElementType * array, int arraySize, int capacity) {
    myArrayPtr = new ElementType[capacity];
    mySize = arraySize;
    myCapacity = capacity;
    for (int i = 0; i < arraySize; ++i)
        myArrayPtr[i] = array[i];
}

List::~List(){ delete [] myArrayPtr;} 

List::List(const List & origList) { 
    mySize = origList.mySize; 
    myCapacity = origList.myCapacity; 
    myArrayPtr = new ElementType[myCapacity]; 
    for(int i = 0; i < mySize; i++) 
        myArrayPtr[i] = origList.myArrayPtr[i]; 
}

List & List::operator=(const List & origList)
{
   if (this != & origList)    // check for list = list
   {
        delete[] myArrayPtr;
        mySize = origList.mySize;
        myCapacity = origList.myCapacity;

        myArrayPtr = new ElementType[myCapacity];
   //--- Copy origList's array into this new array
        for(int i = 0; i < myCapacity; i++)
            myArrayPtr[i] = origList.myArrayPtr[i];
   }
   return *this;
}



bool List::empty() const {
    return (mySize == 0);
}

unsigned List::size() const{
  return mySize;
}

ElementType List::get(unsigned pos) const{
  if(pos >= mySize){
    cerr << "Invalid Index." << endl;
    exit(1);
  }
  return myArrayPtr[pos];
}

void List::display(ostream & out) const {
    for(int i = 0; i < mySize; i++){
       out << myArrayPtr[i] << " ";
   }
}

int List::getCapacity(){
  return myCapacity;
}

void List::ExpandCapacity(int sizeToExpand) {
    ElementType* newArray = new ElementType[sizeToExpand];
    for (int i = 0; i < mySize; ++i) {
        newArray[i] = myArrayPtr[i];
    }
    delete[]myArrayPtr;
    myArrayPtr = newArray;
    myCapacity = sizeToExpand;
}

void List::TryShrink() {
    if (mySize*4 < myCapacity) {
        ElementType* newArray = new ElementType[myCapacity/2];
        for (int i = 0; i < mySize; ++i) {
            newArray[i] = myArrayPtr[i];
        }
        delete[]myArrayPtr;
        myArrayPtr = newArray;
        myCapacity = myCapacity/2;
    }
}

/*
Output:

list1 created from array: 
6 3 5 1 8 4 7 

list2 created from array: 
1 5 15 23 3 
Capacity of list2 is 5
Change the capacity of list 2 to 50.
After the change of capacity, list2 becomes: 
1 5 15 23 3 
Inserting more elements to the expanded list2.
After the insertion, list2 becomes: 
44 43 42 41 40 39 38 37 36 35 34 33 32 31 30 29 28 27 26 25 24 23 22 21 20 19 18 17 16 15 14 13 12 11 10 9 8 7 6 5 4 3 2 1 0 1 5 15 23 3 

Removing elements from the until its size is 10.
But the capacity still remains at 50
After the removal of elements, list2 becomes:
4 3 2 1 0 1 5 15 23 3
Try strinking the capacity. After this, list2 becomes:
4 3 2 1 0 1 5 15 23 3
The capacity of list 2 is 25
*/