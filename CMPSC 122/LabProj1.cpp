#include <iostream>
#include <cstdlib>
#include <time.h>

using namespace std;

int computerMove(int maximum, int numberOfObject)
{
    int n;
    if (numberOfObject <= maximum)
        return -1*(numberOfObject);
    srand(time(0));
    n = rand() % maximum + 1;
    return n;
}

int main()
{
    bool playerTurn;
    string strTemp;
    int numberOfObject, lastMovex2, move;

    cout << "Would you like to go first (y for yes and other input for no)? ";
    cin >> strTemp;
    if (strTemp == "y")
        playerTurn = true;
    else
        playerTurn = false;

    cout << "Enter the initial number of objects. ";
    cin >> numberOfObject;
    cout << endl;
    lastMovex2 = numberOfObject-1;
    while (numberOfObject > 0) {
        if (playerTurn) {
            cout << "It is your turn. Enter the number of objects to remove (1-" << min(lastMovex2,numberOfObject) << ") ";
            cin >> move;

            while (move < 1 || move > lastMovex2){
                cout << "Invalid move. Enter the number of objects to remove (1-" << min(lastMovex2,numberOfObject) << ") ";
                cin >> move;
            }
            numberOfObject -= move;

            if (numberOfObject <= 0) {
                cout << "Move accepted. You win. Congratulations!";
                break;
            }
            else
                cout << "Move accepted. The number of objects remains: " << numberOfObject << endl;

            lastMovex2 = move*2;
            playerTurn = false;
        }
        else {
            move = computerMove(min(lastMovex2,numberOfObject), numberOfObject);
            if (move <= -1) {
                cout << "It is the computer's turn. The computer removes " << -1*(move) << " objects" << "\nThere are no more object remains. The computer wins, try again";
                break;
            }
            else {
                cout << "It is the computer's turn. The computer removes " << (move) << " objects" << endl;
                numberOfObject -= move;
                cout << "The number of objects remains: " << numberOfObject << endl;
            }

            lastMovex2 = move*2;
            playerTurn = true;
        }
        cout << endl;
    }
    

    return 0;
}

/*
***Output if computer goes first:***

Would you like to go first (y for yes and other input for no)? n
Enter the initial number of objects. 30

It is the computer's turn. The computer removes 7 objects
The number of objects remains: 23

It is your turn. Enter the number of objects to remove (1-14) 5
Move accepted. The number of objects remains: 18

It is the computer's turn. The computer removes 7 objects
The number of objects remains: 11

It is your turn. Enter the number of objects to remove (1-11) 4
Move accepted. The number of objects remains: 7

It is the computer's turn. The computer removes 7 objects
There are no more object remains. The computer wins, try again

==========================================================================

***Output if player go first:***

Would you like to go first (y for yes and other input for no)? y
Enter the initial number of objects. 30

It is your turn. Enter the number of objects to remove (1-29) 3
Move accepted. The number of objects remains: 27

It is the computer's turn. The computer removes 5 objects
The number of objects remains: 22

It is your turn. Enter the number of objects to remove (1-10) 4
Move accepted. The number of objects remains: 18

It is the computer's turn. The computer removes 7 objects
The number of objects remains: 11

It is your turn. Enter the number of objects to remove (1-11) 11
Move accepted. You win. Congratulations!
*/