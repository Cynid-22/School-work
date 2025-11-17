public class DemoRunner {
    public static void main(String[] args) {
        System.out.println("Testing Integer Stack (Size 5)");
        StackInteger intStack = new StackInteger();
        intStack.push(10);
        intStack.push(20);
        intStack.push(30);
        intStack.push(40);
        intStack.push(50);
        intStack.push(60);
        intStack.pop();
        intStack.pop();
        intStack.pop();
        intStack.pop();
        intStack.pop();
        intStack.pop();

        System.out.println("\nTesting String Stack (Size 5)");
        StackString stringStack = new StackString();
        stringStack.push("Hello");
        stringStack.push("World");
        stringStack.push("CMPSC");
        stringStack.push("221");
        stringStack.push("Stack");
        stringStack.push("Overflow");
        stringStack.pop();
        stringStack.pop();
        stringStack.pop();
        stringStack.pop();
        stringStack.pop();
        stringStack.pop();
    }
}