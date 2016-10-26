package acync.guava;

import java.io.Serializable;

/**
 * 抽象的响应类 只提供响应状态字段
 * @author liqqc
 */
public abstract class BaseResponse implements Serializable {
    private String status;
}
