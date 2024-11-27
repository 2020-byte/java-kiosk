package org.example.order;

import org.example.menu.MenuItem;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Order.java
 * A class that manages and processes orders consisting of multiple MenuItem objects
 * with support for discounts and order confirmation
 */
public class Order {
    private final List<MenuItem> items;

    /**
     * Constructor for the Order class
     */
    public Order() {
        this.items = new ArrayList<>();
    }

    /**
     * Adds a menu item to the order
     * @param item The MenuItem object to add
     */
    public void addItem(MenuItem item) {
        items.add(item);
        System.out.printf("%s 이 장바구니에 추가되었습니다.%n", item.getName());
    }

    /**
     * Returns all menu items in the current order
     * @return List of MenuItem objects
     */
    public List<MenuItem> getItems() {
        return List.copyOf(items);
    }

    /**
     * Calculates the total price of all items in the order
     * @return The total price as a double
     */
    public double getTotalPrice() {
        return items.stream()
                .mapToDouble(MenuItem::getPrice)
                .sum();
    }


    /**
     * Displays the current order details
     */
    public void displayOrderDetails() {
        if (items.isEmpty()) {
            throw new IllegalStateException("장바구니가 비어 있습니다.");
        }

        System.out.println("\n아래와 같이 주문 하시겠습니까?");
        System.out.println("[ Orders ]");

        Map<String, Long> OrderLabels = items.stream()
                .collect(Collectors.groupingBy(MenuItem::toString, Collectors.counting()));

        OrderLabels.forEach((label, count) ->
                System.out.printf("%s - %d개%n", label, count));

        System.out.printf("[ Total ]%nW %.1f%n", getTotalPrice());
    }

    /**
     * Processes the final order with the selected discount
     * @param discountType The selected discount type
     */
    public void completeOrder(DiscountType discountType) {
        double finalPrice = discountType.calculateDiscountedPrice(getTotalPrice());
        System.out.printf("주문이 완료되었습니다.%n금액은 W %.1f 입니다.%n", finalPrice);
        items.clear(); // Clear the cart after successful order
    }

    /**
     * Cancels the current order
     */
    public void cancelOrder() {
        if (items.isEmpty()) {
            throw new IllegalStateException("취소할 주문이 없습니다.");
        }
        items.clear();
        System.out.println("진행중인 주문이 취소되었습니다.");
    }

    /**
     * Checks if the order contains any items
     * @return true if the order has items, false otherwise
     */
    public boolean hasItems() {
        return !items.isEmpty();
    }
}