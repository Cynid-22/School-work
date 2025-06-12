#include <iostream>
#include <cmath>

using namespace std;

int main()
{
    double score;
    cout << "Enter a score: ";
    cin >> score;
    score = round(score);
    if (score >= 50) {
        if (score >= 60) {
            if (score >= 70) {
                if (score >= 80) {
                    if (score >= 90) {
                        if (score > 100)
                            cout << "Input is invalid";
                        else cout << "Grade: A+";
                    }
                    else cout << "Grade: A";
                }
                else cout << "Grade: B";
            }
            else cout << "Grade: C";
        }
        else cout << "Grade: D";
    }
    else cout << "Grade: F";
    return 0;
}

/*
Output:
Enter a score: 89.5
Grade: A+
*/