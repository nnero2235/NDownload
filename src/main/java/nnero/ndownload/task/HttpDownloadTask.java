package nnero.ndownload.task;

import nnero.ndownload.bean.DownloadBean;
import nnero.ndownload.callback.Callback;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

import java.io.File;
import java.io.IOException;
import java.net.*;

/**
 * **********************************************
 * <p>
 * Author NNERO
 * <p>
 * Time : 16/4/20 上午11:14
 * <p>
 * Function: http download task
 * <p>
 * ************************************************
 */
public class HttpDownloadTask extends DownloadTask{

    public HttpDownloadTask(String url, String destFile, Callback callback) throws IOException {
        super(url, destFile, callback);
    }


    @Override
    public void down(String url, File destFile, Callback callback) {
        HttpURLConnection conn = null;
        Source source = null;
        BufferedSink sink = null;
        try {
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setConnectTimeout((int) mTimeOut);
            conn.setReadTimeout((int) mTimeOut);
            conn.connect(); //may block

            if(conn.getResponseCode() == 200) {
                source = Okio.source(conn.getInputStream());
                sink = Okio.buffer(Okio.sink(destFile));
                sink.writeAll(source);
                if(mCallback != null)
                    mCallback.call(new DownloadBean(url,destFile,DownloadBean.FINISH));
            } else { //may this
                mCallback.call(new DownloadBean(url, destFile, DownloadBean.ERROR_HTTP_CODE));
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
            mCallback.call(new DownloadBean(url, destFile, DownloadBean.ERROR_DOWN));
        } catch (MalformedURLException e) {
            e.printStackTrace();
            mCallback.call(new DownloadBean(url, destFile, DownloadBean.ERROR_DOWN));
        } catch (IOException e) {
            e.printStackTrace();
            mCallback.call(new DownloadBean(url, destFile, DownloadBean.ERROR_DOWN));
        } finally {
            if(source != null){
                try {
                    source.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(sink != null){
                try {
                    sink.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
