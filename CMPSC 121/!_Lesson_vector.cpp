#include <iostream>
#include <vector>

using namespace std;

int main()
{
    vector<int> score{1, 2, 3, 4, 5, 5, 6, 7, 8};

    score.push_back(75); // add the value to the end of the vector

    // size() shows the amount of element in the vector
    cout << score.size() << ": ";
    
    for (auto i = score.begin(); i < score.end(); ++i) {
        cout << *i << " ";
    }


    // test out vec.pop_back(), remove the last element
    cout << "\n";
    score.pop_back();
    cout << score.size() << ": ";
    for (auto i = score.begin(); i < score.end(); ++i) {
        cout << *i << " ";
    }

    //test out vec.resize(n, val), if n is smaller than the current size() -> use pop_back()     until size() == n
    //                                     bigger                          ->     push_back(val)
    cout << "\n";
    cout << score.size() << ": ";
    score.resize(19, 9);
    for (auto i = score.begin(); i < score.end(); ++i) {
        cout << *i << " ";
    }

    // test out vec.clear(), clear all element -> size() = 0
    cout << "\n";
    score.clear();
    cout << score.size() << ": ";
    for (auto i = score.begin(); i < score.end(); ++i) {
        cout << *i << " ";
    }
    cout << "\nis it empty? " << score.empty();

    // vec.at(i) == vec[i]

    return 0;
}

/*
Output:

*/