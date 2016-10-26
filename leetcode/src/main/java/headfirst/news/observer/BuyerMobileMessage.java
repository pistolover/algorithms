package headfirst.news.observer;

public class BuyerMobileMessage implements Watcher {
    private String buyerId = "";
    private String mobileNo = "";

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;

    }

    public void update(Watched o, Object arg) {
    	ConcreateBook b = (ConcreateBook) arg;
        System.out.println("给顾客的发手机短信:" + b.getName() + "降价了,目前价格为：" + b.getPrice());
    }

}
