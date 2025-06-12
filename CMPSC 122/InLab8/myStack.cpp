#include "myStack.h"
#include <iostream>

using namespace std;

myStack::myStack() {
	this->mySize = 0;
	this->stackContents = NULL;
}

void myStack::push(ElementType data) {
	Node* previousFirst = this->stackContents;
	this->stackContents = new Node;
	this->stackContents->data = data;
	this->stackContents->next = previousFirst;
	this->mySize += 1;
}

void myStack::pop() {
	Node* previous = this->stackContents->next;
	this->stackContents = previous;
	this->mySize -= 1;
}

ElementType myStack::top() {
	if (this->mySize <= 0) {
		throw std::out_of_range("Trying to perform top on empty stack!");
	}
	return this->stackContents->data;
}

int myStack::size() {
	return this->mySize;
}

/*
Output:

Driver program to test the myStack class.
A stack for testing is created.
Value 1 pushed to stack.
Current top element of stack is 1
Value 3 pushed to stack.
Current top element of stack is 3
Value 10 pushed to stack.
Current top element of stack is 10
Value 23 pushed to stack.
Current top element of stack is 23
A value is popped from the stack.
Current top element of stack is 10
Value 9 pushed to stack.
Current top element of stack is 9
Three values are popped from the stack.
Current top element of stack is 1
*/