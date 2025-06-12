#include <iostream>
#include <cctype>
#include <string>

using namespace std;

int main()
{
    string input; 
    int count = 1, i=0;

    cout << "Enter the sentence: ";

    getline(cin, input);
    while(!ispunct(input[input.size()-1])) {
        cout << "Enter the string again with period at the end." << endl;
        cout << "Enter the sentence: ";
        getline(cin, input);
    }
    while (!ispunct(input[i])) {
        if (isspace(input[i]))
            count++;
        i++;
    }
    cout << "The sentence is: " << input << endl;
    cout << "The word counter: " << count;

    return 0;
}

/*
Output:
Enter the sentence: there are two trees
Enter the string again with period at the end.
Enter the sentence: there are two trees.
The sentence is: there are two trees.
The word counter: 4
*/