#include <iostream>
#include <ctime>
#include <cstdlib>

using namespace std;

int main()
{
    int number, user;
    srand(time(0));
    number = rand()%100 + 1;

    cout << "Enter user guess between 1 and 100: ";
    cin >> user;
    while (true) {
        if (user < number)
            cout << "Too low, try again." << endl;
        if (user > number)
            cout << "Too high, try again." << endl;
        if (user == number) {
            cout << "Congrats! You guessed the correct number: "<< number;
            break;
        }
        cout << "Please enter the guess again: ";
        cin >> user;
    }


    return 0;
}

/*
Output:
Enter user guess between 1 and 100: 50
Too high, try again.
Please enter the guess again: 25
Too high, try again.
Please enter the guess again: 12
Too high, try again.
Please enter the guess again: 6
Too high, try again.
Please enter the guess again: 3
Congrats! You guessed the correct number: 3
*/