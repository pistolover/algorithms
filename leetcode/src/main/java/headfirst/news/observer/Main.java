package headfirst.news.observer;

public class Main {

    public static void main(String[] args) {
    	//具体的被观察者，比如京东商城上的图书
    	//一旦我们对一些书籍关注，但书降价后京东就会以信息的形式告诉我们
    	ConcreateBook concreateBook = new ConcreateBook();
    	concreateBook.setName("<<Headfirst>>");
    	concreateBook.setPrice(32.00);// 假设原价是45，现在降价促销

        //观察者在实际的应用中可以从数据库或文件中读取数据
    	//手机短信
        BuyerMobileMessage bm = new BuyerMobileMessage();
        bm.setBuyerId("001");
        bm.setMobileNo("13810500085");
        
        //email
        BuyerEmail be = new BuyerEmail();
        be.setBuyerId("001");
        be.setEmail("dobodo@163.com");

        // 增加观察者，在实际应用中就是那些人对该书做了关注
        concreateBook.addObserver(bm);
        concreateBook.addObserver(be);
        //价格修改后调用
        concreateBook.modifyPrice(concreateBook);

    }
}
