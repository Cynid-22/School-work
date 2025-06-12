#pragma once

typedef int ElementType;

class Node {
public:
	ElementType data;
	Node* next;
};

class myStack {
public:
	myStack();
	void push(ElementType data);
	void pop();
	ElementType top();
	int size();

private:
	Node* stackContents; // Points to the linked list that stores the data item
	int mySize;
};
