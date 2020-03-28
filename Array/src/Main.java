public class Main {

    public static void main(String[] args) {
        Genericity<Integer> arr = new Genericity<>(20);
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

        // 执行到此处时，输出的结果为  [-1, 0, 55, 1, 2, 3, 4, 5, 6, 7, 8, 9]
        // 尝试将索引为3的元素删除即删除了1
        arr.remove(3);
        System.out.println(arr);

        // 删除元素4
        arr.removeElement(4);
        System.out.println(arr);

        // 删除第一个元素
        arr.removeFirst();
        System.out.println(arr);

        // 删除最后一个元素
        arr.removeLast();
        System.out.println(arr);

    }
}