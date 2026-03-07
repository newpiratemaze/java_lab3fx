package org.example.works.slideshow;

import javafx.scene.image.Image;

import java.io.File;

public class Iterator
{
    File dir = new File("D:\\projects\\Java\\Java_Labs\\lab3FX" +
            "\\java_lab3fx\\src\\main\\java\\images");


    public Object[] items;
    private int index = 0;
    ImageLoader imgLoader = new ImageLoader();

//    public Iterator(Object[] items) {
//        this.items = items;
//    }

    public void loadImages(String type)
    {
        ImageCollection imgCol = new ImageCollection(dir,type);
        File[] files = imgCol.getFiles();
        items = new Object[files.length];
        for (int i=0;i< files.length;i++)
        {
            Image img = imgLoader.loadFromFile(files[i]);
            items[i] = img;
        }
    }




    public boolean hasNext() {
        if(items!=null && index<items.length)
        {
            return true;
        }
        return false;
    }

    public Object next() {
        if(index==items.length-1)
        {
            index=0;
            return items[index];
        }
        index++;
        return items[index];

    }

    public boolean haspreview() {
        return true;
    }

    public Object preview() {
        if(index>0)
        {
            index--;
            return items[index];
        }
        index=items.length-1;
        return items[index];

    }

    public int getCurrentIndex() {
        return index;
    }

    public Object getLastPic()
    {
        index=items.length-1;
        return items[items.length-1];
    }

    public Object getFirstPic()
    {
        index=0;
        return items[0];
    }





}
