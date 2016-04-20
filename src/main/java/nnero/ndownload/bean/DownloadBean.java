package nnero.ndownload.bean;

import java.io.File;

/**
 * **********************************************
 * <p>
 * Author NNERO
 * <p>
 * Time : 16/4/20 上午10:10
 * <p>
 * Function: package the download info.
 * <p>
 * ************************************************
 */
public class DownloadBean {

    public static final int FINISH = 1;
    public static final int ERROR_HTTP_CODE =2;
    public static final int ERROR_DOWN = 3;

    public String url;
    public String fileName;
    public String fullPath;
    public int status;

    public DownloadBean(String url,File destFile,int status){
        this.url = url;
        this.fileName = destFile.getName();
        this.fullPath = destFile.getAbsolutePath();
        this.status = status;
    }
}
