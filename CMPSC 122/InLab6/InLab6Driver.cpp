#include <iostream>
#include "List.h"

using namespace std;

int main()
{
    int orig[] = {6,3,5,1,8,4,7};
    List list1(orig, 7, 50);
    cout << "list1 created from array: " << endl;
    list1.display(cout);
    cout << endl << endl;;

    int orig2[] = {1,5,15,23,3};
    List list2(orig2, 5, 5);
    cout << "list2 created from array: " << endl;
    list2.display(cout);
    cout << endl;
    cout << "Capacity of list2 is " << list2.getCapacity() << endl;
    cout << "Change the capacity of list 2 to 50." << endl;
    list2.ExpandCapacity(50);
    cout << "After the change of capacity, list2 becomes: " << endl;
    list2.display(cout); // Should be unchanged, since we change the capacity, not the contents
    cout << endl;
    cout << "Inserting more elements to the expanded list2." << endl;
    for(int i=0; i<45; i++){
    	list2.insert(i,0);
    }
    cout << "After the insertion, list2 becomes: " << endl;
    list2.display(cout);
    // Should be 44 43 42 41 40 39 38 37 36 35 34 33 32 31 30 29 28 27 26 25 24 23 22 21 20 19 18 17 16 15 14 13 12 11 10 9 8 7 6 5 4 3 2 1 0 1 5 15 23 3
    cout << endl << endl;


    cout << "Removing elements from the until its size is 10." << endl;
	cout << "But the capacity still remains at 50" << endl;
    while (list2.size() > 10) {
        list2.erase(0);
    }
    cout << "After the removal of elements, list2 becomes: " << endl;
    list2.display(cout);
    // Should be 4 3 2 1 0 1 5 15 23 3
    cout << endl;
    cout << "Try strinking the capacity. After this, list2 becomes: " << endl;
    list2.TryShrink();
    list2.display(cout);
    // Should be 4 3 2 1 0 1 5 15 23 3
    cout << endl;
    cout << "The capacity of list 2 is " << list2.getCapacity() << endl;
}

/*
The test run output:
====================
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
