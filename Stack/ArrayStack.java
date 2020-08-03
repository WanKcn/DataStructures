/**
 * 实现栈
 */
public class ArrayStack<E> implements Stack<E> {

    Array<E> array;

    // 构造函数，用户可以传来容基
    public ArrayStack(int capacity) {
        // 如果用户在最初的时候就已经知道了创建的栈最多可以承载多少个元素
        // 可以在构造的时候将容基传进去
        array = new Array<>(capacity);
    }

    // 用户不知道创建的栈在最大的情况下要承载多少个元素
    public ArrayStack() {
        array = new Array<>();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    @Override
    public void push(E e) {
        array.addLast(e);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: ");
        res.append('[');
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            // 把元素用逗号隔开
            if (i != array.getSize() - 1)
                res.append(", ");

        }
        res.append("] top");   // 在右侧添加top 表示栈顶
        return res.toString();
    }
}
