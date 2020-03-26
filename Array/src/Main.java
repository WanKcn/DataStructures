public class Main {

    public static void main(String[] args) {
        Array arr = new Array(20);
        // 往数字中添加10个元素先
        for (int i = 0; i < 10; i++) {
            arr.addLast(i);
        }
        System.out.println(arr);

        // 在索引1的位置插入元素23
        arr.add(1, 23);
        System.out.println(arr);

        // 在整个数组的最前面添加一个元素-1
        arr.addFirst(-1);
        System.out.println(arr);

        // 查询数组中的索引为3的元素
        System.out.println(arr.getData(3));

        // 将索引为2的元素替换为55
        arr.setData(2, 55);
        System.out.println(arr);
    }
}