
#include <cstring>
#include "mystring2.h"
#pragma warning(disable : 4996) // disbale the unsafe warning message to use strcpy_s(), etc

String::String()
{
    myArrayPtr = new char[0];
    len = 0;
}

String::String(const char s[])
{
    len = strlen(s);
    myArrayPtr = new char[len];
    for (int i = 0; i < len; ++i) {
        myArrayPtr[i] = s[i];
    }
}

void String::append(const String &str)
{
    int len2 = len + str.len;
    char * temp = new char[len2];
    for (int i = 0; i < len; ++i) {
        temp[i] = myArrayPtr[i];
    }
    for (int i = 0; i < str.len; ++i) {
        temp[i+len] = str[i];
    }
    delete [] myArrayPtr;
    myArrayPtr = temp;
    len = len2;
}

bool String::operator==(const String &str) const
{
    return strcmp(myArrayPtr, str.myArrayPtr) == 0;
}

bool String::operator!=(const String &str) const
{
    return strcmp(myArrayPtr, str.myArrayPtr) != 0;
}

bool String::operator>(const String &str) const
{
    return strcmp(myArrayPtr, str.myArrayPtr) > 0;
}

bool String::operator<(const String &str) const
{
    return strcmp(myArrayPtr, str.myArrayPtr) < 0;
}

bool String::operator>=(const String &str) const
{
    return strcmp(myArrayPtr, str.myArrayPtr) >= 0;
}

String String::operator+=(const String &str)
{
    append(str);
    return *this;
}

void String::print(ostream &out) const
{
    string temp;
    for (int i = 0; i < len; ++i) {
        temp += myArrayPtr[i];
    }
    out << temp;
}

int String::length() const
{
    return len;
}

char String::operator[](int i) const
{
    if (i < 0 || i >= len)
    {
        cerr << "can't access location " << i
             << " of string \"" << myArrayPtr << "\"" << endl;
        return '\0';
    }
    return myArrayPtr[i];
}

ostream &operator<<(ostream &out, const String &s)
{
    s.print(out);
    return out;
}

String::~String()
{
    delete[] myArrayPtr;
}

String::String(const String &origString)
{
    len = origString.len;
    myArrayPtr = new char[origString.len];
    for (int i = 0; i < len; ++i)
    {
        myArrayPtr[i] = origString.myArrayPtr[i];
    }
}

String &String::operator=(const String &origString)
{
    if (this != &origString)
    {
        delete[] myArrayPtr;
        len = origString.len;

        myArrayPtr = new char[len];
        for (int i = 0; i < len; ++i)
        {
            myArrayPtr[i] = origString.myArrayPtr[i];
        }
    }
    return *this;
}


/*
Output:

Initial values:
str1 holds "" (length = 0)
str2 holds "init2" (length = 5)
str3 holds "init3" (length = 5)

Enter a value for str1 (no spaces): red

Enter a value for str2 (no spaces): yellow

Enter a value for str3 (no spaces): blue

After assignments...
str1 holds "red" (length = 3)
str2 holds "yellow" (length = 6)
str3 holds "blue" (length = 4)

Enter which element of str1 to display: 1
Element #1 of str1 is 'e'

Enter which element of str2 to display: 2
Element #2 of str2 is 'l'

Enter which element of str3 to display: 3
Element #3 of str3 is 'e'

Enter a value to append to str1 (no spaces): rose

Enter a value to append to str2 (no spaces): house

Enter a value to append to str3 (no spaces): sky

After appending...
str1 holds "redrose" (length = 7)
str2 holds "yellowhouse" (length = 11)
str3 holds "bluesky" (length = 7)

Comparing str1 and str2...
"redrose" is less than "yellowhouse"

test the = operator, after str1 = str2;
str1 holds "yellowhouse" (length = 11)
str2 holds "yellowhouse" (length = 11)

After str1 = str1 + s1:
str1 holds "yellowhouserose" (length = 15)
str2 holds "yellowhouse" (length = 11)

test the copy constructor, after str4(str3);
str3 holds "bluesky" (length = 7)
str4 holds "bluesky" (length = 7)

after appending str3 by str2
str3 holds "blueskyyellowhouse" (length = 18)
str4 holds "bluesky" (length = 7)

str2, str4 are not changed. Type any letter to quit.
a
*/