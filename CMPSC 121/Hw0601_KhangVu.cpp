#include <iostream>

using namespace std;
int max (int x, int y)
{
    if (x>y)
        return x;
    return y;
}


int main()
{
    int a = 10, b = 20;

    int m = max(a, b);
    
    cout << "the max number: " << m;
    return 0;
}

/*
output:
the max number: 20
*/