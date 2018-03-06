package com.company;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) throws java.io.IOException {

		Store store = new Store();
		// write your code here
		boolean imStillHere = true;
		ArrayList<Product> vendor1 = new ArrayList<Product>();
		ArrayList<Product> vendor2 = new ArrayList<Product>();
		ArrayList<Product> vendor3 = new ArrayList<Product>();


		List<Product> myList = vendor1.stream().filter(e -> e.getPrice() > 2).collect(Collectors.toList());
		// Create a array list that contains the vendors
		ArrayList<ArrayList<Product>> vendorList = new ArrayList<ArrayList<Product>>();
		vendorList.add(vendor1);
		vendorList.add(vendor2);
		vendorList.add(vendor3);

		Product soap = new Product("Soap", "One bar of soap", 1.50d, 2);
		Product broom = new Product("Broom", "One bristle broom", 9.50d, 0);
		Product bucket = new Product("Bucket", "One metal bucket", 12d, 7);
		Product cup = new Product("Cup", "10 pack of cups", 4d, 0);
		Product phone = new Product("Phone", "one landline phone", 40d, 0);
		Product[] productList1 = {soap, broom, bucket, phone, cup};


		for (Product product : productList1) {
				vendor1.add(product);
				System.out.println(product.toString());
				store.setProductAvail(product.getName(), product.getQty());
		}

		Product marker = new Product("Marker", "Magic Marker", 3d, 0);
		Product pen = new Product("Pen", "12 ball point pens", 6.5d, 3);
		Product pencil = new Product("Pencil", "number 2 pencil, 10 pack", 5.45d, 0);
		Product table = new Product("Table", "one folding table", 45.5d, 0);
		Product chair = new Product("Chair", "one folding chair", 19.99d, 0);
		Product[] productList2 = {marker, pen, pencil, table, chair};
		for (Product product : productList2) {
				vendor2.add(product);
				System.out.println(product.toString());
				store.setProductAvail(product.getName(), product.getQty());
		}
		Product shirt = new Product("Shirt", "button up shirt", 7.49d, 0);
		Product pants = new Product("Pants", "old Dockers", 8.24d, 7);
		Product belt = new Product("Belt", "brown leather belt", 4.87d, 0);
		Product shoes = new Product("Shoes", "beat up sperrys", 8d, 0);
		Product hat = new Product("Hat", "90s revival bucket hat", 6.45d, 0);
		Product[] productList3 = {shirt, pants, belt, shoes, hat};
		for (Product product : productList3) {
				vendor3.add(product);
				System.out.println(product.toString());
				store.setProductAvail(product.getName(), product.getQty());
		}

		Scanner keyboard = new Scanner(System.in);


		ArrayList<Product> cart = new ArrayList<Product>();

		while (imStillHere) {
			System.out.print("Cart options:" + "\n" +
					"1 to view contents of the cart" + "\n" +
					"2 to add product to your cart" + "\n" +
					"3 to remove product from your cart" + "\n" +
					"4 to checkout" + "\n");

			// try to run a block a code that an exception may occur due to a user invalid input
			try {
				int userInput = keyboard.nextInt();
				switch (userInput) {
					case 1:
						viewCart(cart);
						break;
					case 2:
						String secondInput = keyboard.next();

						// loop through all the vendor products and check if user input match
						for (ArrayList<Product> vendor : vendorList) {
							for (Product product : vendor) {
								if (secondInput.equalsIgnoreCase(product.getName())) {
									if (store.getItemInventory(product.getName()) == 0) {
										System.out.println(product.getName() + " is currently out of stock.");
									} else {
										addToCart(product, cart);
										store.decreaseInventory(product);
										//product.decrementStock(); Inventory is now removed from the store
									}
								}
							}
						}

						break;
					case 3:
						String thirdInput = keyboard.next();
						removeFromCart(thirdInput, cart, store);
						break;
					case 4:
						keyboard.close();
						checkout(cart);
						imStillHere = false;
						break;
					default:
						System.out.println("Invalid input");
						break;
				}
			}
			// Block of code is triggered when User input was invalid and set off an InputMismatch Exception
			catch (InputMismatchException e) {
				System.out.println("Invalid Input\nPlease select from the provided options");
				System.out.println();
				keyboard.next(); // steps over the invalid input in the Scanner stream
				// and prevents the program from going into an infinite loop
			}
		}
	}

	public static void addToCart(Product product, ArrayList<Product> cart) {
		cart.add(product);
	}

	public static void viewCart(ArrayList<Product> cart)  {
		// Check whats in the final cart
		System.out.println("\n" + "****ITEMS IN CART****" + "\n");
		String fileOutput = "";
		for (Product product : cart) {
		    fileOutput += String.format("Item: " + product.getName() + "\n" + "Price: $%.2f \n" + "------------", product.getPrice());
			System.out.format("Item: " + product.getName() + "\n" + "Price: $%.2f \n" + "------------", product.getPrice());
		}
	}

	public static void removeFromCart(String thirdInput, ArrayList<Product> cart, Store store) {
		for (Product product : cart) {
			if (thirdInput.equalsIgnoreCase(product.getName())) {
				cart.remove(product);
				store.increaseInventory(product);
				break;
			}
		}
	}

	public static void checkout(ArrayList<Product> cart) throws java.io.IOException  {
		// Check whats in the final cart
		System.out.println("\n" + "****ITEMS IN CART****" + "\n");
		double total = 0;
		String fileOutput = "";
		for (Product product : cart) {
		    fileOutput += String.format("Item: " + product.getName() + "\n" + "Price: %.2f\n" + "------------", product.getPrice());
			System.out.format("Item: " + product.getName() + "\n" + "Price: %.2f\n" + "------------", product.getPrice());
			total += product.getPrice();
		}
        fileOutput += String.format("%.2f", total);
        try (FileWriter writer = new FileWriter("receipt.txt")) {
            writer.write(fileOutput);
        }

		System.out.format("YOUR TOTAL IS: $%.2f", total);
	}
}
