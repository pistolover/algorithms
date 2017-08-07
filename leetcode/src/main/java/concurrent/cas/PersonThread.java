package concurrent.cas;

public class PersonThread extends Thread {

    private String pname;
    private Integer page;

    @Override
    public void run() {
        this.pname = "hello";
        this.page = 20;
    }

    @Override
    public String toString() {
        return "PersonThread [name=" + pname + ", age=" + page + "]";
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

}
