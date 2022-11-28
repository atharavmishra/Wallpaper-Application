package android.myapplication.mywallpaper;

import java.util.ArrayList;

public class photosClass {
    private ArrayList<srcClass> photos;

    public photosClass(ArrayList<srcClass> photos) {
        this.photos = photos;
    }

    public void setPhotos(ArrayList<srcClass> photos) {
        this.photos = photos;
    }

    public ArrayList<srcClass> getPhotos() {
        return photos;
    }


}
