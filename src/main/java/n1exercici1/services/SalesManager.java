package n1exercici1.services;

import n1exercici1.products.Product;
import n1exercici1.sales.Sale;
import n1exercici1.sales.TicketPrinter;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class SalesManager {

    private static SalesManager salesManager;
    private final List<Sale> salesHistoryList;
    private double earnedMoney;

    private SalesManager(DAOService service, String flowerShopName) {
        this.salesHistoryList = service.getSaleList(flowerShopName);
        calculateTotalSalesValue();
    }
    public static SalesManager getSalesManager(DAOService service, String flowerShopName) {
        if (salesManager == null) salesManager = new SalesManager(service, flowerShopName);
        return salesManager;
    }
    private void calculateTotalSalesValue(){
        this.earnedMoney = this.salesHistoryList.stream().mapToDouble(Sale::getSaleAmount).sum();
    }

    public List<Sale> getSalesHistoryList() {
        return this.salesHistoryList;
    }
    public double getEarnedMoney(){
        return this.earnedMoney;
    }

    public void manageTheCart(List<Product> cart, double salePrice){
        cart.sort(Comparator.comparingInt(Product::getIdProduct));
        Date date = (Calendar.getInstance().getTime());
        addSale(new Sale(salePrice, date , getProductsFromCart(cart)));
    }
    private List<String> getProductsFromCart(List<Product> cart){
        return cart.stream().map(Product::toTable).toList();
    }
    private void addSale(Sale sale) {
        this.salesHistoryList.add(sale);
        System.out.println("Sale registered.");
        updateEarnedMoney(sale);
    }
    private void updateEarnedMoney(Sale sale){
        this.earnedMoney += sale.getSaleAmount();
    }
    public void printSalesHistory() {
        this.salesHistoryList.forEach(System.out::println);
    }
    public void printTcket(int idSale){
        String ticketSale = this.salesHistoryList.get(idSale).generateTicket();
        System.out.println(ticketSale);
        TicketPrinter.printTicketToTXT(ticketSale, idSale);
        System.out.println("\nThe ticket has been printed.");
    }
}