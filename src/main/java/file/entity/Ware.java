package file.entity;

import java.util.ArrayList;

public class Ware extends Entity {
    
    private String name;
    
    private Double price;
    
    private ArrayList<Group> group;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public ArrayList<Group> getGroups() {
        return group;
    }

    public void setGroups(ArrayList<Group> arrayList) {
        this.group = arrayList;
    }
    
}
