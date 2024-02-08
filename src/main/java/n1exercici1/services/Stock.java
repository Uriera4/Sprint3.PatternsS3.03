package n1exercici1.services;

import n1exercici1.exceptions.ProductDoesNotExistsException;
import n1exercici1.products.*;

import java.util.ArrayList;
import java.util.List;

public class Stock {

    private static Stock stock;
    private boolean initStock = false;
    private List<Tree> treeStock;
    private List<Flower> flowerStock;
    private List<Decoration> decorationStock;
    private double stockValue;

    private Stock (String shopName, DAOService service){
        this.treeStock = new ArrayList<>();
        this.flowerStock = new ArrayList<>();
        this.decorationStock = new ArrayList<>();
        List<Product> productStock = service.getProductList(shopName);
        if (productStock!=null) productStock.forEach(this::addProduct);
        this.initStock = true;
    }
    public static Stock getStock (String shopName, DAOService service){
        if (stock == null) stock = new Stock(shopName, service);
        return stock;
    }
    public List<Tree> getTreeStock(){
        return treeStock;
    }
    public List<Flower> getFlowerStock(){
        return flowerStock;
    }
    public List<Decoration> getDecorationStock(){
        return decorationStock;
    }
    public double getStockValue(){
        return stockValue;
    }

    public void addProduct (Product product) {
        if (product instanceof Tree) {;
            treeStock.add((Tree) product);
        } else if (product instanceof Flower) {
            flowerStock.add((Flower) product);
        } else if (product instanceof Decoration) {
            decorationStock.add((Decoration) product);
        }
        if(this.initStock) System.out.println("Product stocked.");
        updateStockValue(product, "add");
    }
    public void removeProduct (Product product){
        try {
            switch (product) {
                case Tree tree -> treeStock.remove(tree);
                case Flower flower -> flowerStock.remove(flower);
                case Decoration decoration -> decorationStock.remove(decoration);
                default -> throw new ProductDoesNotExistsException("This product does not exist.");
            }
            System.out.println("Product removed");
            updateStockValue(product, "remove");
        } catch (ProductDoesNotExistsException e){
            System.out.println(e.getMessage());
        }
    }
    private void updateStockValue(Product product, String action){
        stockValue += (action.equals("add") ? product.getPrice() : -product.getPrice());
    }

}
