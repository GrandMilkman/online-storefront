package file.entity;

import java.util.ArrayList;

public class User extends Entity{

    private String name;

    private String password;

    private ArrayList<Role> roles;

    private Cart cart;

    private String mail;

    public User() {

    }

    public User(long id,String name, String password, ArrayList<Role> roles, Cart cart,String mail) {
        super();
        this.mail = mail;
        this.name = name;
        this.password = password;
        this.roles = roles;
        this.cart = cart;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Role> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<Role> arrayList) {
        this.roles = arrayList;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
    
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

}
