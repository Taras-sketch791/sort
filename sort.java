public class sort {
    public static void main(String[] args) {
        int[] array = new int[] {64, 42, 73, 41, 31, 53, 16, 24, 57, 42, 74, 55, 36};

        bubbleSort(array); // Вызов метода сортировки пузырьком
        printArray(array); // Вывод отсортированного массива
    }
    private static void bubbleSort(int[] array) {
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int i = 1; i < array.length; i++) {
                if (array[i] < array[i - 1]) {
                    // Обмен элементов
                    int temp = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = temp;
                    isSorted = false; // Устанавливаем флаг в false, если была сделана перестановка
                }
            }
        }
    }

    // Метод для вывода массива
    private static void printArray(int[] array) {
        for (int value : array) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}