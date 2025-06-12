#ifndef _MYSTRING2_H
#define _MYSTRING2_H
#include <iostream>
#include <cstring> // for strlen(), etc.
using namespace std;

#define MAX_STR_LENGTH 200

class String
{
    public:
        String();
        String(const char s[]);
        void append(const String &str);

        ~String();
        String(const String &origString);
        String &operator=(const String &origString);

        bool operator==(const String &str) const;
        bool operator!=(const String &str) const;
        bool operator>(const String &str) const;
        bool operator<(const String &str) const;
        bool operator>=(const String &str) const;
        String operator+=(const String &str);

        void print(ostream &out) const;
        int length() const;

        char operator[](int i) const;

    private:
        char *myArrayPtr;
        int len;
};

ostream &operator<<(ostream &out, const String &r);

#endif