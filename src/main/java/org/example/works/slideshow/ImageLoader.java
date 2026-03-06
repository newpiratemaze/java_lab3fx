package org.example.works.slideshow;

import javafx.scene.image.Image;
//import java.awt.Image;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageLoader {
    // Загрузка изображения из файла
    public Image loadFromFile(File file) {
        return new Image(file.toURI().toString());

    }

    // Загрузка из ресурсов (для тестирования)
//    public static Image loadFromResource(String path) {
//        Image[] img_lst = new Image[](path);
//    }

}
