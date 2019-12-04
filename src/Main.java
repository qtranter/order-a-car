<<<<<<< Updated upstream
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Main extends Application {

    @Override
     public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        primaryStage.setTitle("OAC");
        primaryStage.setScene(new Scene(root, 450, 525));
        primaryStage.show();
    }


    public static void main(String[] args) throws SQLException {
        launch(args);
    }
}
=======
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("test.fxml"));
        Parent root = loader.load();
        loader.getController();
        primaryStage.getIcons().add(new Image("https://i.imgur.com/HRn7QCk.png"));
        primaryStage.setScene(new Scene(root, 450, 525));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
>>>>>>> Stashed changes
