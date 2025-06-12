/*---------------------------------------------------------------------
        Driver program to test the PriorityQueue template.
  ----------------------------------------------------------------------*/

#include <iostream>
#include <string>
using namespace std;

#include "PriorityQueue.h"

int main()
{
    PriorityQueue<string> stringPQueue;

    if (stringPQueue.IsEmpty()) {
        cout << "Successfully created an empty priority queue for strings." << endl;
    }

    cout << "Insert string \"Homework Due Today\" to the priority queue with priority value 20." << endl;
    stringPQueue.Insert("Homework Due Today", 20);

    cout << "Insert string \"Play Games\" to the priority queue with priority value 3." << endl;
    stringPQueue.Insert("Play Games", 3);

    cout << "Insert string \"Prepare Dinner\" to the priority queue with priority value 30." << endl;
    stringPQueue.Insert("Prepare Dinner", 30);

    cout << "Insert string \"Exam\" to the priority queue with priority value 70." << endl;
    stringPQueue.Insert("Exam", 70);

    cout << "Insert string \"Workout\" to the priority queue with priority value 15." << endl;
    stringPQueue.Insert("Workout", 15);

    cout << "Insert string \"Homework Due Today\" to the priority queue with priority value 45." << endl;
    stringPQueue.Insert("Homework Due Today", 45); // Duplicated item should cause an error.

    cout << "Current item with the highest priority: " << stringPQueue.Peek() << endl;

    cout << "Removing the highest priority item..." << endl;
    stringPQueue.Pull();

    cout << "Current item with the highest priority: " << stringPQueue.Peek() << endl;

    cout << "Change the priority of item \"Homework Due Today\" to 50." << endl;
    stringPQueue.ChangePriority("Homework Due Today", 50);

    cout << "Current item with the highest priority: " << stringPQueue.Peek() << endl;

    cout << "Removing the two highest priority item..." << endl;
    stringPQueue.Pull();
    stringPQueue.Pull();

    cout << "Current item with the highest priority: " << stringPQueue.Peek() << endl;

}


/*Sample runs:

Successfully created an empty priority queue for strings.
Insert string "Homework Due Today" to the priority queue with priority value 20.
Insert string "Play Games" to the priority queue with priority value 3.
Insert string "Prepare Dinner" to the priority queue with priority value 30.
Insert string "Exam" to the priority queue with priority value 70.
Insert string "Workout" to the priority queue with priority value 15.
Insert string "Homework Due Today" to the priority queue with priority value 45.
***Error: Trying to insert duplicated item.
Current item with the highest priority: Exam
Removing the highest priority item...
Current item with the highest priority: Prepare Dinner
Change the priority of item "Homework Due Today" to 50.
Current item with the highest priority: Homework Due Today
Removing the two highest priority item...
Current item with the highest priority: Workout

*/