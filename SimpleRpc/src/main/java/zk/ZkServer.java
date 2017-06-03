package zk;

import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

public class ZkServer {

    private static ZooKeeper zk = null;

    private static final String[] servers = { "127.0.0.1:2203", "127.0.0.1:2204", "127.0.0.1:2205", "127.0.0.1:2206" };

    public static void main(String[] args) throws Exception {

        // 获取与zookeeper通信的客户端连接
        zk = new ZooKeeper(Constant.zkhosts, Constant.sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                // TODO Auto-generated method stub
            }
        });

        // 一启动就去zookeeper上注册服务器信息，参数1： 服务器的主机名 参数2：服务器的监听端口
        // 先创建出父节点
        if (zk.exists(Constant.parentZnodePath, false) == null) {
            zk.create(Constant.parentZnodePath, null, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }

        
        zk.create(Constant.parentZnodePath, "servers".getBytes(), Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL);
        
        // 连接zk创建znode
        for (String server : servers) {
            zk.create(Constant.parentZnodePath + "/", server.getBytes(), Ids.OPEN_ACL_UNSAFE,
                    CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println("server " + "localhost" + " is online ......");
        }
        
        // 进入业务逻辑处理流程
        System.out.println("server " + "localhost" + " is waiting for task process......");

        if ( zk.getState().isConnected()) {
            try {
                List<String> children = zk.getChildren("/servers", null);
                zk.delete("/servers/0000000155", -1);
                List<String> children2 = zk.getChildren("/servers", null);
                System.err.println();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Thread.sleep(Long.MAX_VALUE);

    }
}
