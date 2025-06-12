#include <iostream>
#include <cstring>

// You need to create LabProj3.h and write the prototypes of the two functions in it
#include "LabProj3.h"

using namespace std;

// functions to test your program, DO NOT modify
void testFind(const char* targetStr, const char* subStr)
{
    int res = myFind(targetStr, subStr);
    if (res <= 0) {
        cout << "String " << "\"" << subStr << "\"" << " does not appear in string "
            << "\"" << targetStr << "\"" << endl;
    }
    else {
        cout << "String " << "\"" << subStr << "\"" << " found in string "
            << "\"" << targetStr << "\"" << " at " << res << " locations" << endl;
    }
}

// functions to test your program, DO NOT modify
void testImproveFormat(char* targetStr)
{
    cout << "The string before improving the format: [" << targetStr << "]" << endl;
    improveFormat(targetStr);
    cout << "The string after format improvement: [" << targetStr << "]" << endl;
    cout << endl;
}


int main()
{
    testFind("abbbfd", "abc");
    testFind("Begining", "in");
    testFind("MyComputer", "put");
    testFind("Download", "o");
    testFind("friendship", "ind");
    testFind("oooooo", "oo");
    cout << endl << endl;

    char str1[] = "Ode     to            Joy";
    testImproveFormat(str1);

    char str2[] = "Welcome   to   my    class!    ";
    testImproveFormat(str2);

    char str3[] = "            Remove  the redundant blank    spaces   in this          string.";
    testImproveFormat(str3);
    //cout << endl << endl;
}

/*
Output:
String "abc" does not appear in string "abbbfd"
String "in" found in string "Begining" at 2 locations
String "put" found in string "MyComputer" at 1 locations
String "o" found in string "Download" at 2 locations
String "ind" does not appear in string "friendship"
String "oo" found in string "oooooo" at 5 locations


The string before improving the format: [Ode     to            Joy]
The string after format improvement: [Ode to Joy]

The string before improving the format: [Welcome   to   my    class!    ]
The string after format improvement: [Welcome to my class!]

The string before improving the format: [            Remove  the redundant blank    spaces   in this          string.       ]
The string after format improvement: [Remove the redundant blank spaces in this string.]
*/
