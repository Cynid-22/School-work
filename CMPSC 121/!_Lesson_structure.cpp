#include <iostream>

using namespace std;

struct gpa 
{
    string course;
    double score;
};

struct student
{
    int studentID; //all 5 of these are member of student, and are access like "student.fname", "student.lname", ...
    string fname;
    string lname;
    int yearInSchool;
    gpa courseGpa; // Nested struct
};

void showItem (student a) // this can also be use as a function argument
{
    cout << a.fname << endl;
    cout << a.lname << endl;
}

student item() // return a struct from function
{
    student tmp = {13, "khang"};
    return tmp;
}

int main()
{
    student arr[5]; // "student" now become a data type like int, long, short, char, string, ...
    student a = {313, "khang", "vu", 2, "CMPSC", 3.98}; // declare variable, CANNOT SKIP OVER MEMBER WHEN DECLARE
    student b;

    cin >> b.fname >> b.lname; // enter fname and lname in student
    cin >> b.courseGpa.course; // enter course in gpa in student

    showItem(b); // show fname and lname of b

    student c;
    c = item(); // assign to c
    cout << c.studentID;

    for (int i = 0; i < 5; ++i) { // struct can also be use in array
        cin >> arr[i].studentID >> arr[i].fname >> arr[i].lname;
    }
    for (int i = 0; i < 5; ++i) {
        cout << arr[i].studentID << " " << arr[i].fname << " " << arr[i].lname;
    }


    student stu1;
    student *stuptr = &stu1;

    (*stuptr).fname = "khang";  // use (*xyz).abc to access member
    stuptr->fname = "test";     // or use xyz->abc



    return 0;
}

/*
Output:

*/