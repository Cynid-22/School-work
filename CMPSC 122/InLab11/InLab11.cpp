#include <iostream>
#include <fstream>

using namespace std;

struct InputNode {
    string studentName;
    string course;
    float grade;
};

struct TreeNode {
    string studentName;
    float averageGrade;
    int numCourses;
    TreeNode * left;
    TreeNode * right;

    TreeNode(string name, float grade)
        : studentName(name), averageGrade(grade), numCourses(1), left(nullptr), right(nullptr) {}
};

void Insert(TreeNode *& root, string studentName, float grade)
{
    if (root == nullptr) {
        root = new TreeNode(studentName, grade);
        return;
    }
    if (studentName < root->studentName)
        Insert(root->left, studentName, grade);
    else if (studentName > root->studentName)
        Insert(root->right, studentName, grade);
    else {
        root->averageGrade = ((root->averageGrade * root->numCourses) + grade) / (root->numCourses + 1);
        root->numCourses++;
    }
}

void PrintTree(TreeNode* root, ofstream& out)
{
    if (root != nullptr) {
        PrintTree(root->left, out);
        out << root->studentName << " " << root->averageGrade << endl;
        PrintTree(root->right, out);
    }
}

int main()
{
    string InputFile, OutputFile, myText;
    TreeNode* root = NULL;
    InputNode inputTree[100];

    cout << "Please type the text file name: ";
    cin >> InputFile;
    cout << "Please give the output text file name: ";
    cin >> OutputFile;

    fstream MyFile(InputFile);

    int iter = 0;
    while(getline(MyFile, myText)) {
        int i = 0;
        string str_grade;
        while (myText[i] != ' ') {
            inputTree[iter].studentName += myText[i];
            ++i;
        }
        ++i;
        while (myText[i] != ' ') {
            inputTree[iter].course += myText[i];
            ++i;
        }
        ++i;
        while(myText[i] != '\0') {
            str_grade += myText[i];
            ++i;
        }
        inputTree[iter].grade = stoi(str_grade);
        iter++;
    }
    MyFile.close();

    for (int i = 0; i < iter; ++i) {
        Insert(root, inputTree[i].studentName, inputTree[i].grade);
    }

    ofstream OutFile(OutputFile);
    PrintTree(root, OutFile);

    OutFile.close();



    cout << "You are done! You can open the file " << OutputFile <<" to check.";

    return 0;
}

/*
Output:
Please type the text file name: input.txt
Please give the output text file name: a.txt
You are done! You can open the file a.txt to check.

output file ("a.txt"):
Alex 70
Ben 75
Bob 70
Bruce 85
Carte 75
Charles 90
Eddy 35
Luke 85
Mike 80
Patric 30
Peter 95
Pique 55
*/