package org.example.kiosk;

import org.example.menu.Menu;
import org.example.menu.MenuItem;
import org.example.order.Order;
import org.example.order.DiscountType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Kiosk.java
 * Main controller class for the kiosk system
 * Handles menu display, user input, and program flow
 */
public class Kiosk {
    private final List<Menu> menus;
    private final Scanner scanner;
    private final Order order;

    /**
     * Constructs a new Kiosk instance
     */
    public Kiosk() {
        this.menus = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.order = new Order();
        initializeMenus();
    }

    /**
     * Initialize all menu categories and their items
     */
    private void initializeMenus() {
        // Burgers Menu
        Menu burgerMenu = new Menu("Burgers");
        List<MenuItem> burgerItems = List.of(
                new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"),
                new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"),
                new MenuItem("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"),
                new MenuItem("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거")
        );
        burgerItems.forEach(burgerMenu::addItem);
        menus.add(burgerMenu);

        // Drinks Menu
        Menu drinkMenu = new Menu("Drinks");
        List<MenuItem> drinkItems = List.of(
                new MenuItem("Shack-made Lemonade", 3.9, "매장에서 직접 만드는 상큼한 레몬에이드"),
                new MenuItem("Fresh Brewed Iced Tea", 3.4, "직접 유기농 홍차를 우려낸 아이스티"),
                new MenuItem("Fifty/Fifty", 3.5, "레몬에이드와 아이스티의 만남"),
                new MenuItem("Fountain Soda", 2.7, "콜라, 스프라이트, 환타 중 선택")
        );
        drinkItems.forEach(drinkMenu::addItem);
        menus.add(drinkMenu);

        // Desserts Menu
        Menu dessertMenu = new Menu("Desserts");
        List<MenuItem> dessertItems = List.of(
                new MenuItem("Classic Hand-Spun Shakes", 5.9, "바닐라, 초콜렛, 솔티드 카라멜"),
                new MenuItem("Floats", 5.9, "루트 비어, 퍼플 카우, 크림시클"),
                new MenuItem("Cup & Cone", 4.9, "바닐라, 초콜렛"),
                new MenuItem("Concretes", 5.9, "쉐이크쉑의 쫀득한 커스터드와 다양한 믹스-인의 조합")
        );
        dessertItems.forEach(dessertMenu::addItem);
        menus.add(dessertMenu);
    }

    /**
     * Starts the kiosk program
     */
    public void start() {
        while (true) {
            System.out.println("\n[ MAIN MENU ]");
            displayMainMenu();

            if (order.hasItems()) {
                System.out.println("[ ORDER MENU ]");
                System.out.println("4. Orders | 장바구니를 확인 후 주문합니다.");
                System.out.println("5. Cancel | 진행중인 주문을 취소합니다.");
            }

            int choice = getValidInput(0, order.hasItems() ? menus.size() + 2 : menus.size());
            if (choice == -1) continue;

            if (choice == 0) {
                System.out.println("\n프로그램을 종료합니다.");
                break;
            }

            try {
                processMainMenuChoice(choice);
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Processes the user's choice from the main menu
     * @param choice User's menu selection
     */
    private void processMainMenuChoice(int choice) {
        if (choice == 0) {
            System.out.println("\n프로그램을 종료합니다.");
            System.exit(0);
        } else if (choice >= 1 && choice <= menus.size()) {
            displayMenuItems(menus.get(choice - 1));
        } else if (choice == 4) {
            processOrder();
        } else if (choice == 5) {
            order.cancelOrder();
        }
    }

    /**
     * Displays items for a specific menu category
     * @param menu The menu category to display
     */
    private void displayMenuItems(Menu menu) {
        while (true) {
            System.out.println("\n[ " + menu.getCategory() + " MENU ]");
            menu.getItems().forEach(item ->
                    System.out.printf("%d. %s%n", menu.getItems().indexOf(item) + 1, item.toString()));
            System.out.println("0. 뒤로가기");

            int choice = getValidInput(0, menu.getItems().size());
            if (choice == -1) continue;

            if (choice == 0) {
                return;
            }

            MenuItem selectedItem = menu.getItems().get(choice - 1);
            System.out.println("\n선택한 메뉴: " + selectedItem.toString());

            System.out.println("위 메뉴를 장바구니에 추가하시겠습니까?");
            System.out.println("1. 확인");
            System.out.println("2. 취소");

            int confirmChoice = getValidInput(1, 2);
            if (confirmChoice == 1) {
                order.addItem(selectedItem);
            }
            return;
        }
    }

    /**
     * Processes the current order
     */
    private void processOrder() {
        try {
            order.displayOrderDetails();
            System.out.println("1. 주문");
            System.out.println("2. 메뉴판");

            int choice = getValidInput(1, 2);
            if (choice == 1) {
                DiscountType.displayOptions();
                int discountChoice = getValidInput(1, DiscountType.values().length);
                order.completeOrder(DiscountType.fromCode(discountChoice));
            }
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Gets valid user input within a specified range
     * @param min Minimum valid value
     * @param max Maximum valid value
     * @return Valid user input or -1 if invalid
     */
    private int getValidInput(int min, int max) {
        try {
            int choice = scanner.nextInt();
            if (choice >= min && choice <= max) {
                return choice;
            } else {
                System.out.println("잘못된 선택입니다. " + min + "부터 " + max + "까지의 숫자를 입력해주세요.");
            }
        } catch (Exception e) {
            System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.");
            scanner.nextLine(); // Clear invalid input
        }
        return -1; // Return -1 if input is invalid
    }

    /**
     * Displays the main menu categories
     */
    private void displayMainMenu() {
        menus.forEach(menu -> System.out.printf("%d. %s%n", menus.indexOf(menu) + 1, menu.getCategory()));
        System.out.println("0. 종료");
    }
}