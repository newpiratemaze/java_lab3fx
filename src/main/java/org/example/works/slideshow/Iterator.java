package org.example.works.slideshow;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Metadata;
import com.drew.metadata.exif.ExifIFD0Directory;
import com.drew.metadata.exif.ExifSubIFDDirectory;
import javafx.scene.image.Image;

import java.io.File;

public class Iterator
{
    File dir = new File("D:\\projects\\Java\\Java_Labs\\lab3FX" +
            "\\java_lab3fx\\src\\main\\java\\images");


    public Object[] items;
    public int index = 0;
    ImageLoader imgLoader = new ImageLoader();
    public String[][] metas;

//    public Iterator(Object[] items) {
//        this.items = items;
//    }

    public void loadImages(String type)
    {
        ImageCollection imgCol = new ImageCollection(dir,type);
        File[] files = imgCol.getFiles();
        items = new Object[files.length];
        metas = new String[files.length][2];
        for (int i=0;i< files.length;i++)
        {
            Image img = imgLoader.loadFromFile(files[i]);
            items[i] = img;

            try {
                Metadata metadata = ImageMetadataReader.readMetadata(files[i]);


//                ExifSubIFDDirectory dateDir = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
//                metas[i][0] = ("Дата: " + dateDir.getDate(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL));
//
//
//
//
//
//                ExifIFD0Directory cameraDir = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);
//                metas[i][1]=("Камера: " + cameraDir.getString(ExifIFD0Directory.TAG_MODEL));



                ExifSubIFDDirectory subDir = metadata.getFirstDirectoryOfType(ExifSubIFDDirectory.class);
                ExifIFD0Directory ifd0Dir = metadata.getFirstDirectoryOfType(ExifIFD0Directory.class);


                String dateValue = null;
                if (subDir != null) {
                    dateValue = subDir.getDescription(ExifSubIFDDirectory.TAG_DATETIME_ORIGINAL);
                }
                if (dateValue == null && ifd0Dir != null) {
                    dateValue = ifd0Dir.getDescription(ExifIFD0Directory.TAG_DATETIME);
                }

                if (dateValue != null) {
                    metas[i][0] = "Дата: " + dateValue;
                } else {
                    metas[i][0] = "Дата: нет данных";
                }


                String cameraValue = null;
                if (ifd0Dir != null) {
                    cameraValue = ifd0Dir.getDescription(ExifIFD0Directory.TAG_MODEL);
                }

                if (cameraValue != null) {
                    metas[i][1] = "Камера: " + cameraValue;
                } else {
                    metas[i][1] = "Камера: неизвестна";
                }

            } catch (Exception e) {
                //System.out.println("Метаданные не найдены");
                e.printStackTrace();
                metas[i][0] = "Дата: ошибка";
                metas[i][1] = "Камера: ошибка";
            }
        }

        System.out.println(metas);
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
