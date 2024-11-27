package org.example.menu;

/**
 * MenuItem.java
 * A class that manages individual menu items
 * Includes the name, price, and description of a menu item
 */
public class MenuItem {
    private final String name;        // Name of the menu item
    private final double price;       // Price of the menu item
    private final String description; // Description of the menu item

    /**
     * Constructor for the MenuItem class
     * @param name The name of the menu item
     * @param price The price of the menu item
     * @param description A description of the menu item
     */
    public MenuItem(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    /**
     * Returns a string representation of the menu item
     * @return A string in the format "Name | W Price | Description"
     */
    @Override
    public String toString() {
        return String.format("%s | W %.1f | %s", name, price, description);
    }

    /**
     * Gets the price of the menu item
     * @return The price as a double
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the Name of the menu item
     * @return The name as a string
     */
    public String getName() {
        return name;
    }
}
