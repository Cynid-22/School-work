#include <iostream>

using namespace std;

class Node{
    public:
        int data;
        Node * next;
};

bool TestConcave(Node * first)
{
    if (first == nullptr || first->next == nullptr || first->next->next == nullptr) {
        return true;
    }

    if ((first->next->data - first->data) >= (first->next->next->data - first->next->data)) {
        return false;
    }
    
    return TestConcave(first->next);
}

int main()
{
    

    return 0;
}

/*
Output:

*/