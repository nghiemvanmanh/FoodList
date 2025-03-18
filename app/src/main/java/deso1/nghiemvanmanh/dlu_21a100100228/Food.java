package deso1.nghiemvanmanh.dlu_21a100100228;

public class Food {
    private String id;
    private String name;
    private double price;
    private String imageResourceId;

    public Food(String name, double price, String image) {
    }

    public Food(String id, String name, double price, String imageResourceId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageResourceId = imageResourceId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(String imageResourceId) {
        this.imageResourceId = imageResourceId;
    }
}
