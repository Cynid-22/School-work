// timer.h for CMPSC122 lab 
// Measure time in Window  
// Define a Timer object t, use t.start() for beginning of the algorithm, t.stop() for the ending, and t.show() for printing.

#ifndef TIMER_H
#define TIMER_H

#include <ctime>
#include <string>
#include <iostream>
using namespace std;

class Timer
{
  public:
    
    Timer();
    Timer(const std::string &label);
    ~Timer();
    void start(void);
    void stop(void);
    void show(void);
 
  private:
    void reset(void);
    string label;
    long tps;
    clock_t start_time, end_time;
    double usertime,
		   systemtime,
		   elapsedtime,
		   waittime;
};

#endif

