package sample;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;


import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Controller {

    @FXML
    private Button loadButton;

    @FXML
    private Button processButton;

    @FXML
    private Button saveButton;

    @FXML
    private ComboBox<String> algorithmComboBox;

    @FXML
    private ImageView originalImage;

    @FXML
    private ImageView newImage;

    final FileChooser fileChooser = new FileChooser();//выбор файла
    //private Desktop desktop = Desktop.getDesktop();//объект рабочего стола

    private MyImage image;
    private Image afterImage;

    @FXML
    void loadImage(ActionEvent event) {//загрузка изображения
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPG","*.jpg"));//фильтр для файла
        File file = fileChooser.showOpenDialog(loadButton.getScene().getWindow());//выбор файла
        if(file!=null)
        {
            image = new MyImage(file);
            originalImage.setImage(image.getImage());
            processButton.setDisable(false);
        }
    }

    @FXML
    void process(ActionEvent event) {
        if(algorithmComboBox.getValue().equals("Оператор Собеля")) {
            afterImage = image.operatorSl();
            newImage.setImage(afterImage);
            saveButton.setDisable(false);
        }
        else if (algorithmComboBox.getValue().equals("Оператор Лапласа")) {
            afterImage = image.operatorSl();
            newImage.setImage(afterImage);
            saveButton.setDisable(false);
        }
        else if (algorithmComboBox.getValue().equals("Оператор Юрченко")) {
            afterImage = image.operatorYurchenko();
            newImage.setImage(afterImage);
            saveButton.setDisable(false);
        }

    }


    @FXML
    void save(ActionEvent event) {
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPEG","*.jpeg"));//фильтр для файла
        File file = fileChooser.showSaveDialog(saveButton.getScene().getWindow());
        if(file!=null)
        {
            try {
                //ImageIO.write(SwingFXUtils.fromFXImage(newImage.getImage(), null), "jpeg", file);
                ImageIO.write(image.getNewBufferedImage(),"jpg",file);
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void initialize() {
        algorithmComboBox.getItems().add("Оператор Собеля");
        algorithmComboBox.getItems().add("Оператор Лапласа");
        algorithmComboBox.getItems().add("Оператор Юрченко");
        algorithmComboBox.getSelectionModel().selectFirst();
    }
}
