package com.protobuftest.protobuf.encode;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class TInteger {
    @Protobuf(fieldType = FieldType.INT32, required =false, order = 1)
    private Integer age;

    @Protobuf(fieldType = FieldType.STRING, required = false, order = 2)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}
