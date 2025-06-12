
/******************************************************************************
 CMPSC122 LabProj12: Project Algorithm Analysis -- sample driver
 ******************************************************************************/

#include <iostream>
#include <iomanip>
#include <cstring>
#include <cstdlib>
#include <fstream>
#include "timer.h"
#include "MaxSubList.h"

const int FROM_SIZE = 500;
const int MUL = 2;

using namespace std;

void Ouput_CSV_File(string filename, Timer* T, int loop);

int main()
{
    int* Result, * Vec;
    Timer* T, Prepare;
    int loop;

    cout << "Please enter the number of loops: ";
    cin >> loop;

    Prepare.start();
    int Size = FROM_SIZE;       // the integers sequence array size

    T = new Timer[loop];
    Result = new int[loop];
    cout << setprecision(4) << fixed << endl;
    // change heading for each algorithm
    cout << string(28, '=') << " Algorithm Blue " << string(28, '=') << endl << endl;
    for (int i = 0; i < loop; i++)
    {
        Vec = new int[Size];
        srand((unsigned)time(NULL));

        for (int j = 0; j < Size; j++)
            Vec[j] = rand() % 101 - 50;

        Result[i] = 0;
        Prepare.stop();
        cout << "Preparation Time: " << Prepare.get_elapsedtime() << "s" << endl;

        T[i].start();
        // Comment and uncomment for each test case
        // Result[i] = MaxSublistSum_Blue(Vec, Size);
        // Result[i] = MaxSublistSum_Green(Vec, Size);
        Result[i] = MaxSublistSum_Red(Vec, Size);
        T[i].stop();

        cout << '[' << setw(2) << i << ']' << setw(50) << "Maximum contiguous subsequence sum (array size = "
            << setw(9) << Size << "): " << setw(6) << Result[i] << endl;
        cout << "Elapsed Time: " << T[i].get_elapsedtime() << 's' << endl;
        cout << string(72, '-') << endl;

        Size = MUL * Size;
        delete[] Vec;
    }
    cout << endl << endl;

    Ouput_CSV_File("Result(Blue).csv", T, loop);  // change the filename for each algorithm
}


void Ouput_CSV_File(string filename, Timer* T, int loop)
{
    ofstream csvOutFile(filename);

    if (!csvOutFile)
    {
        cerr << "Failed to open output file: " << filename << endl;
        exit(EXIT_FAILURE);
    }
    int Size = FROM_SIZE;
    csvOutFile << "\"Array Size\",\"Run Time (s)\"" << endl;
    for (int i = 0; i < loop; i++)
    {
        csvOutFile << Size << "," << T[i].get_elapsedtime() << endl;
        Size = MUL * Size;
    }
    csvOutFile.close();
}

