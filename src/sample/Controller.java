package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;


import javax.imageio.ImageIO;
import java.io.File;
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

    private MyImage image;//объект класса MyImage
    private Image afterImage;//изображение после обработки
    private String format;//формат файла

    @FXML
    void loadImage(ActionEvent event) {//загрузка изображения
        File file = fileChooser.showOpenDialog(loadButton.getScene().getWindow());//выбор файла
        int i = file.getName().lastIndexOf('.');//индекс точки
        if (i >= 0) {
            format = file.getName().substring(i+1);//подстрока после точки (формат)
        }
        if(file!=null)
        {
            image = new MyImage(file);//инициализация объекта
            originalImage.setImage(image.getImage());//вывод изображения в окне
            processButton.setDisable(false);//активация кнопки "Обработать"
        }
    }

    @FXML
    void process(ActionEvent event) {//обработка изображения
        long start = System.currentTimeMillis();//начало работы таймера
        if(algorithmComboBox.getValue().equals("Оператор Собеля")) {
            afterImage = image.operatorSl();//обработанное изображение
            newImage.setImage(afterImage);//вывод обработанного изображения в окно
            saveButton.setDisable(false);//активация кнопки "Сохранить"
        }
        else if (algorithmComboBox.getValue().equals("Оператор Робертса")) {
            afterImage = image.operatorRob();//обработанное изображение
            newImage.setImage(afterImage);//вывод обработанного изображения в окно
            saveButton.setDisable(false);//активация кнопки "Сохранить"
        }
        else if (algorithmComboBox.getValue().equals("Оператор Юрченко")) {
            afterImage = image.operatorYurchenko();//обработанное изображение
            newImage.setImage(afterImage);//вывод обработанного изображения в окно
            saveButton.setDisable(false);//активация кнопки "Сохранить"
        }
        long finish = System.currentTimeMillis();//окончание работы таймера
        long res = finish - start;//затраченное время
        System.out.println("Прошло времени, мс: " + res);//вывод результата

    }


    @FXML
    void save(ActionEvent event) {//сохранение изображения
        File file = fileChooser.showSaveDialog(saveButton.getScene().getWindow());//открытие окна для выбора папки сохранения
        if(file!=null)
        {
            try {
                ImageIO.write(image.getNewBufferedImage(),format,file);//запись изображения в файл
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void initialize() {
        //фильтр для файла
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("JPG", "*.jpg"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG",  "*.png"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("BMP", "*.bmp"));
        //инициализация comboBox
        algorithmComboBox.getItems().add("Оператор Собеля");
        algorithmComboBox.getItems().add("Оператор Робертса");
        algorithmComboBox.getItems().add("Оператор Юрченко");
        algorithmComboBox.getSelectionModel().selectFirst();//установка первого текущего элемента
    }
}
