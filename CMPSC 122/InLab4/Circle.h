#ifndef Circle_H
#define Circle_H

#include "Point.h"

class Circle
{
public:
    Circle(Point cp, double r);           // center point and radius
    Circle(double x, double y, double r); // another constructor
    double getArea();                     // return the area of the circle
    void moveCircle(double dx, double dy);
    bool isItOrigin();
    double getRadius();
    Point getCenter();
    void display() const;
    // space for adding new member functions
private:
    Point center;
    double radius;
};

#endif