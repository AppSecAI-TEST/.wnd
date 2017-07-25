package kr.co.windowfun.widget;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;

/**
 * Created by isyoon on 2017-07-25.
 */

public class CFile implements _TAG {
    private String _CLASSNAME_;
    protected String __CLASSNAME__;

    public CFile() {
        super();
        _CLASSNAME_ = this.getClass().getName();
        __CLASSNAME__ = "[[" + this.getClass().getName() + "]]";
    }

    protected String getMethodName() {
        ////Log.wtf(__CLASSNAME__, "[[getMethodName()]]");
        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        int idx = 0;
        for (int i = 0; i < ste.length; i++) {
            StackTraceElement item = ste[i];
            ////Log.i(_CLASSNAME_, "" + item.getClassName());
            if (item.getClassName().contains(_CLASSNAME_)) {
                idx = i;
                Log.v(__CLASSNAME__, "" + item);
            }
        }
        return ste[idx].toString();
    }

    public String filename;
    public String filesize;

    public String name;
    public String path;
    public String url;

    public File file;
    public long size = 0L;

    public CFile(String filename, String filesize) throws Exception {
        this();
        this.filename = filename;
        this.filesize = filesize;
        if (TextUtils.isEmpty(filename) || TextUtils.isEmpty(filesize)) {
            throw new Exception("No File Info" + "[filename]" + filename + "[filesize]" + filesize);
        }
        if (!TextUtils.isEmpty(filename) && !TextUtils.isEmpty(filesize)) {
            this.name = _TextUtil.getFileName(filename);
            this.path = _TextUtil.getFilePath(filename);
            this.url = _TextUtil.getFileUrl(filename);
            this.file = new File(this.path);
            this.size = this.file != null ? this.file.length() : 0L;
            //Log.wtf(__CLASSNAME__, getMethodName() + this.file + "\n[filesize]" + filesize + "\n[uri]" + filename + "\n[url]" + url + "\n[name]" + name + "\n[path]" + path + "\n[size]" + size);
        }
    }

    public boolean exists() {
        return (this.file != null && this.file.exists() && this.file.isFile());
    }

    public boolean sizes() {
        return (this.size > 0 && this.size == Long.parseLong(this.filesize));
    }

    public boolean equal() {
        try {
            return exists() && sizes();
        } catch (Exception e) {
            //e.printStackTrace();
            return false;
        }
    }
}
