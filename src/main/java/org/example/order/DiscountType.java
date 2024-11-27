package org.example.order;

/**
 * DiscountType.java
 * Enum representing different types of discounts available for orders
 */
public enum DiscountType {
    /** 10% discount for veterans */
    VETERAN(1, "국가유공자", 0.10),
    /** 5% discount for military personnel */
    MILITARY(2, "군인", 0.05),
    /** 3% discount for students */
    STUDENT(3, "학생", 0.03),
    /** No discount applied */
    NONE(4, "일반", 0.0);

    private final int code;         // Unique identifier for the discount type
    private final String description; // Description of the discount type
    private final double rate;      // Discount rate as a decimal (e.g., 0.10 for 10%)

    /**
     * Constructs a discount type with specified parameters
     * @param code Unique identifier
     * @param description Human-readable description
     * @param rate Discount rate as decimal
     */
    DiscountType(int code, String description, double rate) {
        this.code = code;
        this.description = description;
        this.rate = rate;
    }

    /**
     * Finds a discount type by its code
     * @param code The code to search for
     * @return The matching DiscountType or NONE if not found
     */
    public static DiscountType fromCode(int code) {
        for (DiscountType type : values()) {
            if (type.code == code) return type;
        }
        return NONE;
    }

    /** @return The description of the discount type */
    public String getDescription() {
        return description;
    }

    /** @return The discount rate as a decimal */
    public double getRate() {
        return rate;
    }

    /** @return The unique code of the discount type */
    public int getCode() {
        return code;
    }

    /**
     * Displays all available discount options to the console
     * Shows code, description, and percentage for each type
     */
    public static void displayOptions() {
        System.out.println("\n할인 정보를 입력해주세요.");
        for (DiscountType type : values()) {
            System.out.printf("%d. %s : %.0f%%%n",
                    type.getCode(),
                    type.getDescription(),
                    type.getRate() * 100);
        }
    }

    /**
     * Calculates the discounted price
     * @param price The original price before discount
     * @return The price after applying the discount
     */
    public double calculateDiscountedPrice(double price) {
        return price * (1 - this.rate);
    }
}