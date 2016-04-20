import nnero.ndownload.DownloadEngine;
import nnero.ndownload.bean.DataSource;
import nnero.ndownload.callback.ConsoleCallback;
import nnero.ndownload.provider.DataSourceProvider;
import nnero.ndownload.provider.DefaultDataSourceProvider;
import nnero.ndownload.task.HttpDownloadTask;
import org.junit.Test;

/**
 * **********************************************
 * <p>
 * Author NNERO
 * <p>
 * Time : 16/4/20 上午10:25
 * <p>
 * Function:0.1.0 version test
 * <p>
 * ************************************************
 */
public class test_010 {
    @Test
    public void testDown() {
        DataSourceProvider provider = new DefaultDataSourceProvider();
        provider.set(new DataSource("http://pic4.nipic.com/20090903/2125404_132352014851_2.jpg",
                "/Users/nnero/1.jpg"));
        provider.set(new DataSource("http://pic4.nipic.com/20090903/2125404_132352014851_2.jpg",
                "/Users/nnero/2.jpg"));
        provider.set(new DataSource("http://pic4.nipic.com/20090903/2125404_132352014851_2.jpg",
                "/Users/nnero/3.jpg"));

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("provider shutdown!");
                provider.shutdown();
            }
        }).start();
        DownloadEngine.create(HttpDownloadTask.class, provider)
                .mode(DownloadEngine.MODE_SET)
                .thread(3)
                .callback(new ConsoleCallback())
                .start();
    }
}
