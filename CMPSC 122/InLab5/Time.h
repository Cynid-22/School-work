#pragma once
#include <iostream>
using namespace std;

class Time {
    public:

        // Constructors
        Time();
        Time(unsigned hours, unsigned minutes, char am_pm);
        void operator+=(unsigned minutes);
        void operator-=(unsigned minutes);
        Time operator+(unsigned minutes);
        Time operator-(unsigned minutes);
        void operator=(string timeInput);

        // Functions to get the values of member
        unsigned getHours(); // Return the hour number
        unsigned getMinutes(); // Return the minute number
        char getAMorPM(); // Return the AM PM value

        // Set, Display as discussed in class
        void Set(unsigned hours, unsigned minutes, char am_pm);//Set the time
        void Display();                                        //Display the time

        // A convenient forward function 
        void Forward(int minutes);    
        void Backward(int minutes);   

    private:
        unsigned myHours, myMinutes;
        char myAMorPM;
};