#include <iostream>
#include "LabProj3.h"

using namespace std;

int myFind(const char * targetStr, const char * subStr)
{
    int counter=0, sizeTarget, sizeSubStr;
    for(sizeTarget = 0; targetStr[sizeTarget]; ++sizeTarget);
    for(sizeSubStr = 0; subStr[sizeSubStr]; ++sizeSubStr);

    bool match;
    for (int i = 0; i < sizeTarget - sizeSubStr + 1; ++i) {
        if (targetStr[i] == subStr[0]) {
            match = true;
            for (int j = 1; j < sizeSubStr; ++j) {
                if (targetStr[i+j] != subStr[j])
                    match = false;
            }
            if (match)
                counter++;
        }
    }
    return counter;
}

void improveFormat(char * targetStr)
{
    int sizeTarget, i=0;
    for(sizeTarget = 0; targetStr[sizeTarget]; ++sizeTarget);

    // remove the space at the beginning
    while (targetStr[i] == ' ') {
        for (int j = 0; j < sizeTarget; ++j) {
            targetStr[j] = targetStr[j+1];
        }
        sizeTarget--;
    } 

    //removing the space at the end
    i = sizeTarget-1;
    while(targetStr[i] == ' ') {
        targetStr[i] = targetStr[i+1];
        --i;
    }

    //removing the space at the middle
    for (int i = 1; targetStr[i] != '\0'; ++i) {
        if (targetStr[i] == ' ' && targetStr[i-1] == ' ') {
            for (int j = i; targetStr[j] != '\0'; ++j) {
                targetStr[j] = targetStr[j+1];
            }       
            i--;
        }
    }
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