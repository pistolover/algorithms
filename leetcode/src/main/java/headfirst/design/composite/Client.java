package headfirst.design.composite;

public class Client {

	public static void main(String[] args) {
		Employee employee1 = new Employee("张三");
		Employee employee2 = new Employee("李四");
		Employee employee3 = new Employee("王五");
		Employee employee4 = new Employee("赵六");

		Manager manager1 = new Manager("总1");
		Manager manager2 = new Manager("总2");
		manager1.add(manager2);
		manager1.add(employee1);
		manager2.add(employee2);
		manager2.add(employee3);
		manager2.add(employee4);
		
		manager1.method();
	}

}
