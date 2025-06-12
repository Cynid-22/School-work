#include <iostream>

using namespace std;

typedef int ElementType;

const int DEFAULT_CAP = 1024;

class List {
public:
    List(int maxSize = 1024);  //constructor

    //Big Three:
    ~List(); // Destructor
    List(const List& orig); // Copy Constructor
    List& operator= (const List& orig); // Assignment operator

    // Array Convertion Constructor - From Lab 6. Implemented.
    List(ElementType* arr, int arrSize, int Capacity);

    // Member functions you need to implement for Lab 7
    List(List& orig, int lowIndex, int highIndex); // Sublist constructor
    void Append(List& listToAppend); // append function

    unsigned size() const;
    bool empty() const; //check if empty
    void insert(ElementType item, int pos); //insertion
    void erase(int pos);	//deletion
    void display(ostream& out) const; //display every item
    ElementType get(unsigned pos) const;
    int getCapacity();
private:
    int mySize;       // current # of items in list     
    int myCapacity;
    ElementType* myArrayPtr; //pointer to dynamic array
};
