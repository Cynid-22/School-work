#include <iostream>
#include <cmath>
#include "Point.h"
#include "Circle.h"

using namespace std;

Circle::Circle(Point cp, double r)
{
    center = cp;
    radius = r;
}

Circle::Circle(double x, double y, double r)
{
    center = Point(x,y);
    radius = r;
}

double Circle::getArea() 
{
    return M_PI * radius * radius;
}

void Circle::moveCircle(double dx, double dy)
{
    center.move(dx, dy);
}

bool Circle::isItOrigin()
{
    if (center.x() == 0 & center.y() == 0)
        return true;
    else
        return false;
}

double Circle::getRadius()
{
    return radius;
}

Point Circle::getCenter()
{
    return center;
}

void Circle::display() const
{
    cout << "[" << "(" << center.x() << ", "<< center.y() <<")" << ", " << radius << "]";
}





