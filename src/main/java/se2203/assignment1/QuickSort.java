package se2203.assignment1;

import javafx.application.Platform;

public class QuickSort extends SortingStrategy {
    int[] list;
    SortingHubController controller;

    public QuickSort(SortingHubController controller, int[] list) {
        this.list = list;
        this.controller = controller;
    }

    @Override
    public void sort(int[] numbers) {
        quickSort(numbers, 0, numbers.length - 1);
    }

    private void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);

            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    private int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                swap(array, i, j);
                int[] updatedArray = array.clone();
                Platform.runLater(() -> controller.updateGraph(updatedArray));

                try {
                    Thread.sleep(controller.getTimeDelay());
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        swap(array, i + 1, high);




        return i + 1;
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    @Override
    public void run() {
        this.list = controller.getIntArray();

        Platform.runLater(() -> controller.updateGraph(list));

        sort(list);

        Platform.runLater(() -> controller.updateGraph(list));
    }
}
