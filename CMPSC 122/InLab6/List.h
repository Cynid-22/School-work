#pragma once
#pragma warning(disable : 6386)
#include <iostream>

using namespace std;

typedef int ElementType; // Now "ElementType" is "int"
                         // can change int to double,char,...
class List
{
    public:
        List(int maxSize = 1024); // constructor
        List(ElementType * array, int arraySize, int capacity);

        // Big Three:
        ~List();                           // Destructor
        List(const List &orig);            // Copy Constructor
        List &operator=(const List &orig); // Assignment operator

        unsigned size() const;
        bool empty() const;                     // check if empty
        void insert(ElementType item, int pos); // insertion
        void erase(int pos);                    // deletion
        void display(ostream &out) const;       // display every item
        ElementType get(unsigned pos) const;
        int getCapacity();
        void ExpandCapacity(int sizeToExpand);
        void TryShrink();

    private:
        int mySize; // current # of items in list
        int myCapacity;
        ElementType *myArrayPtr; // pointer to dynamic array
};


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