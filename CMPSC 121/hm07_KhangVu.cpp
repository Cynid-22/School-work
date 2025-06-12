#include <iostream>

using namespace std;

void swap(int &a, int &b);
void swap(string &a, string &b);
void swap(double &a, double &b);

int main() {
    int int_x, int_y; 
    double double_x, double_y;
    string str_x, str_y; 

    int_x = 5; 
    int_y = 6;

    double_x = 1.1;
    double_y = 2.2;

    str_x = "firstname";
    str_y = "lastname";

    swap(int_x, int_y);
    swap(double_x, double_y);
    swap(str_x, str_y);

    cout << "int_x: " << int_x << " and int_y: " << int_y << endl;
    cout << "double_x: " << double_x << " and double_y: " << double_y << endl;
    cout << "str_x: " << str_x << " and str_y: " << str_y << endl;
    return 0;
}

void swap(int &a, int &b)
{
    int temp = a;
    a = b;
    b = temp;
}

void swap(string &a, string &b)
{
    string temp = a;
    a = b;
    b = temp;
}

void swap(double &a, double &b)
{
    double temp = a;
    a = b;
    b = temp;
}

/*
output:
int_x: 6 and int_y: 5
double_x: 2.2 and double_y: 1.1
str_x: lastname and str_y: firstname
*/