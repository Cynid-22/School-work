#include <iostream>
#include <cstdlib>
#include <time.h>

using namespace std;

int main()
{
    
    int result;     // is in <cstdlib> (c standard library)
                    // rand() is from [0, RAND_MAX] -> [0, 32767 (this number may/could be change) ]

    srand(time(0)); // srand() create the seed for the random gen
                    // time(0) is in <time.h> select the current time

    for (int i = 0; i < 10; ++i) {
        result = rand();
        if (result < 32767/2)
            cout << "Head" << endl;
        else
            cout << "Tail" << endl;
    }

    int n = rand() % 6 + 1;     // n = [1, 6]
    return 0;
}

/*
Output:

*/