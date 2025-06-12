//
// Name: skeleton template for overloading operators  
//
// One Complex object holds one Complex number

#include "Complex2.h"
#include <iomanip>
#include <cmath>

//------------------------------ Complex ------------------------------------
// default constructor:  parameters are real and imaginary parts respectively
Complex::Complex(double re, double im)
{
   this->re = re;
   this->im = im;
}

//(a)------------------------------- add -------------------------------------
// overloaded +: addition of 2 complex numbers, current object and parameter
Complex Complex::operator+(Complex & z) 
{
   Complex c;
   c.re = re + z.re;
   c.im = im + z.im;
   return c;
}

//(b)------------------------------ subtract ---------------------------------
// overloaded -: subtract 2 complex numbers, current object and parameter
Complex Complex::operator-(Complex & z) 
{
   Complex c;
   c.re = re - z.re;
   c.im = im - z.im;
   return c;
}

//(c)------------------------------ multiply ---------------------------------
// overloaded *: multiply 2 complex numbers, current object and parameter
Complex Complex::operator*(Complex & z) 
{
   Complex c;
   c.re = re*z.re - im*z.im;
   c.im = re*z.im + im*z.re;
   return c;
}

//(d)-------------------------------- divide ---------------------------------
// overloaded /: divide 2 complex numbers, current object and parameter
// check division by zero condition
Complex Complex::operator/(Complex & z) 
{
   Complex c;
   if (z.re == z.im && z.im == 0)
      cout << "DIVIDE BY ZERO ERROR!!!" << endl;
   c.re = (re*z.re + im*z.im)/(z.re*z.re + z.im*z.im);
   c.im = (im*z.re - re*z.im)/(z.re*z.re + z.im*z.im);
   return c;
}

//(e)-------------------------------- equal ----------------------------------
// overloaded ==: equal comparison of current Complex object and parameter
bool Complex::operator==(Complex & z) 
{
   return (re == z.re && im == z.im);
}


//(f)------------------------------- not equal -------------------------------
// overloaded !=: not equal comparison of current Complex object and parameter
bool Complex::operator!=(Complex & z) 
{
   return !(re == z.re && im == z.im);
}


//(g)-------------------------------- print-----------------------------------
// overloaded print()
void Complex::print(ostream& os)
{
   os << "(";
   if (abs(re) == floor(abs(re)))
      os << re;
   else
      os << fixed << setprecision(1) << re;

   if (im==1)
      os << " + ";
   else
      if(im==-1)
         os << " - ";
      else {
         if (im>=0) {
            os <<" + ";
            if (abs(im) == floor(abs(im)))
               os << im;
            else
               os << fixed << setprecision(1) << im;
         }
         else {
            os << " - ";
            if (abs(im) == floor(abs(im)))
               os << -im;
            else
               os << fixed << setprecision(1) << -im;
         }
   }
   os << "i)";
}


//(h)--------------------------------- << -------------------------------------
// overloaded << (output operator)
ostream & operator<<(ostream & os, Complex & z)
{
   z.print(os);
   return os;
}

