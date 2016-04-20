package nnero.ndownload.callback;

import nnero.ndownload.bean.DownloadBean;

/**
 * **********************************************
 * <p>
 * Author NNERO
 * <p>
 * Time : 16/4/20 上午10:07
 * <p>
 * Function: callback in down
 * <p>
 * ************************************************
 */
public interface Callback {

    /**
     * when downloadtask finished ,call() will be invoked.
     * @param bean
     */
    void call(DownloadBean bean);
}
