package service.register;

import java.util.concurrent.CountDownLatch;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;


/**
 * 需要传入一个注册ip进来
 * @author liqqc
 *
 */
public class RpcRegister {

    private static final Logger logger = LogManager.getLogger(RpcRegister.class);

    private Integer timeout = ZkConstant.TIMEOUT;

    private static String registerAddress;

    private static final CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {
        RpcRegister register = new RpcRegister();
        registerAddress = "127.0.0.1:2181";
        register.register("127.0.0.1:8885,127.0.0.1:8886,127.0.0.1:8887,127.0.0.1:8888");
        Thread.sleep(Long.MAX_VALUE);
    }
    
    public void register(String data) {
        try {
            if (data != null) {
              ZooKeeper zk = connectServer();
              if (zk != null) {
                // 创建节点
                createNode(zk, data);
              }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
      }
      /**
       *
       *创建zooKeeper
       * @return
       */
      private ZooKeeper connectServer() {
        ZooKeeper zk = null;
        try {
          // 创建一个zooKeeper实例，第一个参数是目标服务器地址和端口，第二个参数是session 超时时间，第三个参数是节点发生变化时的回调方法
          zk = new ZooKeeper(registerAddress, timeout, new Watcher() {
            public void process(WatchedEvent event) {
              if (event.getState() == Event.KeeperState.SyncConnected) {
                  latch.countDown();
              }
            }
          });
          // 阻塞到计数器为0，直到节点的变化回调方法执行完成
          latch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 返回ZooKeeper实例
        return zk;
      }
      /**
       *
       *
       * @param zk ZooKeeper的实例
       * @param data 注册数据
       */
      private void createNode(ZooKeeper zk, String data) {
        try {
            
            String[] split = data.split(",");
            for (String string : split) {
                byte[] bytes = string.getBytes();
                /**
                 * 创建一个节点，第一个参数是该节点的路径，第二个参数是该节点的初始化数据，第三个参数是该节点的ACL，第四个参数指定节点的创建策略
                 */
                String createResult = zk.create(ZkConstant.ROOT + "/", bytes, ZooDefs.Ids.OPEN_ACL_UNSAFE,CreateMode.EPHEMERAL_SEQUENTIAL);
                System.err.println(createResult);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
      }
      /**
       * Getter method for property <tt>timeout</tt>.
       *
       * @return property value of timeout
       */
      public int getTimeout() {
        return timeout;
      }
      /**
       * Setter method for property <tt>timeout</tt>.
       *
       * @param timeout value to be assigned to property timeout
       */
      public void setTimeout(int timeout) {
        this.timeout = timeout;
      }
      /**
       * Getter method for property <tt>registerPath</tt>.
       *
       * @return property value of registerPath
       */
      /**
       * Getter method for property <tt>registerAddress</tt>.
       *
       * @return property value of registerAddress
       */
      public String getRegisterAddress() {
        return registerAddress;
      }
      /**
       * Setter method for property <tt>registerAddress</tt>.
       *
       * @param registerAddress value to be assigned to property registerAddress
       */
      public void setRegisterAddress(String registerAddress) {
        this.registerAddress = registerAddress;
      }
}
