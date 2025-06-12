#include <iostream>
#include <cmath>

using namespace std;

class Circle
{
    private:
        double radius;
        double diameter;

    public:
        Circle(double radius, double diameter) {
            this->radius = radius;
            this->diameter = diameter;
        }
        double getArea() {
            return this->radius*this->radius*M_PI;
        }
        double getPerimeter() {
            return 2*this->radius*M_PI;
        }

};

int main()
{
    double ra, dia;
    cout << "Enter radius: ";
    cin >> ra;
    cout << "Enter diameter: ";
    cin >> dia;

    Circle cir(ra, dia);

    cout << "\narea: " << cir.getArea() << "\nperimeter: " << cir.getPerimeter();

    return 0;
}

/*
Output:
Enter radius: 1
Enter diameter: 2

area: 3.14159
perimeter: 6.28319
*/