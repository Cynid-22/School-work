// driver for cmpsc122 Lab 4 for complex number

#include <iostream>
#include <iomanip>
using namespace std;
#include "Complex.h"

int main() {
   Complex c1(-3,8), c2(-15,-20), c3(5), c4;

   cout << setprecision(2) << fixed;

   cout << "c1 = ";  c1.print(true); cout << endl;
   cout << "c2 = ";  c2.print(); cout << endl;
   cout << "c3 = ";  c3.print(); cout << endl;
   cout << "c4 = ";  c4.print(true); cout << endl << endl;

 
   c3 = c1.add(c2);  
   cout << "c3 = ";  c1.print(true);  cout << " + ";  c2.print(true);
   cout << " = ";  c3.print();  cout << endl << endl;

   c3 = c1.subtract(c2);
   cout << "c3 = ";  c1.print(true);  cout << " - ";  c2.print(true);
   cout << " = ";  c3.print();  cout << endl << endl;

   c3 = c1.multiply(c2);
   cout << "c3 = ";  c1.print(true);  cout << " x ";  c2.print(true);
   cout << " = ";  c3.print();  cout << endl << endl;

   c3 = c1.divide(c2);
   cout << "c3 = ";  c1.print(true);  cout << " / ";  c2.print(true);
   cout << " = ";  c3.print();  cout << endl << endl;

   c3 = c1.divide(c4);
   cout << "c3 = ";  c1.print(true);  cout << " / ";  c4.print(true);
   cout << " = undefine" << endl << endl;

   double modulus_c1 = c1.modulus();
   cout << "Modulus of c1 = " << modulus_c1 << endl << endl;

   bool is_equal = c1.equal(c2);
   cout << "c1 = ";  c1.print(); cout << endl;
   cout << "c2 = ";  c2.print(); cout << endl;
   cout << "c1 " << (is_equal ? "is" : "is not") << " equal to c2";
   cout << endl << endl;

   c2 = c1;
   is_equal = c1.equal(c2);
   cout << "c1 = ";  c1.print(); cout << endl;
   cout << "c2 = ";  c2.print(); cout << endl;
   cout << "c1 " << (is_equal ? "is" : "is not") << " equal to c2";
   cout << endl << endl;
   return 0;
}


/*
Your sample run output MUST be as shown below:

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