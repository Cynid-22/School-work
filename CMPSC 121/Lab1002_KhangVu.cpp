#include <iostream>

using namespace std;

struct rectangle
{
    double length;
    double width;
    double perimenter;
    double area;
};

rectangle getInfo(rectangle s)
{   
    double l, w;
    cout << "Enter length: ";
    cin >> l;
    cout << "Enter width: ";
    cin >> w;

    s.width = w;
    s.length = l;
    return s;
}

int main()
{
    rectangle shape;

    shape = getInfo(shape);

    shape.perimenter = 2*(shape.width + shape.length);
    shape.area = shape.width * shape.length;

    cout << "\nThe output: ";
    cout << "\nPerimeter: " << shape.perimenter;
    cout << "\nArea: " << shape.area;


    return 0;
}

/*
Output:
Enter length: 3         
Enter width: 4

The output: 
Perimeter: 14
Area: 12
*/