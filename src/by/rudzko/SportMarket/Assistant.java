package by.rudzko.SportMarket;

import by.rudzko.SportMarket.Extra.Strings;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

/**
 * gets and executes the client's instructions
 *
 * @author Olga Rudzko
 */
public class Assistant {

    private String address = Strings.GOODS_ADDRESS;
    private Map<SportEquipment, Integer> map;
    private Shop s;
    private ArrayList<Integer> choice;

    /**
     * prints a list of good and offers to choose program mode
     */
    void call() {
        s = new Shop();
        try {
            map = s.fill(address);
        } catch (FileNotFoundException e) {
            System.out.println(Strings.MARKET_CLOSED);
            Reporter.getInstance().printProblem(Strings.GOODS_NOT_FOUND + address);
        }
        if (!(map == null)) {

            System.out.println(Strings.GREETING);
            System.out.println(s);
            System.out.println(Strings.CHOOSE_MODE);
            Scanner scan = new Scanner(System.in);
            String input = scan.nextLine();
            if (input.equalsIgnoreCase(Strings.TRY)) {
                clientChoice();
            } else {
                System.out.println(Strings.RANDOM_MODE_ACTIVATED);
                random();
            }
        }
    }

    /**
     * random mode, forms random order
     */
    private void random() {
        choice = new ArrayList<Integer>();
        int size = (int) (Math.random() * 4);
        for (int i = 0; i < size; i++) {
            choice.add((int) (Math.random() * (map.size() + 1)));
        }
        SportEquipment[] readyOrder = finish();
    }

    /**
     * mode of client's choice, forms an order reading instructions from console
     */
    private void clientChoice() {

        System.out.println(Strings.CHOOSE_GOODS);

        Scanner scan = new Scanner(System.in);
        String s;

        //checks input and accepts only one, two or three numbers separated by spaces
        while (!(s = scan.nextLine().trim()).matches(Strings.REGEX_FOR_CHOICE)) {
            System.out.println(Strings.NUMBERS_DEMANDED);
        }
        String[] input = s.split(Strings.REGEX);

        choice = new ArrayList<>();
        for (String value : input) {
            int component = Integer.valueOf(value);
            if (component > map.size() || component == 0) {             //checks numbers of positions
                System.out.println(value + Strings.POSITION_NOT_EXIST);
            } else {
                choice.add(component);
            }
        }

        if (!choice.isEmpty()) {
            SportEquipment[] readyOrder = finish();
        }
    }

    /**
     * completes order, calls Reporter to write it down to the file and to overwrite list of goods
     */
    private SportEquipment[] finish() {

        RentUnit ru = new RentUnit();
        SportEquipment[] order = ru.formOrder(map, choice);
        System.out.println(Strings.ORDER_RECEIVED + ru + Strings.THANK_YOU);
        Reporter.getInstance().printOrder(ru);
        Reporter.getInstance().updateFile(map, address);

        return order;
    }

    /**
     * prints the current assortment and th history of orders to console
     */
    void OrdersAndGoodsToConsole() {

        System.out.println(Strings.CURRENT_LIST + s);
        System.out.println(Strings.HISTORY_OF_ORDERS);

        try {

            Scanner scan = new Scanner(new File(System.getProperty(Strings.USER_DIR) +
                    Strings.SRC + Strings.ORDER_TXT));
            while (scan.hasNextLine()) {
                System.out.println(scan.nextLine());
            }

        } catch (FileNotFoundException e) {
            Reporter.getInstance().printProblem(Strings.ORDERS_NOT_FOUND);
        }
    }

    /**
     * checks input for searching and sends a request to Shop
     */
    void findInMarket() {
        System.out.println(Strings.ENTER_SEARCH);
        Scanner scan = new Scanner(System.in);
        String str;
        while (!(str = scan.nextLine().trim().toLowerCase()).matches("[a-z]{3,6}")) {
            System.out.println(Strings.WRONG_INPUT);
        }
        s.findInGoods(str);
    }
}
