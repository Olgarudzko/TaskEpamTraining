package by.rudzko.SportMarket;

/**
 * SportMarket with the opportunity for clients to rent the equipment,
 * forms orders in two modes: client's choice(reads instructions from console) and random,
 * reads list of available goods from the file Extra.Goods.txt, writes reports about orders to the file Extra.Orders.txt,
 * overwrites the file Extra.Goods.txt after completing the order with refreshed assortment, writes errors to Extra.Log.txt
 *
 * @author Olga Rudzko
 */
public class Runner {

    public static void main(String[] args) {

        Assistant assistant=new Assistant();
        assistant.call();

        //next line will print the refreshed data from Goods.txt and Orders.txt to console
        //assistant.OrdersAndGoodsToConsole();

        //next line will execute the search for chars sequence through the current list of goods
        //assistant.findInMarket();
    }
}
