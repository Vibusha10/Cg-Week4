import java.util.*;

public class Program3 {
    private Map<String, Double> productPriceMap = new HashMap<>();
    private Map<String, Double> cartItemsMap = new LinkedHashMap<>();
    private Map<String, Double> sortedItemsMap = new TreeMap<>(Comparator.comparing(cartItemsMap::get));

    public void addProduct(String productName, double price) {
        productPriceMap.put(productName, price);
    }

    public void addItemToCart(String productName) {
        if (productPriceMap.containsKey(productName)) {
            cartItemsMap.put(productName, productPriceMap.get(productName));
        } else {
            System.out.println("Product not found: " + productName);
        }
    }

    public void displayCartItems() {
        System.out.println("Items in the cart (in order of addition):");
        for (Map.Entry<String, Double> entry : cartItemsMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public void displaySortedItemsByPrice() {
        System.out.println("\nItems sorted by price:");
        sortedItemsMap.putAll(cartItemsMap);
        for (Map.Entry<String, Double> entry : sortedItemsMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        Program3 cart = new Program3();

        cart.addProduct("Laptop", 1200.00);
        cart.addProduct("Smartphone", 800.50);
        cart.addProduct("Headphones", 150.75);

        cart.addItemToCart("Laptop");
        cart.addItemToCart("Smartphone");
        cart.addItemToCart("Headphones");

        cart.displayCartItems();
        cart.displaySortedItemsByPrice();
    }
}