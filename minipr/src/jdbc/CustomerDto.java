package jdbc;
// Dto : Data Transfer Object
// 메소드의 파라미터로 사용, 메소드의 리턴 결과로 사용
public class CustomerDto {
    private int custId;
    private String name;
    private String address;
    private String phone;

    public CustomerDto() {}
    public CustomerDto(int custId, String name, String address, String phone) {
        super();
        this.custId = custId;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public int getCustId() {
        return custId;
    }
    public void setCustId(int custId) {
        this.custId = custId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "CustomerDto [custId=" + custId + ", name=" + name + ", address=" + address + ", phone=" + phone + "]";
    }
}