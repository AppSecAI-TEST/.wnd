package kr.co.windowfun.widget;

import com.bumptech.glide.load.Key;

import java.security.MessageDigest;

/**
 * Created by isyoon on 2017-07-21.
 */
@Deprecated
public class IntegerVersionSignature implements Key {
    private int currentVersion;

    public IntegerVersionSignature(int currentVersion) {
        this.currentVersion = currentVersion;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof IntegerVersionSignature) {
            IntegerVersionSignature other = (IntegerVersionSignature) o;
            return currentVersion == other.currentVersion;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return currentVersion;
    }

    @Override
    public void updateDiskCacheKey(MessageDigest messageDigest) {
    }
    //@Override
    //public void updateDiskCacheKey(MessageDigest md) {
    //    messageDigest.update(ByteBuffer.allocate(Integer.SIZE).putInt(signature).array());
    //}
}