package file.entity;

import java.util.List;

public class Cart extends Entity {

    private Long id;

    private User user;

    private List<Ware> wares;

    private Double total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Ware> getWares() {
        return wares;
    }

    public void setWares(List<Ware> wares) {
        this.wares = wares;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

}
