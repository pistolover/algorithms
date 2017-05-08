package memcache;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class Person {
    @Protobuf(fieldType = FieldType.INT32, required = false, order = 1)
    private Integer userId;

    @Protobuf(fieldType = FieldType.STRING, required = false, order = 2)
    private String userName;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
