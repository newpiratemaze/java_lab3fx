package org.example.works.slideshow;

import java.io.File;
import java.io.FilenameFilter;

public class ImageCollection {
    private File[] files;

    public ImageCollection(File directory) {
        this.files = directory.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                String lowercaseName = name.toLowerCase();
                return lowercaseName.endsWith(".jpg") ||
                        lowercaseName.endsWith(".png") ||
                        lowercaseName.endsWith(".jpeg");
            }
        });


        if (this.files == null) {
            this.files = new File[0];
        }
    }

    public File[] getFiles() {
        return files;
    }
}

//
//    public Iterator getIterator() {
//        return new ImageFileIterator();
//    }
//
//    // Метод для получения файла по индексу (для навигации)
//    public File getFile(int index) {
//
//    }
//
//    public int size() {
//        return files.length;
//    }
//
//    // Внутренний класс итератора
//    private class ImageFileIterator implements Iterator {
//        private int currentIndex = 0;
//
//        @Override
//        public boolean hasNext() {
//
//        }
//
//        @Override
//        public Object next() {
//             // Возвращать объект File
//        }
//
//        @Override
//        public Object preview() {
//            // Возвращать объект File
//        }
//
//        // Дополнительные методы для навигации
//        public boolean haspreview() {
//
//        }
//
//
//        public void reset() {
//
//        }
//
//        public int getCurrentIndex() {
//
//        }
//    }
//}
//
