#include <iostream>
#include <string>

#include "Time.h"

using namespace std;

void ShowNowTime(Time& timept, string objectName) {
	cout << "Time object " << objectName << " is now ";
	timept.Display();
	cout << endl;
}

int main()
{
	Time nowTime;
	nowTime.Set(11, 30, 'P');
	ShowNowTime(nowTime, "nowTime");
	cout << "Forward 20 Minutes." << endl;
	//nowTime.Forward(20);
	nowTime += 20;
	ShowNowTime(nowTime, "nowTime");
	// cout << "Backward 30 Minutes." << endl;
	// //nowTime.Forward(30);
	// nowTime -= 30;
	// ShowNowTime(nowTime, "nowTime");
	cout << "Forward 20 Minutes." << endl;
	//nowTime.Forward(20);
	nowTime += 20;
	ShowNowTime(nowTime, "nowTime");
	cout << "Forward 500 Minutes." << endl;
	//nowTime.Forward(500);
	nowTime += 500;
	ShowNowTime(nowTime, "nowTime");
	cout << "Forward 330 Minutes." << endl;
	//nowTime.Forward(330);
	nowTime += 330;
	ShowNowTime(nowTime, "nowTime");

	cout << "Create a testTime object, which forwards nowTime by 30 Minutes. (Testing the + operator.)" << endl;
	Time testTime = nowTime + 30;
	ShowNowTime(testTime, "testTime");

	ShowNowTime(nowTime, "nowTime");
	cout << "nowTime should NOT be changed from the + operator." << endl;

	cout << endl;

	cout << "Assigning string \"0840A\" to nowTime." << endl;
	nowTime = "0840A";
	ShowNowTime(nowTime, "nowTime");

	cout << "Assigning string \"1200P\" to nowTime." << endl;
	nowTime = "1200P";
	ShowNowTime(nowTime, "nowTime");
}
