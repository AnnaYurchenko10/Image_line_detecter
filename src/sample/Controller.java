package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.awt.*;
import java.io.File;



public class Controller {

    @FXML
    private Button loadButton;

    @FXML
    private Button processButton;

    final FileChooser fileChooser = new FileChooser();//выбор файла
    private Desktop desktop = Desktop.getDesktop();//объект рабочего стола

    @FXML
    private ImageView originalImage;

    @FXML
    private ImageView newImage;

    MyImage image;
    Image afterImage;

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
        newImage.setImage(image.operatorSl());
    }

    @FXML
    void initialize() {

    }
}
