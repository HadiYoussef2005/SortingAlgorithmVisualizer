package se2203.assignment1;

import javafx.application.Platform;

public class SelectionSort extends SortingStrategy {
    int[] list;
    SortingHubController controller;

    public SelectionSort(SortingHubController controller, int[] list) {
        this.list = list;
        this.controller = controller;
    }

    @Override
    public void sort(int[] numbers) {
        int n = numbers.length;

        for (int i = 0; i < n - 1; i++) {
            int indexOfNextSmallest = i;

            for (int j = i + 1; j < n; j++) {
                if (numbers[j] < numbers[indexOfNextSmallest]) {
                    indexOfNextSmallest = j;
                }
            }

            swap(numbers, i, indexOfNextSmallest);

            int[] updatedArray = numbers.clone();
            Platform.runLater(() -> controller.updateGraph(updatedArray));

            try {
                Thread.sleep(controller.getTimeDelay());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
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
