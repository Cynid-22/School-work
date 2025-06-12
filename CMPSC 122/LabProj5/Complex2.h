#ifndef COMPLEX2_H
#define COMPLEX2_H
#include <iostream>
#include <iomanip>
using namespace std;

class Complex {     // complex number class
    public:
        Complex(double re = 0.0, double im = 0.0);   
        Complex operator+(Complex & z);
        Complex operator-(Complex & z);
        Complex operator*(Complex & z);
        Complex operator/(Complex & z);
        bool operator==(Complex & z);
        bool operator!=(Complex & z);
        
        // double operator%(Complex & z);
        void print(ostream& os);

    private:
        double re;
        double im;
};

ostream & operator<<(ostream & os, Complex & z);
// May generate linker issue if the code is implemented here.
// The code should be implemented in .cpp source file

#endif

