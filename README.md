# Kiosk System

A Java-based restaurant kiosk system that manages menu items, orders, and discounts.

## Features

- Digital menu display with categories
- Shopping cart functionality
- Discount system for different customer types
- Order processing and management

## Project Structure

```
org.example/
├── Main.java
├── kiosk/
│   └── Kiosk.java
├── menu/
│   ├── Menu.java
│   └── MenuItem.java
└── order/
    ├── Order.java
    └── DiscountType.java
```

## Components

### Kiosk
Main controller class handling user interactions and program flow. Manages menus, orders, and user input validation.

### Menu System
- `Menu`: Manages groups of menu items by category (Burgers, Drinks, Desserts)
- `MenuItem`: Represents individual menu items with name, price, and description

### Order System
- `Order`: Handles shopping cart functionality and order processing
- `DiscountType`: Enum managing different discount types (Veteran, Military, Student)

## Usage

1. Initialize the system:
```java
Kiosk kiosk = new Kiosk();
kiosk.start();
```

2. Navigate through menus using number inputs
3. Add items to cart
4. Process order with optional discounts


## Technical Requirements

- Java 17
- Console-based input/output system