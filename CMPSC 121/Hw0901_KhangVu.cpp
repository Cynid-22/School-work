#include <iostream>
#include <vector>

using namespace std;

int main()
{
    vector<string> vec;
    string temp;

    for (int i = 0; i < 5; ++i) {
        cout << "Enter No." << i+1 << " of element: ";
        cin >> temp;
        vec.push_back(temp);
    }
    cout << endl;

    cout << "The output is:\n";
    for (int i = 0; i < vec.size(); ++i) {
        cout << vec.at(i) << " ";
    }

    return 0;
}

/*
Output:
Enter No.1 of element: banana
Enter No.2 of element: apple
Enter No.3 of element: pineapple
Enter No.4 of element: watermelon
Enter No.5 of element: pear

The output is:
banana apple pineapple watermelon pear
*/