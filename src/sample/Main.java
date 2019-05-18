package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Random;


public class Main extends Application {
    static int[][] map;
    long score;
    int n = 5;
    final int x = 1080;

    public Main(int n) {
        this.n = n;
    }

    public Main() {
        map = new int[n][n];
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group gameRoot = new Group();
       /* Group startRoot = new Group();
        //Scene scene = new Scene(startRoot , 500 , 600);
        Button startButton = new Button("Start");
        startButton.setFont(Font.font(35));
        startButton.setStyle("-fx-background-color: aqua");
        startButton.setTextFill(Color.DARKRED);
        startButton.relocate(190, 30);
        Button exitButton = new Button("Exit");
        startRoot.getChildren().addAll(startButton, exitButton);
        exitButton.setFont(Font.font(35));
        exitButton.setTextFill(Color.DARKRED);
        exitButton.setStyle("-fx-background-color: greenyellow");
        exitButton.relocate(200, 120);
        exitButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.exit(0);
            }
        });
        startButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // scene = new Scene(gameRoot , 1920 , 1080);
            }
        });*/
        Scene scene = new Scene(gameRoot, 1920, 1080);
        playRound();
        System.out.println("");
        show(gameRoot);
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.setTitle("2048");
        primaryStage.show();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case UP:
                        System.err.println("up");
                        goUp();
                        break;
                    case DOWN:
                        System.err.println("down");
                        goDown();
                        break;
                    case RIGHT:
                        System.err.println("right");
                        goRight();
                        break;
                    case LEFT:
                        System.err.println("left");
                        goLeft();
                        break;
                }
                System.out.println("");
                show(gameRoot);
                try {
                    start(primaryStage);
                } catch (Exception e) {


                }
            }

        });
        //Rectangle rectangle = new Rectangle(1080 , 1080);
        //rectangle.setFill(Color.rgb(182,159,129));
        //gameRoot.getChildren().add(rectangle);
        Button exitButtonGame = new Button("Exit");


    }

    private void goUp() {
        for (int i = map.length - 1; i > 0; i--) {
            for (int j = 0; j < map.length; j++) {
                if (map[i - 1][j] == 0) {
                    swap(i - 1, j, i, j);
                } else if (map[i - 1][j] == map[i][j] && map[i][j] != 0) {
                    merge(i - 1, j, i, j);
                }
            }
        }
    }

    private void goDown() {
        for (int i = 0; i < map.length - 1; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i + 1][j] == 0) {
                    swap(i + 1, j, i, j);
                } else if (map[i + 1][j] == map[i][j] && map[i][j] != 0) {
                    merge(i + 1, j, i, j);
                }
            }
        }
    }

    private void goRight() {
        for (int j = 0; j < map.length - 1; j++) {
            for (int i = 0; i < map.length; i++) {
                if (map[i][j + 1] == 0) {
                    swap(i, j + 1, i, j);
                } else if (map[i][j + 1] == map[i][j] && map[i][j] != 0) {
                    merge(i, j + 1, i, j);
                }
            }
        }
    }

    private void goLeft() {
        for (int j = map.length - 1; j > 0; j--) {
            for (int i = 0; i < map.length; i++) {
                if (map[i][j - 1] == 0) {
                    swap(i, j - 1, i, j);
                } else if (map[i][j - 1] == map[i][j] && map[i][j] != 0) {
                    merge(i, j - 1, i, j);
                }
            }
        }
    }

    private void merge(int x1, int y1, int x2, int y2) {
        map[x1][y1]++;
        map[x2][y2] = 0;
    }

    private void swap(int x1, int y1, int x2, int y2) {
        map[x1][y1] = map[x2][y2];
        map[x2][y2] = 0;
    }

    private void makeMap(int n) {
        map = new int[n][n];
    }

    private void playRound() {
        Random random = new Random();
        while (true) {
            int x = random.nextInt(map.length - 1);
            int y = random.nextInt(map.length - 1);
            if (map[x][y] == 0) {
                map[x][y] = 1 + random.nextInt(1);
                return;
            }
        }
    }

    private void show(Group gameRoot) {
        ArrayList<Label> labels = new ArrayList<>();
        ArrayList<Rectangle> rectangles = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] != 0) {
                    Rectangle rectangle1 = new Rectangle(x / n * j, x / n *i , x / n, x / n);
                    rectangle1.setFill(Color.valueOf("0xeee4da"));
                    rectangles.add(rectangle1);
                    Label label = new Label();
                    labels.add(label);
                    label.setText(((Integer)pow(map[i][j])).toString());
                    label.relocate(x / n * (j+1/2) , x / n * (i+1/2));
                }
                System.out.print(map[i][j]+"-");
            }
            System.out.println("");
        }
        gameRoot.getChildren().addAll(rectangles);
        gameRoot.getChildren().addAll(labels);
    }

    private int pow(int n) {
        int j =1 ;
        for (int i = 0 ; i < n ; i++)
            j *= 2;
        return j;
    }

    public static void main(String[] args) {
        map = new int[5][5];
        launch(args);
    }
}
