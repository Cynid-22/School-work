#include <iostream>
#include "List.h"

using namespace std;

int main()
{
    int orig[] = {6,3,5,1,8,4,7,12,54,37,68};
    List list1(orig, 11, 11);
    cout << "list1 created from array: " << endl;
    list1.display(cout);
    cout << endl << endl;

    int orig2[] = {1,5,15,23,3,0,8};
    List list2(orig2, 7, 20);
    cout << "list2 created from array: " << endl;
    list2.display(cout);
    cout << endl << endl;


    List list3(list1, 2, 8);
    cout << "list3 created from list1, from index [2]-[8]: " << endl;
    list3.display(cout);
    cout << endl << endl;

    List list4(list2, 0, 6);
    cout << "list4 created from list2, from index [0]-[6]: " << endl;
    list4.display(cout);
    cout << endl << endl;

    List list5(list2, 3, 3);
    cout << "list5 created from list2, from index [3]-[3]: " << endl;
    list5.display(cout);
    cout << endl << endl;

    list1.Append(list2);
    cout << "Append list2 to list1. Result: " << endl;
    list1.display(cout);
    cout << endl << endl;

    list1.Append(list3);
    cout << "Append another List, list3, to list1. Result: " << endl;
    list1.display(cout);
    cout << endl << endl;

    cout << "The current capacity of list1 is " << list1.getCapacity() << endl; // Should be 42 if correct
}
