#include <iostream>

using namespace std;


// Declation of Node class template
template <typename T>
class Node{
public:
    T data;
    Node<T> * next;
};

template <typename T>
void insert(Node<T> *&first, int position, T value)
{
    Node<T> *newNode = new Node<T>;
    newNode->data = value;
    newNode->next = NULL;

    if (position == 0) {
        newNode->next = first;
        first = newNode;
        return;
    }

    Node<T> *current = first;
    for (int i = 0; i < position - 1 && current != NULL; i++) {
        current = current->next;
    }

    if (current == NULL) {
       current = first;
        while (current->next != NULL)
        {
            current = current->next;
        }
        current->next = newNode;
    }
    else {
        newNode->next = current->next;
        current->next = newNode;
    }
}

// Display
template <typename T>
void display(Node<T> * first){
    Node<T> * cur = first;
    while(cur != NULL){
        cout << (cur->data) << " ";
        cur = cur->next;
    }
    cout << endl;
}

int main(){
    Node<int> * ifirst = NULL; // Empty linked list of integer
    insert<int>(ifirst, 0, 5);
    insert<int>(ifirst, 0, 6);
    insert<int>(ifirst, 0, 7);
    insert<int>(ifirst, 1, 8);
    insert<int>(ifirst, 4, 100);
    cout << "A Linked List of Integers: " << endl;
    display<int>(ifirst);
    
    Node<double> * dfirst = NULL; // Empty linked list of doubles
    insert<double>(dfirst, 0, 5.4);
    insert<double>(dfirst, 0, 23.4);
    insert<double>(dfirst, 0, 7.253);
    insert<double>(dfirst, 1, 100.45);
    insert<double>(dfirst, 4, 15.84);
    cout << "A Linked List of Doubles: "  << endl;
    display<double>(dfirst);
}

/*
Output:

A Linked List of Integers: 
7 8 6 5 100 
A Linked List of Doubles: 
7.253 100.45 23.4 5.4 15.84 
*/