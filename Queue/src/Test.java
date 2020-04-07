import java.util.Random;

public class Test {

    // 测试使用q运行opCount个enqueue和dequeue所需要用到的时间 单位：秒
    private static double testQueue(Queue<Integer> q, int opCount) {
        // 使用nanoTime()计时
        // nanotime()返回的是纳秒，所以使用long型进行存储

        // 记录开始时间
        long startTime = System.nanoTime();

        // 入队操作
        Random random = new Random();
        for (int i = 0; i < opCount; i++) {
            // 生成从0-int的最大值（2147483647）之间随意一个数进行入队操作
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        }
        // 出队操作
        for (int i = 0; i < opCount; i++) {
            q.dequeue();
        }

        // 记录结束时间
        long endTime = System.nanoTime();

        // 返回值是秒
        return (endTime - startTime) / 1000000000.0;
    }


    // 测试两个队列的效率有何不同
    public static void main(String[] args) {
        // 对两种队列都生成实例，入队10万个元素，出队10万个元素
        int opCount = 100000;

        // 计算数组队列耗时
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        double time1 = testQueue(arrayQueue, opCount);
        System.out.println("ArrayQueue time:" + time1 + "s");

        // 计算循环队列耗时
        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        double time2 = testQueue(loopQueue, opCount);
        System.out.println("LoopQueue time:" + time2 + "s");

    }
}