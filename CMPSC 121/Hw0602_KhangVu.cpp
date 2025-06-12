#include <iostream>

using namespace std;
int min (int x, int y)
{
    if (x<y)
        return x;
    return y;
}


int main()
{
    int a = 10, b = 20;

    int m = min(a, b);
    
    cout << "the min number: " << m;
    return 0;
}

/*
output:
the min number: 20
*/