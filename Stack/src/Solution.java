// 使用java提供的Stack
//import java.util.Stack;

// 使用自己定义的栈
class Solution {
    public boolean isValid(String s) {
        // 填写相应的逻辑
        // 声明栈
        ArrayStack<Character> stack = new ArrayStack<>();
        // 遍历字符串
        for (int i = 0; i < s.length(); i++) {
            // 每次都需要看s字符串中第i个字符是什么样子的
            // 将i独立存成字符c
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                // 如果c是左括号，都将它推入栈
                stack.push(c);
            }
            // 否则 else考查的是右括号 需要看栈顶的括号是否和右括号匹配
            else {
                if (stack.isEmpty()) {
                    // 栈是空的话 显然不成功
                    return false;
                }
                // 否则的话看看当前栈顶的字符 topChar
                char topChar = stack.pop();
                // 如果当前的括号是")",栈顶不是左侧小括号"(",则匹配失败
                if (c == ')' && topChar != '(') {
                    return false;
                }
                // 如果当前的括号是"]",栈顶不是左侧中括号"[",则匹配失败
                if (c == ']' && topChar != '[') {
                    return false;
                }
                // 如果当前的括号是"}",栈顶不是左侧大括号"{",则匹配失败
                if (c == '}' && topChar != '{') {
                    return false;
                }
            }
        }
        // 如果此时栈里还有元素，说明不匹配，只有当栈为空的时候整个字符串匹配才是成功的
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        // 返回值是boolean
        System.out.println((new Solution()).isValid("()[]{}"));
        System.out.println((new Solution()).isValid("(]{}"));
        System.out.println((new Solution()).isValid("({})"));
    }
}