package file.entity;

import java.util.ArrayList;
import java.util.Random;

public class User extends Entity {

    private String name;

    private String password;

    private ArrayList<Role> roles;

    private Cart cart;

    private String mail;
    
    private String confirmToken;

    public User() {

    }

    public User(long id, String name, String password, ArrayList<Role> roles, Cart cart, String mail, String confirmToken) {
        super();
        this.mail = mail;
        this.name = name;
        this.password = password;
        this.roles = roles;
        this.cart = cart;
        this.confirmToken=confirmToken;
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

	public String getConfirmToken() {
		return confirmToken;
	}

	public void setConfirmToken(String string) {
		this.confirmToken = string;
	}
	public String generateToken() {
		
		String characters = "qwertyuiopasdfghjklzxcvbnm1234567890QWERTYUIOASDFGHJKLZXCVBNM";
	    Random rnd = new Random();
	    char[] text = new char[61];
	    for (int i = 0; i < 61; i++)
	    {
	        text[i] = characters.charAt(rnd.nextInt(characters.length()));
	    }
	    return new String(text);
		
		
	}
}
