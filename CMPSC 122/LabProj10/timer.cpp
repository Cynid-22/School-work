// timer.cpp for CMPSC122 lab 
// Measure time in Window  
// Define a Timer object t, use t.start() for beginning of the algorithm, t.stop() for the ending, and t.show() for printing.

#include <ctime>
#include <string>
#include <iostream>
#include "timer.h"
using namespace std;


Timer::Timer()
{
  label = "Process Timer";
  reset();
}


Timer::Timer(const std::string &label)
{
  Timer::label = label;
  reset();
}


Timer::~Timer()
{
	
}


void Timer::reset(void)
{
  tps = CLOCKS_PER_SEC;
  end_time = 0;
  usertime = 0;
  systemtime = 0;
  elapsedtime = 0;
  waittime = 0;
}


void Timer::start(void)
{
    start_time = clock();
}


void Timer::show(void)
{
 cout << "  "
      << label << "\n"
      << "  -------------------------------\n"
      << "  Elapsed Time: "
      << elapsedtime
      << "s" << std::endl;

}


void Timer::stop(void)
{
  end_time = clock();
  elapsedtime = ((double)(end_time -
                start_time )/(double)tps );
  if (elapsedtime < 0.001)
  {
    elapsedtime = 0.001;
  }

  if ( waittime < 0.00 )
  {
    waittime = 0.00;
  }
}

