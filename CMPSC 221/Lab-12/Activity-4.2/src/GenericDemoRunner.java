// GenericDemoRunner.java (With Generics)
public class GenericDemoRunner {
    public static void main(String[] args) {

        System.out.println("Testing Generic Integer Stack (Size 5)");
        StackGeneric<Integer> intStack = new StackGeneric<>();
        intStack.push(1);
        intStack.push(2);
        intStack.push(3);
        intStack.push(4);
        intStack.push(5);
        intStack.push(6);
        intStack.pop();
        intStack.pop();

        System.out.println("\nTesting Generic Float Stack (Size 5)");
        StackGeneric<Float> floatStack = new StackGeneric<>();
        floatStack.push(1.1f);
        floatStack.push(2.2f);
        floatStack.push(3.3f);
        floatStack.push(4.4f);
        floatStack.push(5.5f);
        floatStack.push(6.6f);
        floatStack.pop();

        System.out.println("\nTesting Generic String Stack (Size 5)");
        StackGeneric<String> stringStack = new StackGeneric<>();
        stringStack.push("Generics");
        stringStack.push("are");
        stringStack.push("powerful");
        stringStack.push("and");
        stringStack.push("flexible");
        stringStack.push("!");
        stringStack.pop();
        stringStack.pop();
        stringStack.pop();
        stringStack.pop();
        stringStack.pop();
        stringStack.pop();
    }
}