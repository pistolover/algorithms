package acync.guava;

/**
 * 用户相关的枚举类
 * 其中每个成员标识一个http请求
 * @author liqqc
 *
 */
public enum UserEnum implements IEnum {
    ADD,
    UPDATE,
    DELETE,
    MODIFY;
}
