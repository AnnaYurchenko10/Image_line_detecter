package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

public class MyImage {
    private BufferedImage bufferedImage;
    private BufferedImage newBufferedImage;
    private Image image;

    private static int DIFFERENCE = 15;

    public MyImage(File file){//чтение изобрадения из файла
        try {
            image  = new Image(new FileInputStream(file));//инициализация объекта с помощью файлового потока
            //afterImage = new Image(new FileInputStream(file));
            bufferedImage = ImageIO.read(file);//обрабатываемое изображение
            //получаемое изображение
            newBufferedImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(), bufferedImage.getType());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public Image getImage() {
        return image;
    }

    public Image operatorSl()
    {
        int[][] matrix = new int[3][3];//матрица-маска

        //алгоритм получения оттенков цвета
        for(int y = 1; y < bufferedImage.getHeight() - 1; y++) {
            for (int x = 1; x < bufferedImage.getWidth() - 1; x++) {
                matrix[0][0] = new Color(bufferedImage.getRGB(x - 1, y - 1)).getGreen();
                matrix[0][1] = new Color(bufferedImage.getRGB(x - 1, y)).getGreen();
                matrix[0][2] = new Color(bufferedImage.getRGB(x - 1, y + 1)).getGreen();
                matrix[1][0] = new Color(bufferedImage.getRGB(x, y - 1)).getGreen();
                matrix[1][1] = new Color(bufferedImage.getRGB(x, y)).getGreen();
                matrix[1][2] = new Color(bufferedImage.getRGB(x, y + 1)).getGreen();
                matrix[2][0] = new Color(bufferedImage.getRGB(x + 1, y - 1)).getGreen();
                matrix[2][1] = new Color(bufferedImage.getRGB(x + 1, y)).getGreen();
                matrix[2][2] = new Color(bufferedImage.getRGB(x + 1, y + 1)).getGreen();

                int border = (int) convolution(matrix);
                newBufferedImage.setRGB(x, y, ~(border | border << 8 | border << 16));
            }
        }
        //приведение обработанного изображения к классу Image для вывода в оконном интерфейсе
        return SwingFXUtils.toFXImage(newBufferedImage, null );
    }

    public double convolution(int[][] matrix) {
        int gx = ((matrix[2][0] + 2 * matrix[2][1] + matrix[2][2]) - (matrix[0][0] + 2 * matrix[0][1] + matrix[0][2]));
        int gy = ((matrix[0][2] + 2 * matrix[1][2] + matrix[2][2]) - (matrix[0][0] + 2 * matrix[1][0] + matrix[2][0]));
        return Math.sqrt(Math.pow(gy, 2) + Math.pow(gx, 2));
    }

    public Image operatorYurchenko()
    {
        System.out.println(bufferedImage.getHeight());
        System.out.println(bufferedImage.getWidth());
        for(int y = 1; y < bufferedImage.getHeight()-3; y++) {
            for (int x = 1; x < bufferedImage.getWidth()-3; x++) {
                if (Math.abs(new Color(bufferedImage.getRGB(x,y)).getRed() - new Color(bufferedImage.getRGB(x+3,y+3)).getRed())>DIFFERENCE ||
                        Math.abs(new Color(bufferedImage.getRGB(x,y)).getGreen() - new Color(bufferedImage.getRGB(x+3,y+3)).getGreen())>DIFFERENCE ||
                        Math.abs(new Color(bufferedImage.getRGB(x,y)).getBlue() - new Color(bufferedImage.getRGB(x+3,y+3)).getBlue())>DIFFERENCE) {
                    newBufferedImage.setRGB(x,y,Color.BLACK.getRGB());
                }
                else{
                    newBufferedImage.setRGB(x,y, Color.WHITE.getRGB());
                }
            }
        }

        return SwingFXUtils.toFXImage(newBufferedImage, null);
    }

    public BufferedImage getNewBufferedImage() {
        return newBufferedImage;
    }
}
