//
// a Complex object holds one Complex number
//
#include "Complex.h"
#include <iomanip>
#include <iostream>
#include <cmath>

using namespace std;

//------------------------------ Complex ------------------------------------
// default constructor:  parameters are real and imaginary parts respectively
Complex::Complex(double re, double im)
{
   this->re = re;
   this->im = im;
}

//(a)------------------------------- add ------------------------------------
// addition of 2 complex number, current object and parameter
Complex Complex::add(const Complex & z)
{
   Complex c;

   c.re = re + z.re;
   c.im = im + z.im;
   return c;
}

//(b)---------------------------- subtract ----------------------------------
// subtraction of 2 complex number, current object and parameter

Complex Complex::subtract(const Complex & z)
{
   Complex c;

   c.re = re - z.re;
   c.im = im - z.im;
   return c;
}

//(c)---------------------------- multiply ----------------------------------
// multiplication of 2 complex number, current object and parameter
Complex Complex::multiply(const Complex & z)
{
   Complex c;

   c.re = re*z.re - im*z.im;
   c.im = re*z.im + im*z.re;
   return c;
}

//(d)------------------------------ divide ----------------------------------
// division of 2 complex number, current object and parameter,
// division by zero crashes
Complex Complex::divide(const Complex & z)
{
   Complex c;
   if (z.re == z.im && z.im == 0)
      cout << "DIVIDE BY ZERO ERROR!!!" << endl;

   c.re = (re*z.re + im*z.im)/(z.re*z.re + z.im*z.im);
   c.im = (im*z.re - re*z.im)/(z.re*z.re + z.im*z.im);
   return c;
}

//(e)------------------------------ equality ---------------------------------
// equality of 2 complex number, current object and parameter
bool Complex::equal(const Complex & z)
{
   return (re == z.re && im == z.im);
}

//(f)------------------------------ modulus ---------------------------------
// modulus value of a complex number

double Complex::modulus()
{
   return sqrt(re*re + im*im);
}



//(g)------------------------------ print -----------------------------------
// print the complex number with the format: a + bi (or a - bi)
// enclosed the complex number with parenthesis if is_parenthesis is true
// e.g., is_parenthesis = true: (4 + 2i), (-10 +7i), etc. 
void Complex::print(bool is_parenthesis)
{
   if (is_parenthesis)
      if (im>=0)
         cout << fixed << setprecision(2) << "(" << re << " + " << im << "i)";
      else
         cout << fixed << setprecision(2) << "(" << re << " - " << -im << "i)";
   else
      if (im>=0)
         cout << fixed << setprecision(2) << re << " + " << im << "i";
      else
         cout << fixed << setprecision(2) << re << " - " << -im << "i";
         
}

/*
Output:

c1 = (-3.00 + 8.00i)
c2 = -15.00 - 20.00i
c3 = 5.00 + 0.00i
c4 = (0.00 + 0.00i)

c3 = (-3.00 + 8.00i) + (-15.00 - 20.00i) = -18.00 - 12.00i

c3 = (-3.00 + 8.00i) - (-15.00 - 20.00i) = 12.00 + 28.00i

c3 = (-3.00 + 8.00i) x (-15.00 - 20.00i) = 205.00 - 60.00i

c3 = (-3.00 + 8.00i) / (-15.00 - 20.00i) = -0.18 - 0.29i

DIVIDE BY ZERO ERROR!!!
c3 = (-3.00 + 8.00i) / (0.00 + 0.00i) = undefine

Modulus of c1 = 8.54

c1 = -3.00 + 8.00i
c2 = -15.00 - 20.00i
c1 is not equal to c2

c1 = -3.00 + 8.00i
c2 = -3.00 + 8.00i
c1 is equal to c2
*/ 






