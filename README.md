# E-commerce System (Java)

This project is a simplified object-oriented e-commerce system implemented in Java, designed as a solution to the **Fawry Quantum Internship Challenge**.

It models real-world behaviors like managing products (some expirable, some shippable), tracking customer carts, processing checkouts, and integrating a shipping service.

---

## Problem Statement

Implement a system with the following features:

- Define products with `name`, `price`, and `quantity`.
- Some products may **expire** (like Cheese and Biscuits); others may not (like TVs and Mobiles).
- Some products may require **shipping** (like Cheese and TVs), others may not (like mobile scratch cards).
  - Every shippable product should provide its **weight**.
- A customer can:
  - Add products to a cart (with specific quantity, not exceeding stock).
  - Checkout the cart.

During checkout:
- The system must:
  - Print checkout details:
    - Order subtotal
    - Shipping fees
    - Total paid
    - Remaining customer balance
  - Collect items that need shipping and send them to `ShippingService`, which accepts a list of objects with:
    - `getName()`
    - `getWeight()`

Error Handling:
- Throw an error if:
  - The cart is empty
  - A product is out of stock or expired
  - The customer’s balance is insufficient

---

## Design Overview

### Key Classes

| Class              | Responsibility                                                                 |
|-------------------|----------------------------------------------------------------------------------|
| `Product`          | Base class for all products with `name`, `price`, `quantity`, and `weight`.     |
| `ExpirableProduct` | Subclass of `Product` with `expiryDate` and an overridden `isExpired()` method. |
| `CartItem`         | Represents a product + quantity selected by a customer.                         |
| `Cart`             | Holds a list of `CartItem` and manages totals, shipping filter, and validation. |
| `Customer`         | Holds customer `name`, `balance`, and their `Cart`. Handles adding/checking out.|
| `ShippingService`  | Static utility class that handles shipping of shippable items.                  |

---

## Design Decisions & Discussions

### Product Types
- All products have a **`weight`** field:
  - `weight > 0` means the product is **shippable**
  - This avoids creating a separate `ShippableProduct` class or interface
- For expirable products, we use an **`ExpirableProduct` subclass**:
  - This allows cleaner expiry logic using `isExpired()` method
  - We avoid bloating the base `Product` class with irrelevant fields

### CartItem Use
- A `CartItem` wraps a `Product` and its **chosen quantity**
- This allows:
  - Tracking quantity per product
  - Computing totals
  - Avoiding duplicate items

### Cart Behavior
- Handles:
  - Adding/updating items
  - Validating available stock
  - Computing subtotal and filtering shippable items
- Duplicate items are merged with quantity validation

### Validation Happens Again at Checkout
Even though we validate when adding items:
- Stock may change before checkout
- Products may expire after being added
- Balance may drop before payment


### ShippingService
- Implemented as a **static method** for simplicity:
  - No state, config, or dependency injection needed
  - Easy to call like a utility
- Only accepts products with `weight > 0`

---

## Error Handling

The system will throw errors for:
- Attempting to checkout with an empty cart
- Insufficient customer balance
- Expired or out-of-stock products

---

