package serialize.test;

import java.util.ArrayList;
import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class Products {

    @Protobuf(fieldType = FieldType.STRING, required = true, order = 1)
    private String s1 = "zz";

    @Protobuf(fieldType = FieldType.STRING, required = true, order = 2)
    private String s2 = "hh";

    @Protobuf(fieldType = FieldType.STRING, required = true, order = 3)
    private String s3 = "ll";

    @Protobuf(fieldType = FieldType.STRING, required = true, order = 4)
    private String s4 = "aa";

    @Protobuf(fieldType = FieldType.STRING, required = true, order = 5)
    private String s5 = "bb";

    @Protobuf(fieldType = FieldType.STRING, required = true, order = 6)
    private String s6 = "cc";

    @Protobuf(fieldType = FieldType.STRING, required = true, order = 7)
    private String s7 = "dd";

    @Protobuf(fieldType = FieldType.STRING, required = true, order = 8)
    private String s8 = "ee";

    @Protobuf(fieldType = FieldType.STRING, required = true, order = 9)
    private String s9 = "ff";

    @Protobuf(fieldType = FieldType.INT32, required = true, order = 10)
    private int i1 = 12345678;

    @Protobuf(fieldType = FieldType.INT32, required = true, order = 11)
    private int i2 = 12345678;

    @Protobuf(fieldType = FieldType.INT32, required = true, order = 12)
    private int i3 = 12345678;

    @Protobuf(fieldType = FieldType.INT32, required = true, order = 13)
    private int i4 = 12345678;

    @Protobuf(fieldType = FieldType.INT32, required = true, order = 14)
    private int i5 = 12345678;

    @Protobuf(fieldType = FieldType.INT32, required = true, order = 15)
    private int i6 = 12345678;

    @Protobuf(fieldType = FieldType.INT32, required = true, order = 16)
    private int i7 = 12345678;

    @Protobuf(fieldType = FieldType.INT32, required = true, order = 17)
    private int i8 = 12345678;

    @Protobuf(fieldType = FieldType.INT32, required = true, order = 18)
    private int i9 = 12345678;

    @Protobuf(fieldType = FieldType.BOOL, required = true, order = 19)
    private boolean b1 = false;

    @Protobuf(fieldType = FieldType.BOOL, required = true, order = 20)
    private boolean b2 = false;

    @Protobuf(fieldType = FieldType.BOOL, required = true, order = 21)
    private boolean b3 = true;

    @Protobuf(fieldType = FieldType.BOOL, required = true, order = 22)
    private boolean b4 = false;

    @Protobuf(fieldType = FieldType.BOOL, required = true, order = 23)
    private boolean b5 = false;

    @Protobuf(fieldType = FieldType.BOOL, required = true, order = 24)
    private boolean b6 = true;

    @Protobuf(fieldType = FieldType.BOOL, required = true, order = 25)
    private boolean b7 = false;

    @Protobuf(fieldType = FieldType.BOOL, required = true, order = 26)
    private boolean b8 = false;

    @Protobuf(fieldType = FieldType.BOOL, required = true, order = 27)
    private boolean b9 = false;

    @Protobuf(fieldType = FieldType.OBJECT, required = false, order = 28)
    private List<String> list;

    public Products() {
//        list.add("acdfghkilpfdfdfdf");
//        list.add("acdfghkilpfdfdfdf");
//        list.add("acdfghkilpfdfdfdf");
//        list.add("acdfghkilpfdfdfdf");
//        list.add("acdfghkilpfdfdfdf");
//        list.add("acdfghkilpfdfdfdf");
//        list.add("acdfghkilpfdfdfdf");
//        list.add("acdfghkilpfdfdfdf");
//        list.add("acdfghkilpfdfdfdf");
//        list.add("acdfghkilpfdfdfdf");
//        list.add("acdfghkilpfdfdfdf");
//        list.add("acdfghkilpfdfdfdf");
//        list.add("acdfghkilpfdfdfdf");
//        list.add("acdfghkilpfdfdfdf");
//        list.add("acdfghkilpfdfdfdf");
    }

    
    
    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public String getS2() {
        return s2;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }

    public String getS3() {
        return s3;
    }

    public void setS3(String s3) {
        this.s3 = s3;
    }

    public String getS4() {
        return s4;
    }

    public void setS4(String s4) {
        this.s4 = s4;
    }

    public String getS5() {
        return s5;
    }

    public void setS5(String s5) {
        this.s5 = s5;
    }

    public String getS6() {
        return s6;
    }

    public void setS6(String s6) {
        this.s6 = s6;
    }

    public String getS7() {
        return s7;
    }

    public void setS7(String s7) {
        this.s7 = s7;
    }

    public String getS8() {
        return s8;
    }

    public void setS8(String s8) {
        this.s8 = s8;
    }

    public String getS9() {
        return s9;
    }

    public void setS9(String s9) {
        this.s9 = s9;
    }

    public int getI1() {
        return i1;
    }

    public void setI1(int i1) {
        this.i1 = i1;
    }

    public int getI2() {
        return i2;
    }

    public void setI2(int i2) {
        this.i2 = i2;
    }

    public int getI3() {
        return i3;
    }

    public void setI3(int i3) {
        this.i3 = i3;
    }

    public int getI4() {
        return i4;
    }

    public void setI4(int i4) {
        this.i4 = i4;
    }

    public int getI5() {
        return i5;
    }

    public void setI5(int i5) {
        this.i5 = i5;
    }

    public int getI6() {
        return i6;
    }

    public void setI6(int i6) {
        this.i6 = i6;
    }

    public int getI7() {
        return i7;
    }

    public void setI7(int i7) {
        this.i7 = i7;
    }

    public int getI8() {
        return i8;
    }

    public void setI8(int i8) {
        this.i8 = i8;
    }

    public int getI9() {
        return i9;
    }

    public void setI9(int i9) {
        this.i9 = i9;
    }

    public boolean isB1() {
        return b1;
    }

    public void setB1(boolean b1) {
        this.b1 = b1;
    }

    public boolean isB2() {
        return b2;
    }

    public void setB2(boolean b2) {
        this.b2 = b2;
    }

    public boolean isB3() {
        return b3;
    }

    public void setB3(boolean b3) {
        this.b3 = b3;
    }

    public boolean isB4() {
        return b4;
    }

    public void setB4(boolean b4) {
        this.b4 = b4;
    }

    public boolean isB5() {
        return b5;
    }

    public void setB5(boolean b5) {
        this.b5 = b5;
    }

    public boolean isB6() {
        return b6;
    }

    public void setB6(boolean b6) {
        this.b6 = b6;
    }

    public boolean isB7() {
        return b7;
    }

    public void setB7(boolean b7) {
        this.b7 = b7;
    }

    public boolean isB8() {
        return b8;
    }

    public void setB8(boolean b8) {
        this.b8 = b8;
    }

    public boolean isB9() {
        return b9;
    }

    public void setB9(boolean b9) {
        this.b9 = b9;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

}
