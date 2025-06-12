#include <iostream>
#include <cmath>

using namespace std;

const double PI = M_PI; // M_PI is in cmath

class Car 
{
    private: // can only access if in the same class
        //field or class variable. Same as structure
        string type;
        string model;
        string color;
        int speed = 0;

    public: // can be accessed by function outside of the class
        //constructor
        Car (string type, string model, string color) { // this does not have a return type. Similar to "void"
            this->type = type;
            this->model = model;
            this->color = color;
        }

        //method
        int increaseSpeed (int increment) {
            this->speed = this->speed + increment;
            return this->speed;
        }

        ~Car();  // destructor
};


class Circle 
{
    private:
        double radius;
        double diameter;
        double area;

    public:
        Circle (double radius, double diameter) {
            this->radius = radius;
            this->diameter = diameter;
        }
        double getArea () {
            return PI * pow(this->radius, 2.0);
        }
};

class construct
{
    public:
        int a, b; // in class, anything that is not declared as "public" will be construded as "private"

        construct() {}
};

class Sample
{
    private:
        int id;

    public:
        Sample (int x) {
            this->id = x;
        }
        void display () {
            cout << "ID: " << id << endl;
        }
};

int main()
{
    Circle c(1,2); // Circle (double radius, double diameter)

    double area = c.getArea();

    cout << area << endl;

    Car car1("sedan", "2018", "Red"); // create an variable with the template of class Car
    cout << car1.increaseSpeed(5) << endl;
    cout << car1.increaseSpeed(7) << endl;
    cout << car1.increaseSpeed(5) << endl;


    construct test;
    cout << "a: " << test.a << " b: " << test.b << endl;
    test.a = 5; test.b = 10;
    cout << "a: " << test.a << " b: " << test.b << endl;


    // Copy constructor
    Sample obj1(10);
    obj1.display();

    Sample obj2(obj1); // obj2 = obj1
    obj2.display();

    return 0;
}

/*
Output:

*/