#include <iostream>
#include <cstdlib>
#include <time.h>
#include <iomanip>

using namespace std;

int main()
{
    
    int result;     // is in cstdlib (c standard library)
                    // rand() is from [0, RAND_MAX] -> [0, 32767 (this number may/could be change) ]
    cout << fixed << setprecision(7);
    long double H = 0, T = 0;
    double limit = 32767/2;
    srand(time(0));

    for (long long i = 0; i < 5'000'000'000; ++i) {
        result = rand();
        if (result <= limit)
            H++;
        else
            T++;
    }
    cout << "Head: " << H/(H+T)*100 << "%" << endl;
    cout << "Tails: " << T/(H+T)*100 << "%" << endl;
    return 0;
}

/*
Output:

*/