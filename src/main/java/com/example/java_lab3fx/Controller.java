package com.example.java_lab3fx;
import javafx.css.Size;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import org.example.works.slideshow.*;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
//import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;



public class Controller {
    //Image[] images = new Image[8];
    ImageLoader imgLoader = new ImageLoader();

    @FXML
    ImageView imageView = new ImageView();
    @FXML
    TextField indexField;
    Iterator iterator = new Iterator();

    @FXML
    public void loadPhotos()
    {
//        for (int i=0;i<8;i++)
//        {
//            File file = new File("D:\\projects\\Java\\Java_Labs\\lab3FX\\java_lab3fx\\src\\main\\java\\images\\"+(i+1)+".jpg");
//            Image img = imgLoader.loadFromFile(file);
//            images[i] = img;
//        }
        iterator.loadImages();

        if(iterator.items.length==0)
        {
            indexField.setText("Пусто");
        }

        imageView.setImage((Image)iterator.getFirstPic());

       updateIndexField();

    }

    @FXML
    public void nextPic()
    {
        imageView.setImage((Image) iterator.next());
        updateIndexField();
    }

    @FXML
    public void prevPic()
    {
        imageView.setImage((Image)iterator.preview());
        updateIndexField();
    }

    @FXML
    public void firstPic()
    {
        imageView.setImage((Image)iterator.getFirstPic());
        updateIndexField();
    }

    @FXML
    public void lastPic()
    {
        imageView.setImage((Image)iterator.getLastPic());
        updateIndexField();
    }


    @FXML
    public void updateIndexField()
    {
        indexField.setText((iterator.getCurrentIndex()+1)+" из "+iterator.items.length);
    }



}
