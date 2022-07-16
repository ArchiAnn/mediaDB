import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Persistence extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("samplePersistence.fxml"));
        primaryStage.setTitle("App");
        primaryStage.setScene(new Scene(root, 500, 450));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}