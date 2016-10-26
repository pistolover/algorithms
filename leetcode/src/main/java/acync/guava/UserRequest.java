package acync.guava;

/**
 * 用户相关请求类
 * @author liqqc
 *
 */
public class UserRequest implements BaseRequest {

    private static final String REQ_URL = "http://www/126.com";
    
    private String uid;
    private String uname;
    private String desc;

    private static final String UID = "uid";
    private static final String UNAME = "uname";
    private static final String DESC = "desc";

    @Override
    public String build() {
        return REQ_URL + appendParams();
    }

    private String appendParams() {
        StringBuffer buffer  = new StringBuffer();
        buffer.append("?").append(UID).append("=").append(uid);
        buffer.append("&").append(UNAME).append("=").append(uname);
        buffer.append("&").append(DESC).append("=").append(desc);
        return buffer.toString();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
