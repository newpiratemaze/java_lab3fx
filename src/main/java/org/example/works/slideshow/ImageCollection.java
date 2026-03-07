package org.example.works.slideshow;

import java.io.File;
import java.io.FilenameFilter;

public class ImageCollection {
    private File[] files;

    public ImageCollection(File directory, String type) {
        this.files = directory.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                String ln = name.toLowerCase();
                return switch (type.toLowerCase()) {
                    case "all" -> ln.endsWith(".jpg") || ln.endsWith(".jpeg") || ln.endsWith(".png");
                    case "jpg" -> ln.endsWith(".jpg") || ln.endsWith(".jpeg");
                    case "png" -> ln.endsWith(".png");
                    default -> false;
                };
            }
        });

        if (this.files == null) this.files = new File[0];
    }

    public File[] getFiles() {
        return files;
    }

    public int size() {
        return files.length;
    }
}