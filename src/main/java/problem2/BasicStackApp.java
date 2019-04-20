package problem2;

public class BasicStackApp {

    public static void main(String[] args) {

        BasicStack stack = new BasicStack(50);

        stack.push(15);
        System.out.println("Pushed 15");

        stack.push(10);
        System.out.println("Pushed 10");

        stack.push(12);
        System.out.println("Pushed 12");

        stack.push(11);
        System.out.println("Pushed 11");

        stack.push(7);
        System.out.println("Pushed 7");

        System.out.println(String.format("Min element is %d", stack.min()));

        stack.push(2);
        System.out.println("Pushed 2");

        stack.push(5);
        System.out.println("Pushed 5");

        stack.push(1);
        System.out.println("Pushed 1");

        System.out.println(String.format("Min element is %d", stack.min()));

        System.out.println(String.format("Popped element is %d", stack.pop()));
        System.out.println(String.format("Popped element is %d", stack.pop()));
        System.out.println(String.format("Popped element is %d", stack.pop()));
        System.out.println(String.format("Popped element is %d", stack.pop()));

        System.out.println(String.format("Min element is %d", stack.min()));

        System.out.println(String.format("Popped element is %d", stack.pop()));

        System.out.println(String.format("Min element is %d", stack.min()));

        System.out.println(String.format("Popped element is %d", stack.pop()));
    }
}
