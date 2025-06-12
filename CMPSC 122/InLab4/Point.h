#ifndef Point_H
#define Point_H

class Point {
public:
    Point();                   // default constructor
    Point(double x, double y); // another constructor
    double x() const;          // get function, return _x
    double y() const;          // get function, return _y
    double magnitude() const;
    void move(double dx, double dy);
    void print() const;
private:
    double _x, _y;
};

#endif