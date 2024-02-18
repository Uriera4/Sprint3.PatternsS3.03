package n1exercici1.products;

public class Flower extends Product{

    private final String color;

    public Flower(String name, double price, String color){
        super(name, price);
        this.color = color;
    }

    public String getColor(){
        return this.color;
    }
    public String getType(){
        return "FLOWER";
    }
    public String toString(){
        return "Flower: " + getName() + ", Color: " + this.color + ", Price: " + getPrice() + "€.";
    }
    public String toTable(){
        return "Flower     " + getName() + "    " + this.color + "      " + getPrice() + "€.";
    }

}
