package com.example.beadsproject;

import java.util.ArrayList;
import java.util.List;

public class JewelryItem extends Beads {

    private List<Beads> beadsList;
    private String shape;
    private String color;
    private String type;
    private float size;

    public JewelryItem(String shape, String color, String type,float size)
    {
        super(shape,color,type,size);
        beadsList = new ArrayList<>();
    }

    public List<Beads> getBeads() {return beadsList;}

    public void addBead(Beads bead) {
        beadsList.add(bead);
    }



//    public double calculateTotalPrice() {
//        double total = 0;
//        for (Beads bead : beadsList) {
//            total += bead.getPrice();
//        }
//        return total;
//    }



    public double calculateTotalPrice() {
        if (size <= 10) return 5.00;
        else if (size <= 30) return 25.00;
        else return 45.00;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Beads bead : beadsList) {
            sb.append(bead.toString()).append("\n\n");
        }
        sb.append("Total Jewelry Price: $").append(calculateTotalPrice());
        return sb.toString();
    }
}// end of class