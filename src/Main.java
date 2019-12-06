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
        loader.setLocation(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        loader.getController();
        primaryStage.getIcons().add(new Image("https://i.imgur.com/hC8D0oo.png"));
        primaryStage.setTitle("OAK");
        primaryStage.setScene(new Scene(root, 450, 525));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

}