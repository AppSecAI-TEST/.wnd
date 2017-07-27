package kr.co.windowfun.widget;

import android.net.Uri;

import java.io.File;

/**
 * Created by isyoon on 2017-07-25.
 */

public class _TextUtil implements _TAG {
    public static String getFileName(String filename) {
        return filename.substring(filename.lastIndexOf("/") + 1);
    }

    public static String getFileUrl(String filename) {
        return filename.substring(0, filename.lastIndexOf("/") + 1) + Uri.encode(filename.substring(filename.lastIndexOf("/") + 1)/*, "UTF-8"*/);
    }

    public static String getFilePath(String filename) {
        return root_mp4 + File.separator + filename.substring(filename.lastIndexOf("/") + 1);
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
