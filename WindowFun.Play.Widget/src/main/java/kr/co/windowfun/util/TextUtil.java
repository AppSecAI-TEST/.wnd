package kr.co.windowfun.util;

import android.net.Uri;

import java.io.File;

import kr.co.windowfun._DEF;

/**
 * Created by isyoon on 2017-07-25.
 */

public class TextUtil implements _DEF {
    public static String getFileName(String filename) {
        try {
            return filename.substring(filename.lastIndexOf("/") + 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getFileUrl(String filename) {
        try {
            return filename.substring(0, filename.lastIndexOf("/") + 1) + Uri.encode(filename.substring(filename.lastIndexOf("/") + 1)/*, "UTF-8"*/);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getFilePath(String filename) {
        try {
            return root_mp4 + File.separator + filename.substring(filename.lastIndexOf("/") + 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    ////출처: http://ccdev.tistory.com/23 [초보코딩왕의 Power Dev.]
    //public String getPathFromUri(Uri uri){
    //    Cursor cursor = getContentResolver().query(uri, null, null, null, null );
    //    cursor.moveToNext();
    //    String path = cursor.getString( cursor.getColumnIndex( "_data" ) );
    //    c.close();
    //    return path;
    //}

    ////출처:http://ccdev.tistory.com/23 [초보코딩왕의 Power Dev.]
    //public Uri getUriFromPath(String path) {
    //    String fileName = "file:///sdcard/DCIM/Camera/2013_07_07_12345.jpg";
    //    Uri fileUri = Uri.parse(fileName);
    //    String filePath = fileUri.getPath();
    //    Cursor c = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
    //            null, "_data = '" + filePath + "'", null, null);
    //    cursor.moveToNext()
    //    int id = cursor.getInt(cursor.getColumnIndex("_id"));
    //    Uri uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);
    //
    //    return uri;
    //}
}