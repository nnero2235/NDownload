package nnero.ndownload.provider;

import nnero.ndownload.bean.DataSource;

/**
 * **********************************************
 * <p>
 * Author NNERO
 * <p>
 * Time : 16/4/20 上午10:33
 * <p>
 * Function:data source
 * <p>
 * ************************************************
 */
public interface DataSourceProvider {

    /**
     * fetch data active
     */
    void fetch();

    /**
     * set data passive
     * @param dataSource
     */
    void set(DataSource dataSource);

    /**
     * get data source
     * @return
     */
    DataSource get();

    /**
     * this method can stop engine
     * @return
     */
    void shutdown();

    /**
     * is shutdown
     * @return
     */
    boolean isShutdown();
}
