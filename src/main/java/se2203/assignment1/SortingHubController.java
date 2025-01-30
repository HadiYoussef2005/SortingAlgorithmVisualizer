package se2203.assignment1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class SortingHubController implements Initializable {
    @FXML
    private AnchorPane displayBox;
    @FXML
    private Label timeDelayLabel;
    @FXML
    private Slider timeDelaySlider;
    @FXML
    private Label arraySizeLabel;
    @FXML
    private Slider arraySizeSlider;
    @FXML
    private ComboBox<String> algorithmComboBox;
    private int[] intArray;
    SortingStrategy sortingMethod;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        timeDelayLabel.setText("200");
        timeDelaySlider.setValue(200);
        arraySizeLabel.setText("80");
        arraySizeSlider.setValue(80);
        intArray = new int[80];
        sortingMethod = new MergeSort(this, intArray);
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = i + 1;
        }
        sortingMethod.shuffle(intArray);
        updateGraph(intArray);
        ObservableList<String> algorithmList = FXCollections.observableArrayList("Merge Sort", "Quick Sort", "Selection Sort");
        algorithmComboBox.getItems().addAll(algorithmList);
        algorithmComboBox.setValue("Merge Sort");
    }

    public void changeTimeDelay(){
        timeDelayLabel.setText(String.format("%d", (long) timeDelaySlider.getValue()));
    }

    public void changeArraySize(){
        arraySizeLabel.setText(String.format("%d", (int) arraySizeSlider.getValue()));
        int size = (int) arraySizeSlider.getValue();
        intArray = new int[size];

        for (int i = 0; i < size; i++) {
            intArray[i] = i + 1;
        }

        sortingMethod.shuffle(intArray);


        updateGraph(intArray);
    }



    public void updateGraph(int[] arr) {
        displayBox.getChildren().clear();

        double paneHeight = displayBox.getPrefHeight();
        double paneWidth = displayBox.getPrefWidth();
        double barWidth = paneWidth / arr.length;
        double max = arr[0];
        for(int i = 1; i < arr.length; i++){
            if(arr[i] > max){
                max = arr[i];
            }
        }

        for (int i = 0; i < arr.length; i++) {
            double barHeight = (arr[i]/max)*324;
            double xPosition = i * barWidth;
            double yPosition = paneHeight - barHeight;

            Rectangle bar = new Rectangle(barWidth - 2, barHeight);
            bar.setX(xPosition+2);
            bar.setY(yPosition);
            bar.setStyle("-fx-fill: red; -fx-stroke-width: 1;");

            displayBox.getChildren().add(bar);
        }
    }

    public void sortVals(){
        if(isSorted()){
            return;
        }
        Thread thread = new Thread(sortingMethod);
        thread.start();
    }

    public long getTimeDelay() {
        return (long) timeDelaySlider.getValue();
    }
    public int[] getIntArray(){
        return intArray;
    }

    public void shuffleArr(){
        sortingMethod.shuffle(intArray);
        updateGraph(intArray);
    }
    public void reverseArr(){
        sortingMethod.reverse(intArray);
        updateGraph(intArray);
    }
    public void changeSortingMethod(){
        String algo = algorithmComboBox.getValue();
        if(algo.equals("Merge Sort")){
            sortingMethod = new MergeSort(this, intArray);
        } else if (algo.equals("Selection Sort")){
            sortingMethod = new SelectionSort(this, intArray);
        } else {
            sortingMethod = new QuickSort(this, intArray);
        }
    }
    public boolean isSorted(){
        for(int i = 1; i < intArray.length; i++){
            if(intArray[i] < intArray[i-1]){
                return false;
            }
        }
        return true;
    }
}