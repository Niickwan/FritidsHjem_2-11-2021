package com.company;

import java.io.*;
import java.util.Scanner;

public class VareData implements Serializable {
    private int stk;
    private String vare;
    private float pris;

    public VareData() {
    }

    public VareData(int stk, String vare, float pris) {
        this.stk = stk;
        this.vare = vare;
        this.pris = pris;
    }

    public void getOrdersFromFile(VareData[] vareArr) {
        try {
            File orders = new File("Bestilling.txt");
            Scanner myReader = new Scanner(orders);
            for (int i = 0; i < 5; i++) {
                VareData order = new VareData(myReader.nextInt(), myReader.next(), myReader.nextFloat());
                vareArr[i] = order;
            }
            myReader.close();
            //printOrders(vareArr);
        } catch (IOException e) {
            System.err.println("Error");
            e.printStackTrace();
        }
    }

    public void writeToSerFile(VareData[] vareArr) {
        try {
            FileOutputStream fos = new FileOutputStream("varer.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // Write object to file
            for (VareData vare : vareArr) {
                oos.writeObject(vare);
                System.out.println("Order successfully added!");
            }
            oos.close();
            fos.close();
        } catch (IOException e) {
            System.err.println("Error");
            e.printStackTrace();
        }

    }

    public void printOrders(VareData[] order) {
        for (VareData data : order) {
            System.out.println(data.stk + " " + data.vare + " " + data.pris);
        }
    }

    public void priceWithAndWithoutDiscord(VareData[] arr) {
        for (VareData data : arr) {
            if (data.stk > 10) {
                System.out.printf("%s Pris med rabat : %2.2f\n", data.vare, data.pris*0.85);
            } else {
                System.out.printf("%s Pris uden rabat: %2.2f\n", data.vare, data.pris);
            }
        }
    }

    public void allOrders(VareData[] arr) {
        for (VareData data : arr) {
            if (data.stk > 10) {
                System.out.printf("Med rabat  | Vare: %s, %d stk, a %2.2f stykket = %2.2f kr i alt.\n", data.vare, data.stk, data.pris * 0.85, (data.pris * data.stk) * 0.85);
            } else {
                System.out.printf("Uden rabat | Vare: %s, %d stk, a %2.2f stykket = %2.2f kr i alt.\n", data.vare, data.stk, data.pris, data.pris * data.stk);
            }
        }
    }

    public void completeOrder(VareData[] arr) {
        try {
            File myFile = new File("faktura.txt");
            if (myFile.createNewFile()) {
                System.out.println("File created!");
            } else {
                System.out.println("File Already exists!");
            }
            BufferedWriter bufWrite = new BufferedWriter(new FileWriter("faktura.txt", false));
            bufWrite.write("FAKTURA\n");
            bufWrite.write("---------------------------------------\n");
            bufWrite.write("Dit køb:\n");
            for (VareData data : arr) {
                if (data.stk > 10) {
                    bufWrite.write(data.stk + " stk " + data.vare + " a " + data.pris + " kr stykket = Uden rabat: " + data.stk* data.pris + " Med Rabat: " + (data.pris*data.stk)*0.85 + "\n");
                } else {
                    bufWrite.write(data.stk + " stk " + data.vare + " a " + data.pris + " kr stykket = Uden rabat: " + data.pris*data.stk + "\n");
                }
            }
            bufWrite.write("---------------------------------------\n");
            bufWrite.write("Tak for dit køb!\n");
            bufWrite.write("---------------------------------------\n");
            bufWrite.close();
        } catch (IOException e) {
            System.err.println("Error");
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "VareData{" +
                "stk=" + stk +
                ", vare='" + vare + '\'' +
                ", pris=" + pris +
                '}';
    }
}
