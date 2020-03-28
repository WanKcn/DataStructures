public class Main {
    public static void main(String[] args) {

        ArrayStack<Integer> stack = new ArrayStack<>();

        // 入栈测试
        for (int i = 0; i < 5; i++) {
            stack.push(i);
            System.out.println(stack);
        }

        // 进行一次出栈
        stack.pop();
        System.out.println(stack);
    }
}
