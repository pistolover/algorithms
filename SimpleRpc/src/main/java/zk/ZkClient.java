package zk;

import java.util.ArrayList;
import java.util.List;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.ZooKeeper;

public class ZkClient {
//    private static volatile List<String> servers;
    private static ZooKeeper zk;


    /**
     * 从zk中获取在线服务器信息
     */
    public static void updateServers() throws Exception {
        // 从servers父节点下获取到所有子节点，并注册监听
        List<String> children = zk.getChildren(Constant.parentZnodePath, true);
        ArrayList<String> serverList = new ArrayList<String>();
        for (String child : children) {
            byte[] data = zk.getData(Constant.parentZnodePath + "/" + child, false, null);
            serverList.add(new String(data));
        }
        // 如果客户端是一个多线程程序，而且各个线程都会竞争访问servers列表，所以，在成员中用volatile修饰了一个servers变量
        // 而在更新服务器信息的这个方法中，是用一个临时List变量来进行更新
//        servers = serverList;
        // 将更新之后的服务器列表信息打印在控制台观察一下
        for (String server : serverList) {
            System.out.println(server);
        }
        System.out.println("===================");

    }


    public static void main(String[] args) throws Exception {
        // 先构造一个zk的连接
        // 服务器在需求中并不需要做任何监听
        zk = new ZooKeeper(Constant.zkhosts, Constant.sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if (event.getType() == EventType.None)
                    return;
                try {
                    // 获取新的服务器列表,重新注册监听
                    updateServers();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        // 获取服务器列表
        updateServers();

        Thread.sleep(20000);
        // 客户端进入业务流程，请求服务器的服务
        Thread.sleep(Long.MAX_VALUE);

    }

}
