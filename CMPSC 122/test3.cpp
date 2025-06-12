#include <iostream>

using namespace std;

class BinNode {
    public:
    int data;
    BinNode* left;
    BinNode* right;
};

int CountPositiveEdges(BinNode * root)
{
    if (root == nullptr) {
        return 0;
    }

    int count = 0;
    if (root->left != nullptr && root->data > 0 && root->left->data > 0) {
        count++;
    }
    if (root->right != nullptr && root->data > 0 && root->right->data > 0) {
        count++;
    }

    count += CountPositiveEdges(root->left);
    count += CountPositiveEdges(root->right);

    return count;

}

int main()
{
    

    return 0;
}

/*
Output:

*/