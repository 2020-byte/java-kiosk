package org.example.menu;
import java.util.ArrayList;
import java.util.List;

/**
 * Menu.java
 * A class that manages and groups multiple MenuItem objects by category
 */

public class Menu {
    private final String category;          // Name of the menu category (e.g., "Burgers", "Drinks", etc.)
    private final List<MenuItem> items;     // List of menu items belonging to this category

    /**
     * Constructor for the Menu class
     * @param category The name of the menu category
     */
    public Menu(String category) {
        this.category = category;
        this.items = new ArrayList<>();
    }

    /**
     * Adds a menu item to the category
     * @param item The MenuItem object to add
     */
    public void addItem(MenuItem item) {
        items.add(item);
    }

    /**
     * Returns all menu items in the current category
     * @return List of MenuItem objects
     */
    public List<MenuItem> getItems() {
        return List.copyOf(items);
    }

    /**
     * Returns the name of the category
     * @return The category name
     */
    public String getCategory() {
        return category;
    }
}

