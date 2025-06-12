#include <iostream>

using namespace std;

template <typename myType> //This is used the same as a usual variable type
class Stack
{
    public:
        // code...
    private:
        myType a, b;
        int c, d;
        // ...
};

template <typename myType> //This must precede every function/class
void Swap (myType& a, myType& b)
{
    myType hold = a;
    a = b;
    b = hold;
}

int main()
{
    int a, b;
    Swap<int>(a, b);

    string str_a, str_b;
    Swap<string>(str_a, str_b);

    Stack<int> c_a; // remember to use <type>

    return 0;
}

