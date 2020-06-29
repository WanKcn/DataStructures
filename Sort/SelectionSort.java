public class SelectionSort {

    // 算法类不允许产生任何实例
    private SelectionSort() {
    }

    public static void sort(int arr[]) {

        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex])
                    minIndex = j;
            }
            swap(arr, i, minIndex);

        }
    }

    // 交换数组的两个元素下标位置，必须通过引用来交换
    private static void swap(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int arr[] = {3, 5, 1, 4, 2, 9, 0, 8, 7};
        sort(arr);
        for (int num : arr)
            System.out.print(num + ",");
    }
}
