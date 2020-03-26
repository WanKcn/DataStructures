public class Array {
    private int[] data;  //只能承载int型数据
    private int size;   //data[]中有多少个有效元素

    // 构造函数，传入数组的容量capacity构造Array
    public Array(int capacity){
        data = new int[capacity];  // capacity数组希望开辟的容量
        size = 0;
    }

    // 无参构造，默认数组容量capacity=10
    public Array(){
        this(10);  // 默认
    }

    // 获取数组中的元素个数
    public int getSize(){
        return size;
    }

    // 获取数组容量
    public int getCapacity(){
        return data.length; // 容量就是数组长度
    }

    // 返回数组是否为空
    public boolean isEmpty(){
        return size == 0;
    }
}
