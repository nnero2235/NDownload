package nnero.ndownload;

import nnero.ndownload.bean.DataSource;
import nnero.ndownload.callback.Callback;
import nnero.ndownload.provider.DataSourceProvider;
import nnero.ndownload.task.DownloadTask;
import nnero.ndownload.thread.BlockThreadPool;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * **********************************************
 * <p>
 * Author NNERO
 * <p>
 * Time : 16/4/20 上午10:01
 * <p>
 * Function: the downloadtask center manager
 *          shutdown is controlled by DataSourceProvider
 *          @since 0.1.0
 * <p>
 * ************************************************
 */
public class DownloadEngine<T extends DownloadTask> extends Thread {

    public static final int MODE_FETCH = 1;
    public static final int MODE_SET = 2;

    private Class<T> mDownloadTaskClazz;
    private DataSourceProvider mProvider;
    private BlockThreadPool mThreadPool;
    private Callback mCallback;

    private int mMode;

    private DownloadEngine(Class<T> mDownloadTaskClazz,DataSourceProvider provider){
        this.mDownloadTaskClazz = mDownloadTaskClazz;
        this.mProvider = provider;
    }

    /**
     * create engine by downloadtask and sourceprovider.
     * @since 0.1.0
     * @param downloadTaskClazz
     * @param provider
     * @return
     */
    public static DownloadEngine create(Class<?> downloadTaskClazz,DataSourceProvider provider){
        if(downloadTaskClazz == null){
            throw new RuntimeException("user must implement his own download task!");
        }

        if(provider == null){
            throw new RuntimeException("user must implement his own provider!");
        }
        return new DownloadEngine(downloadTaskClazz,provider);
    }

    /**
     * set thead numbers for downloadtask.
     * @param num
     * @return
     */
    public DownloadEngine thread(int num){
        if(num > 1){
            mThreadPool = new BlockThreadPool(num);
        }
        return this;
    }

    /**
     * set provider mode
     * @param mode
     * @return
     */
    public DownloadEngine mode(int mode){
        this.mMode = mode;
        return this;
    }

    /**
     * set callback
     * @param callback
     * @return
     */
    public DownloadEngine callback(Callback callback){
        this.mCallback = callback;
        return this;
    }

    /**
     * start engine
     *
     */
    @Override
    public void run(){
        switch (mMode){
        case MODE_SET:
            modeSet();
            break;
        case MODE_FETCH:
            modeFetch();
            break;
        }
        System.out.println("Engine shutDown!");
    }

    private void modeSet(){
        while (!mProvider.isShutdown()){
            DataSource dataSource = mProvider.get();
            if(dataSource != null){
                if(mThreadPool == null){ //single thread
                    T downloadTask = newDownLoadTask(dataSource.url,dataSource.destFile);
                    if(downloadTask != null) {
                        downloadTask.run();
                    }else{
                        System.out.println("new DownloadTask is null");
                    }
                } else { //multi thread
                    T downloadTask = newDownLoadTask(dataSource.url,dataSource.destFile);
                    if(downloadTask != null) {
                        mThreadPool.execute(downloadTask);
                    } else {
                        System.out.println("new DownloadTask is null");
                    }
                }
            }
        }
    }
    //reflect newInstance of downloadTask.
    private T newDownLoadTask(String url,String destFile){
        try {
            Constructor<T> con = mDownloadTaskClazz.getConstructor(
                    String.class,String.class, Callback.class);
            T downloadTask = con.newInstance(url,destFile,mCallback);
            return downloadTask;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void modeFetch(){

    }
}
