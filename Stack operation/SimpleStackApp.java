import java.util.Stack;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SimpleStackApp extends Application {
    private Stack<String> stack = new Stack<>();
    private ListView<String> listView;
    private TextField inputField;
    private Label outputLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Initialize UI components
        listView = new ListView<>();
        inputField = new TextField();
        outputLabel = new Label();
        ImageView imageView = new ImageView("images\\images.png");

        // Buttons
        Button pushButton = createButton("Push", this::handlePush);
        Button popButton = createButton("Pop", this::handlePop);
        Button peekButton = createButton("Peek", this::handlePeek);
        Button searchButton = createButton("Search", this::handleSearch);

        // Layout
        VBox buttonsBox = new VBox(10, pushButton, popButton, peekButton, searchButton);
        HBox inputBox = new HBox(10, inputField, outputLabel);
        VBox mainBox = new VBox(20, imageView, buttonsBox, inputBox, listView);
        mainBox.setPadding(new Insets(20));
        mainBox.setAlignment(Pos.CENTER);

        // Set up scene and stage
        Scene scene = new Scene(mainBox, 400, 500);
        stage.setScene(scene);
        stage.setTitle("Simple Stack App");
        stage.show();
    }

    private Button createButton(String text, javafx.event.EventHandler<ActionEvent> handler) {
        Button button = new Button(text);
        button.setOnAction(handler);
        button.setPrefWidth(100);
        return button;
    }

    private void handlePush(ActionEvent event) {
        String item = inputField.getText();
        if (!item.isEmpty()) {
            stack.push(item);
            listView.getItems().add(0, item);
            inputField.clear();
            outputLabel.setText("Item pushed: " + item);
        } else {
            outputLabel.setText("Please enter an item to push.");
        }
    }

    private void handlePop(ActionEvent event) {
        if (!stack.isEmpty()) {
            String item = stack.pop();
            listView.getItems().remove(0);
            outputLabel.setText("Item popped: " + item);
        } else {
            outputLabel.setText("Stack is empty.");
        }
    }

    private void handlePeek(ActionEvent event) {
        if (!stack.isEmpty()) {
            String item = stack.peek();
            outputLabel.setText("Top item: " + item);
        } else {
            outputLabel.setText("Stack is empty.");
        }
    }

    private void handleSearch(ActionEvent event) {
        String item = inputField.getText();
        if (!item.isEmpty()) {
            int position = stack.search(item);
            if (position != -1) {
                outputLabel.setText("Item found at position: " + position);
            } else {
                outputLabel.setText("Item not found.");
            }
            inputField.clear();
        } else {
            outputLabel.setText("Please enter an item to search.");
        }
    }
}
