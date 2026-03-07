package com.example.java_lab3fx;
import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import javafx.animation.*;
import javafx.css.Size;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.example.works.slideshow.*;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
//import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.TimeUnit;



public class Controller {
    //Image[] images = new Image[8];
    ImageLoader imgLoader = new ImageLoader();

    @FXML
    ImageView imageView = new ImageView();
    @FXML
    TextField indexField;
    @FXML
    Pane pane1;
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
        iterator.loadImages("all");

        if(iterator.items.length==0)
        {
            indexField.setText("Пусто");
        }

        imageView.setImage((Image)iterator.getFirstPic());


       updateIndexField();

       getExif();

    }

    @FXML
    public void loadJPGPhotos()
    {
//        for (int i=0;i<8;i++)
//        {
//            File file = new File("D:\\projects\\Java\\Java_Labs\\lab3FX\\java_lab3fx\\src\\main\\java\\images\\"+(i+1)+".jpg");
//            Image img = imgLoader.loadFromFile(file);
//            images[i] = img;
//        }
        iterator.loadImages("jpg");

        if(iterator.items.length==0)
        {
            indexField.setText("Пусто");
        }

        imageView.setImage((Image)iterator.getFirstPic());

        updateIndexField();

    }

    @FXML
    public void loadPNGPhotos()
    {
//        for (int i=0;i<8;i++)
//        {
//            File file = new File("D:\\projects\\Java\\Java_Labs\\lab3FX\\java_lab3fx\\src\\main\\java\\images\\"+(i+1)+".jpg");
//            Image img = imgLoader.loadFromFile(file);
//            images[i] = img;
//        }
        iterator.loadImages("png");

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
        //imageView.imageProperty().wait(500);
        slideNext((Image)iterator.next());
        //imageView.setImage((Image) iterator.next());
        //imageView.opacityProperty().setValue(100);
        updateIndexField();
        getExif();
    }

    @FXML
    public void prevPic()
    {
        slidePrev((Image)iterator.preview());
        //imageView.setImage((Image)iterator.preview());
        updateIndexField();
        getExif();
    }

    @FXML
    public void firstPic()
    {
        sizeNext((Image)iterator.getFirstPic());
        //imageView.setImage((Image)iterator.getFirstPic());
        updateIndexField();
        getExif();
    }

    @FXML
    public void lastPic()
    {
        sizeNext((Image)iterator.getLastPic());
        //slideNext((Image)iterator.getLastPic());
        //imageView.setImage((Image)iterator.getLastPic());
        updateIndexField();
        getExif();
    }


    @FXML
    public void updateIndexField()
    {
        indexField.setText((iterator.getCurrentIndex()+1)+" из "+iterator.items.length);
    }

    public void slideNext(Image nextImage) {

        ImageView nextView = new ImageView(nextImage);
        nextView.setFitWidth(imageView.getFitWidth());
        nextView.setFitHeight(imageView.getFitHeight());
        nextView.setPreserveRatio(true);


        nextView.setTranslateX(imageView.getFitWidth());
        ((Pane)imageView.getParent()).getChildren().add(nextView);

        double x = imageView.getX();
        TranslateTransition out = new TranslateTransition(Duration.millis(1000), imageView);
        out.setFromX(x+imageView.getFitWidth()/3.6);
        out.setToX(x-1000);


        TranslateTransition in = new TranslateTransition(Duration.millis(1000), nextView);
        in.setFromX(x+1000);
        in.setToX(x-nextView.getFitWidth()/3.7);


        out.setOnFinished(e -> {
            imageView.setImage(nextImage);
            imageView.setTranslateX(0);
            ((Pane)imageView.getParent()).getChildren().remove(nextView);
        });

        out.play();
        in.play();
    }


    public void slidePrev(Image nextImage) {

        ImageView nextView = new ImageView(nextImage);
        nextView.setFitWidth(imageView.getFitWidth());
        nextView.setFitHeight(imageView.getFitHeight());
        nextView.setPreserveRatio(true);


        nextView.setTranslateX(imageView.getFitWidth());
        ((Pane)imageView.getParent()).getChildren().add(nextView);

        double x = imageView.getX();
        TranslateTransition out = new TranslateTransition(Duration.millis(1000), imageView);
        out.setFromX(x+imageView.getFitWidth()/3.6);
        out.setToX(x+1000);


        TranslateTransition in = new TranslateTransition(Duration.millis(1000), nextView);
        in.setFromX(x-1000);
        in.setToX(x-nextView.getFitWidth()/3.7);


        out.setOnFinished(e -> {
            imageView.setImage(nextImage);
            imageView.setTranslateX(0);
            ((Pane)imageView.getParent()).getChildren().remove(nextView);
        });

        out.play();
        in.play();
    }



    public void sizeNext(Image nextImage)
    {
        double width = imageView.getFitWidth();
        double height = imageView.getFitHeight();

        //TranslateTransition decrease = new TranslateTransition(Duration.millis(1000),imageView);

        ScaleTransition decrease = new ScaleTransition(Duration.millis(1000),imageView);
        decrease.setFromX(1.0);
        decrease.setFromY(1.0);
        decrease.setToX(0.0);
        decrease.setToY(0.0);


        decrease.setOnFinished(e -> {
            imageView.setImage(nextImage);
            imageView.setScaleX(0.0);
            imageView.setScaleY(0.0);
        });


        ScaleTransition grow = new ScaleTransition(Duration.millis(1000), imageView);
        grow.setFromX(0.0);
        grow.setFromY(0.0);
        grow.setToX(1.0);
        grow.setToY(1.0);

        SequentialTransition sequentialTransition = new SequentialTransition(decrease, grow);
        sequentialTransition.play();
    }



    boolean auto = false;
    PauseTransition pause;

    @FXML
    public void autoScroll()
    {

        if (pause == null) {
            pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(e -> {
                nextPic();
                pause.playFromStart();
            });
        }


        if (pause.getStatus() == Animation.Status.RUNNING) {
            pause.stop();
        } else {
            pause.play();
        }


    }
    @FXML
    TextField dateField;
    @FXML
    TextField camField;

    @FXML
    public void getExif()
    {
        String date = iterator.metas[iterator.getCurrentIndex()][0];
        String cam = iterator.metas[iterator.getCurrentIndex()][1];

        dateField.setText(date);
        camField.setText(cam);
    }



}
