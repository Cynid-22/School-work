#include <iostream>
#include <iomanip>

using namespace std;

void sort_arr(int len, int arr[])
{
    int temp;
    for (int i = 0; i < len-1; ++i)
        for (int j = i+1; j < len; ++j)
            if (arr[i]>arr[j]) {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
}

double avg(int arr[], int size)
{
    double total=0;
    for (int i = 0; i < size; ++i) {
        total += arr[i];
    }
    return total/size;
}

int main()
{
    int size, lowest=INT_MIN, count_lowest=0;
    double total = 0;
    cout << "Please enter the number of test scores: ";
    cin >> size;
    while(size <= 0) {
        cout << "Invalid amount, please enter again: ";
        cin >> size;
    }

    int* scores = new int[size];

    for (int i = 0; i < size; ++i) {
        cout << "Enter test score " << i+1 << ": ";
        cin >> scores[i];
        while(scores[i] < 0) {
            cout << "Invalid amount, please enter again: ";
            cin >> scores[i];
    }
        total += scores[i];
    }

    sort_arr(size,scores);

    cout << "The test scores ascending order:\n================================\n";
    for (int i = 0; i < size; ++i)
        cout << scores[i] << endl;
    cout << setprecision(2) << fixed << "\nThe average score is " << avg(scores, size) << endl;

    for (int i = 1; i < size; ++i)
        scores[i-1] = scores[i];
    cout << "The test scores (dropping lowest):\n==================================\n";
    for (int i = 0; i < size-1; ++i)
        cout << scores[i] << endl;
    cout << setprecision(2) << fixed << "\nThe average score is " << avg(scores, size-1) << endl;

    return 0;
}

/*
Output:
Please enter the number of test scores: -5
Invalid amount, please enter again: 0
Invalid amount, please enter again: 6
Enter test score 1: 69
Enter test score 2: 93
Enter test score 3: -1
Invalid amount, please enter again: 75
Enter test score 4: 96
Enter test score 5: 91
Enter test score 6: 88
The test scores ascending order:
================================
69
75
88
91
93
96

The average score is 85.33
The test scores (dropping lowest):
==================================
75
88
91
93
96

The average score is 88.60
*/