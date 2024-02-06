package sample;

public class Item {
    private String name;
    private String code;
    private double price;

    public Item(String name, String code, double price) {
        super();
        this.name = name;
        this.code = code;
        this.price = price;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

}