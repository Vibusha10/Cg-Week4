import java.util.*;

interface Category {
    String getCategoryName();
}

enum BookCategory implements Category {
    FICTION, NONFICTION, EDUCATIONAL;

    public String getCategoryName() {
        return name();
    }
}

enum ClothingCategory implements Category {
    MEN, WOMEN, KIDS;

    public String getCategoryName() {
        return name();
    }
}

enum GadgetCategory implements Category {
    MOBILE, LAPTOP, ACCESSORY;

    public String getCategoryName() {
        return name();
    }
}

class Product<T extends Category> {
    private String name;
    private double price;
    private T category;

    public Product(String name, double price, T category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public T getCategory() {
        return category;
    }

    public void applyDiscount(double percentage) {
        price = price - (price * percentage / 100);
    }

    public void displayInfo() {
        System.out.println("Product: " + name + ", Price: $" + price + ", Category: " + category.getCategoryName());
    }
}

class MarketplaceUtil {
    public static <T extends Product<?>> void applyDiscount(T product, double percentage) {
        product.applyDiscount(percentage);
        System.out.println("Discount applied: " + percentage + "% on " + product.getName());
    }
}

public class Program2 {
    public static void main(String[] args) {
        Product<BookCategory> book = new Product<>("Java Programming", 45.0, BookCategory.EDUCATIONAL);
        Product<ClothingCategory> shirt = new Product<>("Casual Shirt", 25.0, ClothingCategory.MEN);
        Product<GadgetCategory> phone = new Product<>("Smartphone", 299.0, GadgetCategory.MOBILE);

        List<Product<? extends Category>> catalog = new ArrayList<>();
        catalog.add(book);
        catalog.add(shirt);
        catalog.add(phone);

        for (Product<? extends Category> product : catalog) {
            product.displayInfo();
        }

        System.out.println("\nApplying Discounts:");
        MarketplaceUtil.applyDiscount(book, 10);
        MarketplaceUtil.applyDiscount(shirt, 15);
        MarketplaceUtil.applyDiscount(phone, 5);

        System.out.println("\nCatalog After Discounts:");
        for (Product<? extends Category> product : catalog) {
            product.displayInfo();
        }
    }
}
