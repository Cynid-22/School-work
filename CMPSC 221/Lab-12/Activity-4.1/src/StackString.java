public class StackString {
    private int maxSize = 5;
    private String[] stackArray;
    private int top;

    public StackString() {
        stackArray = new String[maxSize];
        top = -1;
    }

    public void push(String value) {
        if (top == maxSize - 1) {
            System.out.println("Error: Stack Overflow for '" + value + "'. Stack is full.");
        } else {
            top++;
            stackArray[top] = value;
            System.out.println("Pushed: '" + value + "'");
        }
    }

    public String pop() {
        if (top == -1) {
            System.out.println("Error: Stack Underflow. Stack is empty.");
            return null;
        } else {
            String value = stackArray[top];
            top--;
            System.out.println("Popped: '" + value + "'");
            return value;
        }
    }
}