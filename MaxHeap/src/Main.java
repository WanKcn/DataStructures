import java.sql.SQLOutput;
import java.util.Random;

public class Main {
    // 传入一个数组，是否使用heapify的方式进行堆整理
    private static double testHeap(Integer[] testData, boolean isHeapify) {
        // 记录开始时间
        long startTime = System.nanoTime();

        // 创建最大堆
        MaxHeap<Integer> maxHeap;
        if (isHeapify)
            maxHeap = new MaxHeap<>(testData);
        else {
            maxHeap = new MaxHeap<>();
            for (int n : testData)
                maxHeap.add(n);
        }

        // 创建好堆之后验证以下堆的正确性
        // 每一次取出最大值，数组其实是一个降序排序的数组
        int[] arr = new int[testData.length];
        for (int i = 0; i < testData.length; i++) {
            arr[i] = maxHeap.extractMax();
            // 此时，数组应该是一个降序数组
        }

        // 对数组进行验证，如果不是降序，则堆实现存在问题抛出异常
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] < arr[i])
                throw new IllegalArgumentException("Error");
        }
        System.out.println("Test MaxHeap completed.");
        // 记录结束时间
        long endtTime = System.nanoTime();

        return (endtTime - startTime) / 1000000000.0;

    }

    public static void main(String[] args) {
        int n = 1000000;
        // 随机生成测试数组
        Random random = new Random();
        Integer[] testData = new Integer[n];
        for (int i = 0; i < n; i++)
            testData[i] = random.nextInt(Integer.MAX_VALUE);

        // 调用测试
        double time1 = testHeap(testData, false);
        System.out.println("Without heapify:" + time1 + "s");
        double time2 = testHeap(testData, true);
        System.out.println("With heapify:" + time2 + "s");
    }

}
