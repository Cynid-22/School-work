#include <iostream>

using namespace std;

class Rectangle
{
    private:
        double width;
        double length;

    public:
        Rectangle(double width, double length) {
            this->width = width;
            this->length = length;
        }
        double getArea() {
            return this->width * this->length;
        }
        double getPerimeter() {
            return 2*(this->width + this->length);
        }

};

int main()
{
    double len, wid;
    cout << "Enter width: ";
    cin >> wid;
    cout << "Enter length: ";
    cin >> len;

    Rectangle rec(wid,len);

    cout << "\narea: " << rec.getArea() <<"\nperimeter: " << rec.getPerimeter();

    return 0;
}

/*
Output:
Enter width: 3
Enter length: 4

area: 12
perimeter: 14
*/