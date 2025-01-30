package se2203.assignment1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class SortHubApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SortHubApplication.class.getResource("SortHub-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Image image = new Image("/WesternLogo.png");
        stage.getIcons().add(image);
        stage.setTitle("Sorting Hub");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}