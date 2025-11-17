public class StackInteger {
    private int maxSize = 5;
    private int[] stackArray;
    private int top;

    public StackInteger() {
        stackArray = new int[maxSize];
        top = -1;
    }

    public void push(int value) {
        if (top == maxSize - 1) {
            System.out.println("Error: Stack Overflow for " + value + ". Stack is full.");
        } else {
            top++;
            stackArray[top] = value;
            System.out.println("Pushed: " + value);
        }
    }

    public int pop() {
        if (top == -1) {
            System.out.println("Error: Stack Underflow. Stack is empty.");
            return -1;
        } else {
            int value = stackArray[top];
            top--;
            System.out.println("Popped: " + value);
            return value;
        }
    }
}