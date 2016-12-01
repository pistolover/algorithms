package future.mutiTask;

//数据库中的实体bean
public class Person {
  private int id;
  private String name;
  private int age;
  private String address;
  private long telephone;


  public Person(){}

  public Person(int id, String name, int age, String address, long tel){
      this.id = id;
      this.name = name;
      this.age = age;
      this.address = address;
      this.telephone = tel;
  }


  public String getName() {
      return name;
  }

  public void setName(String name) {
      this.name = name;
  }

  public int getAge() {
      return age;
  }

  public void setAge(int age) {
      this.age = age;
  }

  public int getId() {
      return id;
  }

  public void setId(int id) {
      this.id = id;
  }

  public String getAddress() {
      return address;
  }

  public void setAddress(String address) {
      this.address = address;
  }

  public long getTelephone() {
      return telephone;
  }

  public void setTelephone(long telephone) {
      this.telephone = telephone;
  }
}
