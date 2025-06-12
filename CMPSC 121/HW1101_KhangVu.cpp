#include <iostream>

using namespace std;

int main()
{
    int arr[25] = {0}, i=0;
    string input;

    cout << "Enter the sentence: ";
    getline(cin, input);
    while(!ispunct(input[input.size()-1])) {
        cout << "Enter the string again with period at the end." << endl;
        cout << "Enter the sentence: ";
        getline(cin, input);
    }

    while(!ispunct(input[i])) {
        arr[input[i]-'a']++;
        i++;
    }

    cout << "a count: " << arr['a'-'a'] << endl;
    cout << "e count: " << arr['e'-'a'] << endl;
    cout << "i count: " << arr['i'-'a'] << endl;
    cout << "o count: " << arr['o'-'a'] << endl;
    cout << "u count: " << arr['u'-'a'] << endl;



    return 0;
}

/*
Output:
Enter the sentence: there are two trees
Enter the string again with period at the end.
Enter the sentence: there are two trees.
a count: 1
e count: 5
i count: 0
o count: 1
u count: 0
*/