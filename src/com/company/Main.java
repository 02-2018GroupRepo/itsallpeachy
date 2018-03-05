package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// write your code here
		boolean imStillHere = true;
		ArrayList<Product> vendor1 = new ArrayList<Product>();
		ArrayList<Product> vendor2 = new ArrayList<Product>();
		ArrayList<Product> vendor3 = new ArrayList<Product>();

		// Create a array list that contains the vendors
		ArrayList<ArrayList<Product>> vendorList = new ArrayList<ArrayList<Product>>();
		vendorList.add(vendor1);
		vendorList.add(vendor2);
		vendorList.add(vendor3);

		Product soap = new Product("Soap", "One bar of soap", 1.50d, 2);
		Product broom = new Product("Broom", "One bristle broom", 9.50d);
		Product bucket = new Product("Bucket", "One metal bucket", 12d, 7);
		Product cup = new Product("Cup", "10 pack of cups", 4d);
		Product phone = new Product("Phone", "one landline phone", 40d);
		Product[] productList1 = {soap, broom, bucket, phone, cup};

		for (Product product : productList1) {
			if (product.getQty() > 0) {
				vendor1.add(product);
				System.out.println(product.toString());
			}
		}

		Product marker = new Product("Marker", "Magic Marker", 3d);
		Product pen = new Product("Pen", "12 ball point pens", 6.5d, 3);
		Product pencil = new Product("Pencil", "number 2 pencil, 10 pack", 5.45d);
		Product table = new Product("Table", "one folding table", 45.5d);
		Product chair = new Product("Chair", "one folding chair", 19.99d);
		Product[] productList2 = {marker, pen, pencil, table, chair};
		for (Product product : productList2) {
			if (product.getQty() > 0) {
				vendor2.add(product);
				System.out.println(product.toString());
			}
		}
		Product shirt = new Product("Shirt", "button up shirt", 7.49d);
		Product pants = new Product("Pants", "old Dockers", 8.24d, 7);
		Product belt = new Product("Belt", "brown leather belt", 4.87d);
		Product shoes = new Product("Shoes", "beat up sperrys", 8d);
		Product hat = new Product("Hat", "90s revival bucket hat", 6.45d);
		Product[] productList3 = {shirt, pants, belt, shoes, hat};
		for (Product product : productList3) {
			if (product.getQty() > 0) {
				vendor3.add(product);
				System.out.println(product.toString());
			}

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
									addToCart(product, cart);
									product.decrementStock();
								}
							}
						}

						break;
					case 3:
						String thirdInput = keyboard.next();
						removeFromCart(thirdInput, cart);
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

	public static void viewCart(ArrayList<Product> cart) {
		// Check whats in the final cart
		System.out.println("\n" + "****ITEMS IN CART****" + "\n");
		for (Product product : cart) {
			System.out.println("Item: " + product.getName() + "\n" + "Price: " + product.getPrice() + "\n" + "------------");
		}
	}

	public static void removeFromCart(String thirdInput, ArrayList<Product> cart) {
		for (Product product : cart) {
			if (thirdInput.equalsIgnoreCase(product.getName())) {
				cart.remove(product);
				break;
			}
		}
	}

	public static void checkout(ArrayList<Product> cart) {
		// Check whats in the final cart
		System.out.println("\n" + "****ITEMS IN CART****" + "\n");
		double total = 0;
		for (Product product : cart) {
			System.out.println("Item: " + product.getName() + "\n" + "Price: " + product.getPrice() + "\n" + "------------");
			total += product.getPrice();
		}
		System.out.println("YOUR TOTAL IS: " + total);
	}
}
