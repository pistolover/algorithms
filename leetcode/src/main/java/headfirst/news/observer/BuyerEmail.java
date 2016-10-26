package headfirst.news.observer;

public class BuyerEmail implements Watcher {
    private String buyerId = "";
    private String email = "";

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // 该方法会被“被观察者的父类”既Observable调用
    public void update(Watched o, Object arg) {
        // 这里做具体发电子邮件的操作
    	ConcreateBook b = (ConcreateBook) arg;
        System.out.println("给顾客的发电子邮件:" + b.getName() + "降价了,目前价格为：" + b.getPrice());
    }
}
