import java.util.Random;

public class MaxHeapTest {
    public static void main(String[] args) {
        // 测试堆中100万可数据，随机向堆中添加
        int n = 1000000;
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));
        }

        // 每一次取出最大值，数组其实是一个降序排序的数组
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = maxHeap.extractMax();
        }

        // 对数组进行验证，如果不是降序，则堆实现存在问题抛出异常
        for (int i = 1; i < n; i++) {
            if (arr[i - 1] < arr[i])
                throw new IllegalArgumentException("Error");
        }
        System.out.println("Test MaxHeap completed.");
    }

}