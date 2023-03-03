package ma.enset.javafx_jdbc_orm.dao.entities;
//une classe persistante ou entité
public class Product {
    private  long id;
    private String name;
    private String reference;
    private float price;
    private Category category;

    public Product() {
    }

    public Product(long id, String name, String reference, float price, Category category) {
        this.id = id;
        this.name = name;
        this.reference = reference;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", reference='" + reference + '\'' +
                ", price=" + price +
                '}';
    }
}
