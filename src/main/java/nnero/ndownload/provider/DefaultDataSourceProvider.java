package nnero.ndownload.provider;

import nnero.ndownload.bean.DataSource;

import java.util.LinkedList;
import java.util.List;

/**
 * **********************************************
 * <p>
 * Author NNERO
 * <p>
 * Time : 16/4/20 上午11:49
 * <p>
 * Function:simple implement for data source provider
 * <p>
 * ************************************************
 */
public class DefaultDataSourceProvider implements DataSourceProvider {

    private LinkedList<DataSource> mDataSourceList = new LinkedList<>();

    private boolean mShutDown;

    @Override
    public void fetch() {
        //not implement
    }

    @Override
    public void set(DataSource dataSource) {
        synchronized (this){
            mDataSourceList.push(dataSource);
        }
    }

    @Override
    public DataSource get() {
        synchronized (this){
            return mDataSourceList.poll();
        }
    }

    @Override
    public void shutdown() {
        mShutDown = true;
    }

    @Override
    public boolean isShutdown() {
        return mShutDown;
    }
}
