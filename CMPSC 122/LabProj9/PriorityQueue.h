#pragma once

#include <iostream>
using namespace std;

template <typename T>
class PriorityQueue {
private:
    struct Node {
        T item;
        int priority;
    };

    Node* array;
    int capacity;
    int size;

    void expandCapacity() {
        int newCapacity = capacity + (capacity / 2);
        Node* newArray = new Node[newCapacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        delete[] array;
        array = newArray;
        capacity = newCapacity;
    }

    int findIndex(const T& item) const {
        for (int i = 0; i < size; i++) {
            if (array[i].item == item) return i;
        }
        return -1;
    }

    int highestPriorityIndex() const {
        if (size == 0) return -1;
        int maxIndex = 0;
        for (int i = 1; i < size; i++) {
            if (array[i].priority > array[maxIndex].priority) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

public:
    PriorityQueue(int initCapacity = 1024) : capacity(initCapacity), size(0) {
        array = new Node[capacity];
    }

    ~PriorityQueue() {
        delete[] array;
    }

    PriorityQueue(const PriorityQueue& other) : capacity(other.capacity), size(other.size) {
        array = new Node[capacity];
        for (int i = 0; i < size; i++) {
            array[i] = other.array[i];
        }
    }

    PriorityQueue& operator=(const PriorityQueue& other) {
        if (this != &other) {
            delete[] array;
            capacity = other.capacity;
            size = other.size;
            array = new Node[capacity];
            for (int i = 0; i < size; i++) {
                array[i] = other.array[i];
            }
        }
        return *this;
    }

    bool IsEmpty() const {
        return size == 0;
    }

    void Insert(T item, int priorityVal) {
        if (findIndex(item) != -1) {
            cout << "***Error: Trying to insert duplicated item." << endl;
            return;
        }
        if (size == capacity) {
            expandCapacity();
        }
        array[size++] = {item, priorityVal};
    }

    void ChangePriority(T item, int priorityVal) {
        int index = findIndex(item);
        if (index != -1) {
            array[index].priority = priorityVal;
        }
    }

    T Pull() {
        int index = highestPriorityIndex();
        if (index == -1) throw runtime_error("Priority queue is empty.");
        T highestPriorityItem = array[index].item;
        array[index] = array[--size]; // Move last element to removed spot
        return highestPriorityItem;
    }

    T Peek() const {
        int index = highestPriorityIndex();
        if (index == -1) throw runtime_error("Priority queue is empty.");
        return array[index].item;
    }
};

/*
Output: 

Successfully created an empty priority queue for strings.
Insert string "Homework Due Today" to the priority queue with priority value 20.
Insert string "Play Games" to the priority queue with priority value 3.
Insert string "Prepare Dinner" to the priority queue with priority value 30.
Insert string "Exam" to the priority queue with priority value 70.
Insert string "Workout" to the priority queue with priority value 15.
Insert string "Homework Due Today" to the priority queue with priority value 45.
***Error: Trying to insert duplicated item.
Current item with the highest priority: Exam
Removing the highest priority item...
Current item with the highest priority: Prepare Dinner
Change the priority of item "Homework Due Today" to 50.
Current item with the highest priority: Homework Due Today
Removing the two highest priority item...
Current item with the highest priority: Workout
*/