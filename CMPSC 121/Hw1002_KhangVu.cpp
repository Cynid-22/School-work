#include <iostream>

using namespace std;

struct gpa
{   
    string course;
    double score;
};

struct student
{
    int studentID;
    string fname;
    string lname;
    int yearInSchool;
    gpa grade;
};

student getInfo(student s)
{
    cout << "Enter Student ID: ";
    cin >> s.studentID;
    cout << "Enter first name: ";
    cin >> s.fname;
    cout << "Enter last name: ";
    cin >> s.lname;
    cout << "Enter year in school: ";
    cin >> s.yearInSchool;
    cout << "Enter course: ";
    cin >> s.grade.course;
    cout << "Enter score: ";
    cin >> s.grade.score;

    return s;
}

int main()
{
    student a;
    a = getInfo(a);

    cout << "\nThe output: " << endl;
    cout << "StudentID: " << a.studentID << endl;
    cout << "First name: " << a.fname << endl;
    cout << "Last name: " << a.lname << endl;
    cout << "Year in school: " << a.yearInSchool << endl;
    cout << "Course: " << a.grade.course << endl;
    cout << "The GPA: " << a.grade.score << endl;
    return 0;
}

/*
Output:
Enter Student ID: 123456
Enter first name: Khang
Enter last name: Vu
Enter year in school: 1
Enter course: ENGL83S
Enter score: 4.00

The output: 
StudentID: 123456
First name: Khang
Last name: Vu
Year in school: 1
Course: ENGL83S
The GPA: 4
*/