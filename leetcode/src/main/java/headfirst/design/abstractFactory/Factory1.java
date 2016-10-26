package headfirst.design.abstractFactory;

//每一种牌子的产品负责生产工厂，即不同的厂商负责自己牌子的生产
public abstract class Factory1 {
	abstract public IproductA getproductA1();
	abstract public IproductB getProductB1();
}
