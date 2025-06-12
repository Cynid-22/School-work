#ifndef LabProj3_H
#define LabProj3_H

int myFind(const char * targetStr, const char * subStr);
void improveFormat(char * targetStr);

#endif

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