package nnero.ndownload.task;

import com.google.common.base.Strings;
import nnero.ndownload.callback.Callback;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * **********************************************
 * <p>
 * Author NNERO
 * <p>
 * Time : 16/4/20 上午10:04
 * <p>
 * Function: the interface of the abstract downloader
 * <p>
 * ************************************************
 */
public abstract class DownloadTask implements Runnable{

    protected String mUrl;
    protected File mDestFile;
    protected Callback mCallback;
    protected long mTimeOut;

    public DownloadTask(String url,String destFile,Callback callback) throws IOException {
        if(Strings.isNullOrEmpty(url)){
            throw new RuntimeException("DownloadTask: url is null");
        }
        mUrl = url;
        mDestFile = new File(destFile);
        if(mDestFile.exists()){
            mDestFile.delete();
        }
        mDestFile.createNewFile();
        mCallback = callback;
        mTimeOut = 10 * 1000;//10 second
    }

    public void setTimeOut(long timeOut){
        this.mTimeOut = timeOut;
    }

    @Override
    public void run() {
        down(mUrl,mDestFile,mCallback);
    }

    /**
     * down source from url to destfile
     * @param url
     * @param destFile
     * @param callback is optional
     */
    public abstract void down(String url,File destFile,Callback callback);
}
