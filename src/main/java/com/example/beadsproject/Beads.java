package com.example.beadsproject;

public class Beads {
    private String shape;
    private String color;
    private String type;
    private float size;
    private double price; // price based on size

    public Beads(String shape, String color, String type, float size) {
        this.shape = shape;
        this.color = color;
        this.type = type;
        this.size = size;
        this.price = calculatePrice();
    }

    public double calculatePrice() {
        if (size >= 1 && size <= 10) {
            return 5.00;
        } else if (size >= 11 && size <= 30) {
            return 25.00;
        } else {
            return 45.00;
        }
    }

    public void setShape(String shape) {this.shape = shape;}
    public void setColor(String color) {this.color = color;}
    public void setType(String type) {this.type = type;}
    public void setSize(float size) {this.size = size;}
    public void setPrice(double price) {this.price = price;}


    public String getShape() { return shape; }
    public String getColor() { return color; }
    public String getType() { return type; }
    public float getSize() { return size; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return "Bead Shape: " + shape + "\nBead Color: " + color + "\nBead Type: " + type +
                "\nBead Size: " + size + " cm\nBead Price: $" + price;
    }




}// end of class