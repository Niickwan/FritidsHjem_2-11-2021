package com.company;

public class Main {

    public static void main(String[] args) {
        VareData vareData = new VareData();
        VareData[] vareArr = new VareData[5];
        vareData.getOrdersFromFile(vareArr);
        vareData.printOrders(vareArr);
        vareData.writeToSerFile(vareArr);
        vareData.priceWithAndWithoutDiscord(vareArr);
        vareData.allOrders(vareArr);
        vareData.completeOrder(vareArr);
    }
}
