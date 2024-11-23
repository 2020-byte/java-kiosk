package org.example.kiosk;
import org.example.menu.Menu;
import org.example.menu.MenuItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main controller class for the kiosk system
 * Handles menu display, user input, and program flow
 */
public class Kiosk {
    private final List<Menu> menus;
    private final Scanner scanner;

    public Kiosk() {
        this.menus = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        initializeMenus();
    }

    /**
     * Initialize all menu categories and their items
     */
    private void initializeMenus() {
        // Burgers Menu
        Menu burgerMenu = new Menu("Burgers");
        burgerMenu.addItem(new MenuItem("ShackBurger", 6.9, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        burgerMenu.addItem(new MenuItem("SmokeShack", 8.9, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        burgerMenu.addItem(new MenuItem("Cheeseburger", 6.9, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        burgerMenu.addItem(new MenuItem("Hamburger", 5.4, "비프패티를 기반으로 야채가 들어간 기본버거"));
        menus.add(burgerMenu);

        // Drinks Menu
        Menu drinkMenu = new Menu("Drinks");
        drinkMenu.addItem(new MenuItem("Shack-made Lemonade", 3.9, "매장에서 직접 만드는 상큼한 레몬에이드"));
        drinkMenu.addItem(new MenuItem("Fresh Brewed Iced Tea", 3.4, "직접 유기농 홍차를 우려낸 아이스티"));
        drinkMenu.addItem(new MenuItem("Fifty/Fifty", 3.5, "레몬에이드와 아이스티의 만남"));
        drinkMenu.addItem(new MenuItem("Fountain Soda", 2.7, "콜라, 스프라이트, 환타 중 선택"));
        menus.add(drinkMenu);

        // Desserts Menu
        Menu dessertMenu = new Menu("Desserts");
        dessertMenu.addItem(new MenuItem("Classic Hand-Spun Shakes", 5.9, "바닐라, 초콜렛, 솔티드 카라멜"));
        dessertMenu.addItem(new MenuItem("Floats", 5.9, "루트 비어, 퍼플 카우, 크림시클"));
        dessertMenu.addItem(new MenuItem("Cup & Cone", 4.9, "바닐라, 초콜렛"));
        dessertMenu.addItem(new MenuItem("Concretes", 5.9, "쉐이크쉑의 쫀득한 커스터드와 다양한 믹스-인의 조합"));
        menus.add(dessertMenu);
    }

    /**
     * Starts the kiosk program
     */
    public void start() {
        while (true) {
            System.out.println("\n[ MAIN MENU ]");
            displayMainMenu();

            int choice = getValidInput(0, menus.size());
            if (choice == -1)  continue;

            if (choice == 0) {
                System.out.println("\n프로그램을 종료합니다.");
                break;
            }

            displayMenuItems(menus.get(choice - 1));
        }
    }


    /**
     * Get user input without looping internally
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
     * Display main menu categories
     */
    private void displayMainMenu() {
        for (int i = 0; i < menus.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, menus.get(i).getCategory());
        }
        System.out.println("0. 종료      | 종료");
    }

    /**
     * Display items for a selected menu category
     * @param menu Selected menu category
     */
    private void displayMenuItems(Menu menu) {
        while (true) {
            System.out.println("\n[ " + menu.getCategory() + " MENU ]");
            List<MenuItem> items = menu.getItems();

            for (int i = 0; i < items.size(); i++) {
                System.out.printf("%d. %s%n", i + 1, items.get(i).toString());
            }
            System.out.println("0. 뒤로가기");

            int choice = getValidInput(0, items.size());
            if(choice == -1)  continue;

            if (choice == 0) {
                return;
            }

            MenuItem selectedItem = items.get(choice - 1);
            System.out.println("\n선택한 메뉴: " + selectedItem.toString());
            return;  // Return to main menu after showing selected item
        }
    }
}