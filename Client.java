package Class;

import java.util.Objects;

public class Client {
    enum gender {Man, Woman} ;
    protected String name;
    protected String userName;
    protected String password;
    protected int phoneNumber;
    protected gender clientGender;

    public Client(String name, String userName, String password, int phoneNumber, gender clientGender) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.clientGender = clientGender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public gender getClientGender() {
        return clientGender;
    }

    public void setClientGender(gender clientGender) {
        this.clientGender = clientGender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return userName.equals(client.userName) && password.equals(client.password);
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", clientGender=" + clientGender +
                '}';
    }
}
