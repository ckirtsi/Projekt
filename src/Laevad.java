import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Laevad extends Application {
    GridPane laud;
    int lauaPikkusLaevades = 9;
    int laevaPikkusPx = 50;

    @Override
    public void start(Stage primaryStage) throws Exception {
        lava();
        laevad();
        reageerimine();
    }

    private void reageerimine() {
        laud.setOnMouseClicked(event -> {
           Rectangle ruut = (Rectangle) event.getTarget();
            String tyyp = ruut.getId();
            if (tyyp.equals("Meri")){
                ruut.setFill(Color.DARKBLUE);
            } else if (tyyp.equals("Laev")){
                ruut.setFill(Color.RED);
                ruut.setId("Põhjas");
            }

            if (laevasidOnAlles()){
                gameover();
            }
        });
    }

    private void gameover() {
        StackPane stack = new StackPane();
        Label go = new Label("Võitsid");
        stack.getChildren().add(go);
        Scene scene = new Scene(stack);
        Stage goStage = new Stage();
        goStage.setScene(scene);
        goStage.show();
    }

    private boolean laevasidOnAlles() {
        for (Node ruut : laud.getChildren()){
            if (ruut.getId().equals("Laev")){
                return true;
            }
        }
        return false;
    }

    private void laevad() {
        for (int i = 0; i < lauaPikkusLaevades; i++) {
            for (int j = 0; j < lauaPikkusLaevades; j++) {
                Rectangle ruut = new Rectangle(laevaPikkusPx, laevaPikkusPx);
                int rand = (int) (Math.random() * 1.3);
                if (rand == 1){
                    ruut.setId("Laev");
                }else {
                    ruut.setId("Meri");
                }
                ruut.setFill(Color.BLUE);
                ruut.setStroke(Color.RED);
                laud.add(ruut, i, j);
            }
        }
    }

    private void lava() {
        laud = new GridPane();
        Scene scene = new Scene(laud, laevaPikkusPx * lauaPikkusLaevades, laevaPikkusPx * lauaPikkusLaevades);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
