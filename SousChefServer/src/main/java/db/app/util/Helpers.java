package db.app.util;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

/**
 * A class for storing methods used by multiple classes
 */
public class Helpers {
    public static boolean containsIgnoreCase(String src, String what) {
        final int length = what.length();
        if (length == 0)
            return true; // Empty string is contained

        final char firstLo = Character.toLowerCase(what.charAt(0));
        final char firstUp = Character.toUpperCase(what.charAt(0));

        for (int i = src.length() - length; i >= 0; i--) {
            // Quick check before calling the more expensive regionMatches() method:
            final char ch = src.charAt(i);
            if (ch != firstLo && ch != firstUp)
                continue;

            if (src.regionMatches(true, i, what, 0, length))
                return true;
        }

        return false;
    }

    /**
     * Converts the Blob from the SQL db to a Base64 encoded string for serialization
     * @param object The object to prepare
     */
    public static void prepForSerialization(ImageAndPicture object) {
        try {
            if(object.getPicture() == null || object.getPicture().length() < 4) {
                object.setImage(null);
                return;
            }
            int len;
            len = (int) object.getPicture().length();
            byte[] bytes = object.getPicture().getBytes(1, len);
            object.setImage(Base64.getEncoder().encodeToString(bytes));	//for sending over JSON
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Converts what is currently a String encoded in Base64 to a Blob for storage in SQL db
     * @param object The object to prepare
     */
    public static void convertStringToBlob(ImageAndPicture object) {
        if(object.getImage() == null)
            return;
        try {
            byte[] bytes = Base64.getDecoder().decode(object.getImage());
            Blob b = new SerialBlob(bytes);
            object.setPicture(b);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
