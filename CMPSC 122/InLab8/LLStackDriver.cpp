#include <iostream>

#include "myStack.h"

using namespace std;

int main() {
	
	cout << "Driver program to test the myStack class." << endl;

	myStack testStack;
	cout << "A stack for testing is created." << endl;

	testStack.push(1);
	cout << "Value 1 pushed to stack." << endl;
	cout << "Current top element of stack is " << testStack.top() << endl;

	testStack.push(3);
	cout << "Value 3 pushed to stack." << endl;
	cout << "Current top element of stack is " << testStack.top() << endl;

	testStack.push(10);
	cout << "Value 10 pushed to stack." << endl;
	cout << "Current top element of stack is " << testStack.top() << endl;

	testStack.push(23);
	cout << "Value 23 pushed to stack." << endl;
	cout << "Current top element of stack is " << testStack.top() << endl;

	testStack.pop();
	cout << "A value is popped from the stack." << endl;
	cout << "Current top element of stack is " << testStack.top() << endl;

	testStack.push(9);
	cout << "Value 9 pushed to stack." << endl;
	cout << "Current top element of stack is " << testStack.top() << endl;

	testStack.pop();
	testStack.pop();
	testStack.pop();
	cout << "Three values are popped from the stack." << endl;
	cout << "Current top element of stack is " << testStack.top() << endl;
}