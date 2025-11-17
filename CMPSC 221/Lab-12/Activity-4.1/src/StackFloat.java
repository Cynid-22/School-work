public class StackFloat {
    private int maxSize = 5;
    private float[] stackArray;
    private int top;

    public StackFloat() {
        stackArray = new float[maxSize];
        top = -1;
    }

    public void push(float value) {
        if (top == maxSize - 1) {
            System.out.println("Error: Stack Overflow for " + value + ". Stack is full.");
        } else {
            top++;
            stackArray[top] = value;
            System.out.println("Pushed: " + value);
        }
    }

    public float pop() {
        if (top == -1) {
            System.out.println("Error: Stack Underflow. Stack is empty.");
            return -1.0f;
        } else {
            float value = stackArray[top];
            top--;
            System.out.println("Popped: " + value);
            return value;
        }
    }
}