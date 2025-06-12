#include <iostream>

using namespace std;

template <typename T>
class Queue {
private:
    struct Node {
        T data;
        Node* next;
        Node(T val) : data(val), next(nullptr) {}
    };
    
    Node* front;
    Node* rear;
    int count;

public:
    Queue() : front(nullptr), rear(nullptr), count(0) {}
    
    ~Queue() {
        while (!isEmpty()) {
            dequeue();
        }
    }
    
    void enqueue(T val) {
        Node* newNode = new Node(val);
        if (rear == nullptr) {
            front = rear = newNode;
        } else {
            rear->next = newNode;
            rear = newNode;
        }
        count++;
    }
    
    T dequeue() {
        if (isEmpty()) {
            cout << "Queue is empty";
        }
        Node* temp = front;
        T val = temp->data;
        front = front->next;
        
        if (front == nullptr) {
            rear = nullptr;
        }
        
        delete temp;
        count--;
        return val;
    }
    
    bool isEmpty() const {
        return front == nullptr;
    }
    
    int size() const {
        return count;
    }
    
    T peek() const {
        if (isEmpty()) {
            cout << "Queue is empty";
        }
        return front->data;
    }
};

class RestaurantManager {
private:
    int totalLargeTables;
    int totalSmallTables;
    int occupiedLargeTables;
    int occupiedSmallTables;
    Queue<string> largeWaitingLine;
    Queue<string> smallWaitingLine;

public:
    RestaurantManager(int large, int small) 
        : totalLargeTables(large), totalSmallTables(small), 
          occupiedLargeTables(0), occupiedSmallTables(0) {}

    void processCommand(const string& command) {
        string cmd;
        string tableType;
        string name;
        
        // Simple command parsing
        size_t firstSpace = command.find(' ');
        if (firstSpace != string::npos) {
            cmd = command.substr(0, firstSpace);
            size_t secondSpace = command.find(' ', firstSpace + 1);
            
            if (secondSpace != string::npos) {
                tableType = command.substr(firstSpace + 1, secondSpace - firstSpace - 1);
                name = command.substr(secondSpace + 1);
            } else {
                tableType = command.substr(firstSpace + 1);
            }
        } else {
            cmd = command;
        }

        if (cmd == "new") {
            if (tableType != "large" && tableType != "small") {
                cout << "Invalid command!" << endl;
                return;
            }
            
            if (name.empty()) {
                cout << "Invalid command!" << endl;
                return;
            }

            if (tableType == "large") {
                handleNewCustomer(name, true);
            } else {
                handleNewCustomer(name, false);
            }
        }
        else if (cmd == "checkout") {
            if (tableType != "large" && tableType != "small") {
                cout << "Invalid command!" << endl;
                return;
            }
            
            if (tableType == "large") {
                handleCheckout(true);
            } else {
                handleCheckout(false);
            }
        }
        else if (cmd == "info") {
            displayInfo();
        }
        else {
            cout << "Invalid command!" << endl;
        }
    }

private:
    void handleNewCustomer(const string& name, bool needsLargeTable) {
        if (needsLargeTable) {
            if (occupiedLargeTables < totalLargeTables) {
                occupiedLargeTables++;
                cout << "New customer " << name << " takes an available large table." << endl;
            } else {
                largeWaitingLine.enqueue(name);
                cout << "New customer " << name << " enters the large waiting line." << endl;
            }
        } else {
            if (occupiedSmallTables < totalSmallTables) {
                occupiedSmallTables++;
                cout << "New customer " << name << " takes an available small table." << endl;
            } else if (occupiedLargeTables < totalLargeTables) {
                occupiedLargeTables++;
                cout << "New customer " << name << " takes an available large table." << endl;
            } else {
                smallWaitingLine.enqueue(name);
                cout << "New customer " << name << " enters the small waiting line." << endl;
            }
        }
    }

    void handleCheckout(bool isLargeTable) {
        if (isLargeTable) {
            if (occupiedLargeTables == 0) {
                cout << "No customer occupied any large table!" << endl;
                return;
            }
            
            occupiedLargeTables--;
            cout << "New large table seat(s) is available." << endl;
            
            // Check waiting lines
            if (!largeWaitingLine.isEmpty()) {
                string customer = largeWaitingLine.dequeue();
                occupiedLargeTables++;
                cout << "Next customer " << customer << " takes an available large table." << endl;
            } else if (!smallWaitingLine.isEmpty()) {
                string customer = smallWaitingLine.dequeue();
                occupiedLargeTables++;
                cout << "Next customer " << customer << " takes an available large table." << endl;
            }
        } else {
            if (occupiedSmallTables == 0) {
                cout << "No customer occupied any small table!" << endl;
                return;
            }
            
            occupiedSmallTables--;
            cout << "New small table seat(s) is available." << endl;
            
            // Check waiting lines (only small table waiters can take small tables)
            if (!smallWaitingLine.isEmpty()) {
                string customer = smallWaitingLine.dequeue();
                occupiedSmallTables++;
                cout << "Next customer " << customer << " takes an available small table." << endl;
            }
        }
    }

    void displayInfo() {
        cout << "Small tables: " << occupiedSmallTables << " / " << totalSmallTables << endl;
        cout << "Large tables: " << occupiedLargeTables << " / " << totalLargeTables << endl;
        cout << smallWaitingLine.size() << " customer(s) waiting for small table." << endl;
        cout << largeWaitingLine.size() << " customer(s) waiting for large table." << endl;
    }
};

int main() {
    cout << "Welcome to the Restaurant Table Management System." << endl;
    
    int largeTables, smallTables;
    cout << "Enter the total number of large tables: ";
    cin >> largeTables;
    cout << "Enter the total number of small tables: ";
    cin >> smallTables;
    
    cin.ignore();
    
    RestaurantManager manager(largeTables, smallTables);
    
    string command;
    while (true) {
        cout << "\n" << "Enter Command: ";
        getline(cin, command);
        
        if (command.empty()) {
            break;
        }
        
        manager.processCommand(command);
    }
    
    return 0;
}

/*
Output: 

Welcome to the Restaurant Table Management System.
Enter the total number of large tables: 2
Enter the total number of small tables: 3

Enter Command: checkout large
No customer occupied any large table!

Enter Command: checkout sma;;
Invalid command!

Enter Command: new large Edison
New customer Edison takes an available large table.

Enter Command: new large Matt
New customer Matt takes an available large table.

Enter Command: new large Luke
New customer Luke enters the large waiting line.

Enter Command: info
Small tables: 0 / 3
Large tables: 2 / 2
0 customer(s) waiting for small table.
1 customer(s) waiting for large table.

Enter Command: checkout large
New large table seat(s) is available.
Next customer Luke takes an available large table.

Enter Command: checkout large
New large table seat(s) is available.

Enter Command: info
Small tables: 0 / 3
Large tables: 1 / 2
0 customer(s) waiting for small table.
0 customer(s) waiting for large table.

Enter Command: checkout large
New large table seat(s) is available.

Enter Command: checkout large
No customer occupied any large table!

Enter Command: New large Todd
Invalid command!

Enter Command: new small Andy
New customer Andy takes an available small table.

Enter Command: new small Ken
New customer Ken takes an available small table.

Enter Command: new small Dan
New customer Dan takes an available small table.

Enter Command: new small Frank
New customer Frank takes an available large table.

Enter Command: new small Will
New customer Will takes an available large table.

Enter Command: ìno
Invalid command!

Enter Command: info
Small tables: 3 / 3
Large tables: 2 / 2
0 customer(s) waiting for small table.
0 customer(s) waiting for large table.

Enter Command: new small Smith
New customer Smith enters the small waiting line.

Enter Command: new large Mary
New customer Mary enters the large waiting line.

Enter Command: info
Small tables: 3 / 3
Large tables: 2 / 2
1 customer(s) waiting for small table.
1 customer(s) waiting for large table.

Enter Command: checkout large
New large table seat(s) is available.
Next customer Mary takes an available large table.

Enter Command: testing invalid command
Invalid command!

Enter Command: checkout Large
Invalid command!

Enter Command: check our Small Peter
Invalid command!

Enter Command: exit
Invalid command!

Enter Command:
*/