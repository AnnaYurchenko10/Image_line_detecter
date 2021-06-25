package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;


public class Controller {

    @FXML
    private Button loadButton;

    final FileChooser fileChooser = new FileChooser();//выбор файла
    private Desktop desktop = Desktop.getDesktop();//объект рабочего стола

    @FXML
    void loadImage(ActionEvent event) {//загрузка изображения
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPG","*.jpg"));//фильтр для файла
        File file = fileChooser.showOpenDialog(loadButton.getScene().getWindow());//выбор файла
        if(file!=null)
        {
            openFile(file);
        }
    }

    private void openFile(File file) {
        try {
            desktop.open(file);
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {

    }
}
