public class Main {
    public static void main(String[] args) {
        /* 数组队列测试
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        // 入列测试
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);

            // 往队列中每插入三个元素，就取出一个元素
            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
        */

        LoopQueue<Integer> queue = new LoopQueue<>();
        // 入列测试
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);

            // 往队列中每插入三个元素，就取出一个元素
            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
