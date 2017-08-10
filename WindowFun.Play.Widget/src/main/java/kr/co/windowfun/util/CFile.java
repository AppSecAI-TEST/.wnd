package kr.co.windowfun.util;

import android.text.TextUtils;

import java.io.File;

/**
 * Created by isyuun on 2017-07-25.
 */

public class CFile /*implements _JSON */ {
    private String _CLASSNAME_;
    protected String __CLASSNAME__;

    protected String getMethodName() {
        //Log.wtf(__CLASSNAME__, "[[getMethodName()]][ST]");
        final StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        String reg = "[^A-Za-z0-9.]";
        int i;
        StackTraceElement item = null;
        for (i = 0; i < ste.length; i++) {
            item = ste[i];
            if (item.getMethodName().equalsIgnoreCase("getMethodName")) continue;
            if (item.getMethodName().contains("access$")) continue;
            //Log.v(_CLASSNAME_.replaceAll(reg, ""), item.getClassName().replaceAll(reg, "") + ":" + item.getMethodName() + "(" + item.getFileName() + ":" + item.getLineNumber() + ")");
            if (item.getClassName().replaceAll(reg, "").contains(_CLASSNAME_.replaceAll(reg, ""))) {
                //Log.i(_CLASSNAME_, item.getClassName() + ":" + item.getMethodName() + "(" + item.getFileName() + ":" + item.getLineNumber() + ")");
                break;
            }
        }
        //Log.wtf(__CLASSNAME__, "[[getMethodName()]][ED]");
        return "" + item;
    }

    public CFile() {
        super();
        _CLASSNAME_ = this.getClass().getName();
        __CLASSNAME__ = "[[" + this.getClass().getName() + "]]";
    }

    public String file_name;
    public String file_size;

    public String name;
    public String path;
    public String url;

    public File file;
    public long size = 0L;

    public CFile(String file_name, String file_size) throws Exception {
        this();
        this.file_name = file_name;
        this.file_size = file_size;
        if (TextUtils.isEmpty(file_name) || TextUtils.isEmpty(file_size)) {
            throw new Exception("No File Info" + "[file_name]" + file_name + "[file_size]" + file_size);
        }
        if (!TextUtils.isEmpty(file_name) && !TextUtils.isEmpty(file_size)) {
            this.name = TextUtil.getFileName(file_name);
            this.path = TextUtil.getFilePath(file_name);
            this.url = TextUtil.getFileUrl(file_name);
            this.file = new File(this.path);
            this.size = this.file != null ? this.file.length() : 0L;
            //Log.wtf(__CLASSNAME__, getMethodName() + this.file + "\n[file_size]" + file_size + "\n[uri]" + file_name + "\n[url]" + url + "\n[name]" + name + "\n[path]" + path + "\n[size]" + size);
        }
    }

    public boolean exists() {
        return (this.file != null && this.file.exists() && this.file.isFile());
    }

    public boolean sizes() {
        return (this.size > 0 && this.size == Long.parseLong(this.file_size));
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
