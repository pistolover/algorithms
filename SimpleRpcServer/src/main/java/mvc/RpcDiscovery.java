package mvc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import constants.ZkConstant;
import io.netty.util.internal.ThreadLocalRandom;

public class RpcDiscovery {
    private Logger logger = LogManager.getLogger(RpcDiscovery.class);
    private CountDownLatch latch = new CountDownLatch(1);
    private String registerAddress;
    private volatile List<String> dataList = new ArrayList<>();

    public void init() {
        System.err.println("Rpc 服务发现初始化...");
        ZooKeeper zk = connectServer();
        if (zk != null) {
            watchNode(zk);
        }
    }

    public String descoveryService() {
        String data = null;
        int size = dataList.size();
        if (size > 0) {
            if (size == 1) {
                data = dataList.get(0);
            } else {
                data = dataList.get(ThreadLocalRandom.current().nextInt(size));
            }
        }
        return data;
    }

    private ZooKeeper connectServer() {
        ZooKeeper zk = null;
        try {
            zk = new ZooKeeper(registerAddress, ZkConstant.TIMEOUT, new Watcher() {
                public void process(WatchedEvent event) {
                    if (event.getState() == Event.KeeperState.SyncConnected) {
                        latch.countDown();
                    }
                }
            });
            latch.await();
        } catch (Exception e) {
            logger.error(e.getCause());
        }
        return zk;
    }

    private void watchNode(final ZooKeeper zk) {
        try {
            List<String> nodeList = zk.getChildren(ZkConstant.ROOT, new Watcher() {
                public void process(WatchedEvent event) {
                    if (event.getType() == Event.EventType.NodeChildrenChanged) {
                        watchNode(zk);
                    }
                }
            });
            List<String> nodes = new ArrayList<String>();
            for (String node : nodeList) {
                byte[] bytes = zk.getData(ZkConstant.ROOT + "/" + node, false, null);
                if(bytes != null && bytes.length !=0 && bytes.length>2) {
                    nodes.add(new String(bytes));
                }
            }
            dataList = nodes;
            if (dataList.isEmpty()) {
                logger.warn("尚未注册任何服务");
            }
            logger.info("dataList : " + dataList);
        } catch (Exception e) {
            logger.error(e.getCause());
        }
    }

    public String getRegisterAddress() {
        return registerAddress;
    }

    public void setRegisterAddress(String registerAddress) {
        this.registerAddress = registerAddress;
    }

}
