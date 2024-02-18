package n1exercici1.products;

public class Tree extends Product{

    private final double height;

    public Tree (String name, double price, double height){
        super(name, price);
        this.height = height;
    }

    public double getHeight(){
        return this.height;
    }
    public String getType(){
        return "TREE";
    }
    public String toString(){
        return "Tree: " + getName() + ", Height: " + this.height + "m, Price: " + getPrice() + "€.";
    }
    public String toTable(){
        return "Tree       " + getName() + "  " + this.height + "             " + getPrice() + "€.";
    }

}
