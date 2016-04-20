package nnero.ndownload.bean;


/**
 * **********************************************
 * <p>
 * Author NNERO
 * <p>
 * Time : 16/4/20 上午10:35
 * <p>
 * Function: package data source
 * <p>
 * ************************************************
 */
public class DataSource {

    public String url;
    public String destFile;

    public DataSource(String url,String destFile){
        this.url = url;
        this.destFile = destFile;
    }
}
