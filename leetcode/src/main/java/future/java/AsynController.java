package future.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AsynController {

    private ExecutorService service;

    private FutureContext<String> futureContext;

    public FutureContext<String> getFutureContext() {
        return futureContext;
    }

    public AsynController() {
        this.service = Executors.newCachedThreadPool();
        this.futureContext = new FutureContext<String>();
    }

    public static void main(String[] args) {
        AsynController con = new AsynController();
        con.startCompution();

        outputResult rt = new outputResult();
        rt.setFuContext(con.getFutureContext());

        Thread t = new Thread(rt);
        t.start();
    }

    private void startCompution() {
        final Random r = new Random();
        //创建目标线程并执行，将结果存起来
        for (int i = 0; i < 30; i++) {
            Future<String> submit = this.service.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    int nextInt = r.nextInt(100);
                    Thread.sleep(500);
                    return " " + nextInt;
                }
            });
            this.futureContext.add(submit);
        }
    }

    public static class FutureContext<T> {
        private List<Future<T>> list = new ArrayList<Future<T>>();

        public List<Future<T>> getList() {
            return list;
        }

        public void add(Future<T> f) {
            this.list.add(f);
        }
    }

    public static class outputResult implements Runnable {
        private FutureContext<String> fuContext;

        public FutureContext<String> getFuContext() {
            return fuContext;
        }

        public void setFuContext(FutureContext<String> fuContext) {
            this.fuContext = fuContext;
        }

        public void run() {
            System.err.println("start to output result:");
            List<Future<String>> list = this.fuContext.getList();
            for (Future<String> future : list) {
                this.outputData(future);
            }
        }

        int t = 0;
        private void outputData(Future<String> future) {
            try {
                while (true) {
                    if (future.isDone() && !future.isCancelled()) {
                        if(t == 0) {
                            Thread.sleep(5000);
                            t++;
                        }
                        System.err.println("future: " + future + "; result" + future.get());
                        break;
                    } else {
                        Thread.sleep(1000);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
