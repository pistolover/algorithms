package serialize.test;

import java.io.Serializable;
import java.util.List;

import com.baidu.bjf.remoting.protobuf.FieldType;
import com.baidu.bjf.remoting.protobuf.annotation.Protobuf;

public class ListProducts implements Serializable {
    
    @Protobuf(fieldType = FieldType.OBJECT, required = false, order = 1)
    private List<Products> products;

    public List<Products> getProducts() {
        return products;
    }

    public void setProducts(List<Products> products) {
        this.products = products;
    }

}
