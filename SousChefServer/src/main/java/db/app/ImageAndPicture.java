package db.app;

import java.sql.Blob;

public interface ImageAndPicture {
    Blob getPicture();
    void setPicture(Blob picture);
    String getImage();
    void setImage(String image);
}
