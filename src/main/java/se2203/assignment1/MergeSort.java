package se2203.assignment1;

import javafx.application.Platform;

public class MergeSort extends SortingStrategy {
    int[] list;
    SortingHubController controller;

    public MergeSort(SortingHubController controller, int[] list) {
        this.list = list;
        this.controller = controller;
    }

    @Override
    public void sort(int[] number) {
        mergeSort(number, 0, number.length - 1);
    }

    private void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);

            merge(array, left, mid, right);
        }
    }

    private void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] L = new int[n1];
        int[] R = new int[n2];

        System.arraycopy(array, left, L, 0, n1);
        System.arraycopy(array, mid + 1, R, 0, n2);

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                array[k] = L[i++];
            } else {
                array[k] = R[j++];
            }
            k++;
            Platform.runLater(() -> controller.updateGraph(list));

            try {
                Thread.sleep(controller.getTimeDelay()/(n1+n2));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        while (i < n1) {
            array[k++] = L[i++];
            Platform.runLater(() -> controller.updateGraph(list));

            try {
                Thread.sleep(controller.getTimeDelay()/(n1+n2));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        while (j < n2) {
            array[k++] = R[j++];
            Platform.runLater(() -> controller.updateGraph(list));

            try {
                Thread.sleep(controller.getTimeDelay()/(n1+n2));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }

    @Override
    public void run() {
        this.list = controller.getIntArray();

        Platform.runLater(() -> controller.updateGraph(list));

        sort(list);
    }

}
