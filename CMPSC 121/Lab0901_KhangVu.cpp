#include <iostream>
#include <vector>

using namespace std;

int main()
{
    vector<int> vec;
    int temp;

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
Enter No.1 of element: 1
Enter No.2 of element: 2
Enter No.3 of element: 3
Enter No.4 of element: 4
Enter No.5 of element: 5

The output is:
1 2 3 4 5
*/