#include <iostream>
#include <cmath>
#include "Point.h"
#include "Circle.h"
using namespace std;
int main()
{
    Point p0;
    Point p1(5, -2);
    Point p2 = p1;
    p0 = p1;
    cout << "p0.x() = " << p0.x() << "\n";
    cout << "p0.y() = " << p0.y() << "\n";
    cout << p0.magnitude() << endl;
    p0.move(3, 9);
    p0.print();
    
    Circle c(Point(1, 6), 5);
    cout << "Area of circle: " << c.getArea() << endl;
    c.display();
    c.moveCircle(6, 8);
    c.display();
    cout << endl << "Is it at Origin: " << c.isItOrigin() << endl;
    cout << "Radius: " << c.getRadius() << endl;
    c.getCenter().print();

    return 0;
}

/*
Output:
p0.x() = 5
p0.y() = -2
5.38516
(8, 7)Area of circle: 78.5398
[(1, 6), 5][(7, 14), 5]Is it at Origin: 0
Radius: 5
(7, 14)
*/