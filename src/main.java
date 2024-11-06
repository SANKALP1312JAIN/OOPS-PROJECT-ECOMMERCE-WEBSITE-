import java.util.ArrayList;
import java.util.List;

// Abstract User Class
abstract class User {
    protected String userID;
    protected String username;

    public User(String id, String name) {//constructor
        this.userID = id;
        this.username = name;
    }

    public abstract String getUserRole(); // Abstract method to enforce implementation in subclasses
}

// Product Class
class Product {
    private int productID;
    private String name;
    private double price;
    private int stock;

    public Product(int id, String name, double price, int stock) {
        this.productID = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void updateStock(int amount) {
        this.stock += amount;
    }

    public String getName() {
        return name;
    }

    public int getProductID() {
        return productID;
    }
}

// ShoppingCart Class
class ShoppingCart {
    private List<Product> cartItems;

    public ShoppingCart() {
        this.cartItems = new ArrayList<>();
    }

    public void addItem(Product product) {
        cartItems.add(product);
    }

    public void removeItem(Product product) {
        cartItems.remove(product);
    }

    public double calculateTotal() {
        double total = 0;
        for (Product item : cartItems) {
            total += item.getPrice();
        }
        return total;
    }

    public List<Product> getCartItems() {
        return new ArrayList<>(cartItems); // Returning a copy for encapsulation
    }

    public void clearCart() {
        cartItems.clear();
    }
}

// Order Class
class Order {
    private int orderID;
    private List<Product> products;
    private double orderTotal;

    public Order(int id, List<Product> products) {
        this.orderID = id;
        this.products = new ArrayList<>(products);
        this.orderTotal = 0;
    }

    public void calculateTotal() {
        for (Product product : products) {
            orderTotal += product.getPrice();
        }
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public int getOrderID() {
        return orderID;
    }
}

class Admin extends User {

    public Admin(String id, String name) {
        super(id, name);//calls the super class constructor
    }

    @Override
    public String getUserRole() {
        return "Admin";
    }

    public void addProduct() {
        // Logic to add a product
    }

    public void removeProduct() {
        // Logic to remove a product
    }

    public void updateProduct() {
        // Logic to update a product
    }
}

// Customer Class Inheriting User
class Customer extends User {
    private ShoppingCart shoppingCart;
    private List<Order> orderHistory;

    public Customer(String id, String name) {
        super(id, name);
        this.shoppingCart = new ShoppingCart();
        this.orderHistory = new ArrayList<>();
    }

    @Override
    public String getUserRole() {
        return "Customer";
    }

    public void addToCart(Product product) {
        shoppingCart.addItem(product);
    }

    public void placeOrder() {
        Order newOrder = new Order(orderHistory.size() + 1, shoppingCart.getCartItems());
        newOrder.calculateTotal();
        orderHistory.add(newOrder);
        shoppingCart.clearCart();
    }
}

// Main Class to Run the System
public class OnlineShoppingSystem {
    public static void main(String[] args) {
        Admin admin = new Admin("A1", "AdminUser");
        Customer customer = new Customer("C1", "CustomerUser");

        Product product1 = new Product(101, "Laptop", 75000.0, 10);
        Product product2 = new Product(102, "Smartphone", 50000.0, 20);

        // Admin manages products
        admin.addProduct();
        admin.removeProduct();

        // Customer adds items to the cart and places an order
        customer.addToCart(product1);
        customer.addToCart(product2);
        System.out.println("Total Cart Value: " + customer.shoppingCart.calculateTotal());

        customer.placeOrder();
        System.out.println("Order placed with total amount: " + customer.orderHistory.get(0).getOrderTotal());
    }
}
