package nnero.ndownload.callback;

import nnero.ndownload.bean.DownloadBean;

/**
 * **********************************************
 * <p>
 * Author NNERO
 * <p>
 * Time : 16/4/20 下午2:12
 * <p>
 * Function: just console print !
 * <p>
 * ************************************************
 */
public class ConsoleCallback implements Callback {
    @Override
    public void call(DownloadBean bean) {
        if(bean.status == DownloadBean.FINISH){
            System.out.println("url:"+bean.url+" :"+bean.fileName+": download finished!");
        } else {
            System.out.println("url:"+bean.url+" :"+bean.fileName+": download fail!");
        }
    }
}
