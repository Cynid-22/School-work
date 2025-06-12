#include <iostream>

using namespace std;

int main()
{
    int size;
    cout << "Enter the size: ";
    cin >> size;
    int score[size];
    double average = 0, total = 0;
    for (int i = 0; i<size; ++i) {
        cout << "Enter No." << i+1 << " of the test score: ";
        cin >> score[i];
        total += score[i];
    }
    cout << "the total point of the test score is: " << total;
    average = total/size;
    cout << "\nThe average of the test score is: " << average;
    return 0;
}

/*
Output:
Enter the size: 10
Enter No.1 of the test score: 5
Enter No.2 of the test score: 34 
Enter No.3 of the test score: 56 
Enter No.4 of the test score: 96
Enter No.5 of the test score: 35
Enter No.6 of the test score: 75
Enter No.7 of the test score: 13
Enter No.8 of the test score: 69
Enter No.9 of the test score: 90
Enter No.10 of the test score: 57
the total point of the test score is: 530
The average of the test score is: 53
*/