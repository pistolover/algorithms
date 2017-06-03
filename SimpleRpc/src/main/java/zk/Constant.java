package zk;

public class Constant {
    // zk服务器列表
    public static final String zkhosts = "127.0.0.1:2181";
    // 连接的超时时间
    public static final int sessionTimeout = 2000;
    // 服务在zk下的路径
    public static final String parentZnodePath = "/servers";

}
