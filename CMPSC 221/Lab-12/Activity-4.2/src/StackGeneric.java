public class StackGeneric<T> {
    private int maxSize = 5;
    private T[] stackArray;
    private int top;

    @SuppressWarnings("unchecked")
    public StackGeneric() {
        stackArray = (T[]) new Object[maxSize];
        top = -1;
    }

    public void push(T value) {
        if (top == maxSize - 1) {
            System.out.println("Error: Stack Overflow for " + value.toString() + ". Stack is full.");
        } else {
            top++;
            stackArray[top] = value;
            System.out.println("Pushed: " + value.toString());
        }
    }

    public T pop() {
        if (top == -1) {
            System.out.println("Error: Stack Underflow. Stack is empty.");
            return null;
        } else {
            T value = stackArray[top];
            stackArray[top] = null;
            top--;
            System.out.println("Popped: " + value.toString());
            return value;
        }
    }
}