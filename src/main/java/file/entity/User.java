package file.entity;

import java.util.ArrayList;

public class User extends Entity{
    
    private String name;
    
    private String password;
    
    private ArrayList<Role> roles;
    
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
    
}
