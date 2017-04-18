package by.rudzko.SportMarket;

import by.rudzko.SportMarket.Extra.Strings;

import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * contains a map of available goods, filled by data from file
 *
 * @author Olga Rudzko
 * @see SportEquipment;
 */

public class Shop {

    // key is a SportEquipment, value is an available amount
    private Map<SportEquipment, Integer> goods;

    Map<SportEquipment, Integer> getGoods() {
        return goods;
    }


    /**
     * fills this.goods with data from file
     *
     * @param address contains address of the source file
     * @return filled map
     */
    Map<SportEquipment, Integer> fill(String address) throws FileNotFoundException {

        File f = new File(System.getProperty(Strings.USER_DIR) + Strings.SRC + address);
        Scanner s = null;
        int lineNumber = 0;
        goods = new TreeMap<SportEquipment, Integer>();

        try {
            s = new Scanner(f);

            //defines category
            while (s.hasNextLine()) {

                String line = s.nextLine();
                lineNumber++;       //counts lines for log to define the places of possible mistakes

                try {
                    String[] parts = line.split(Strings.REGEX);
                    Category category = null;
                    switch (parts[0].trim()) {
                        case Strings.TENNIS:
                            category = Category.TENNIS;
                            break;
                        case Strings.FOOTBALL:
                            category = Category.FOOTBALL;
                            break;
                        case Strings.CHESS:
                            category = Category.CHESS;
                            break;
                        case Strings.GYMNASTICS:
                            category = Category.GYMNASTICS;
                            break;
                        case Strings.SWIMMING:
                            category = Category.SWIMMING;
                            break;
                        default:
                            throw new EnumConstantNotPresentException(Category.class, parts[0]);
                    }

                    String title = parts[1].trim();                     //defines good's name
                    double price = Double.valueOf(parts[2].trim());     //defines price
                    int amount = Integer.valueOf(parts[3].trim());      //defines available amount

                    if (amount <= 0 || title.length() < 3) {
                        throw new NullPointerException();
                    }

                    goods.put(new SportEquipment(category, parts[1], price), amount);    //creates a mapping

                } catch (EnumConstantNotPresentException e) {
                    Reporter.getInstance().printProblem(e.enumType() + Strings.SPACE + e.constantName() +
                            Strings.ENUM_NOT_EXIST + lineNumber);

                } catch (IndexOutOfBoundsException e) {
                    Reporter.getInstance().printProblem(Strings.LACK_OF_DATA + lineNumber);

                } catch (NumberFormatException e) {
                    Reporter.getInstance().printProblem(Strings.WRONG_NUMBER_DATA + lineNumber);

                } catch (NullPointerException e) {
                    Reporter.getInstance().printProblem(Strings.NULL_NEGATIVE + lineNumber);
                }

            }
            return goods;

        } finally {
            s.close();  //closes Scanner in order to release the file "Goods.txt" and let the Reporter overwrite it
        }
    }

    /**
     * finds char sequence in goods name and categories in current assortment
     */
    void findInGoods(String str) {
        Iterator<Map.Entry<SportEquipment, Integer>> i = goods.entrySet().iterator();
        int count = 1;
        boolean flag = false;       //indicates whether sequences were found or not

        while (i.hasNext()) {

            Map.Entry<SportEquipment, Integer> pair = i.next();
            SportEquipment seq = pair.getKey();
            String category = seq.getCategory().toString();
            String name = seq.getTitle();
            if (category.contains(str.toUpperCase()) || name.contains(str.toLowerCase())) {
                System.out.println(Integer.toString(count) + Strings.DROP + category + Strings.SEPARATOR2 +
                        name + Strings.CURRENCY + seq.getPrice() + Strings.SEPARATOR + pair.getValue());
                flag = true;
            }
            count++;
        }
        if (!flag) System.out.println(Strings.ITEM_IS_NOT_FOUND);

    }

    @Override
    public String toString() {

        if (goods == null) {
            return Strings.LIST_NOT_CREATED;
        }

        if (goods.isEmpty()) {
            return Strings.LIST_IS_EMPTY;
        }

        StringBuilder sb = new StringBuilder();
        Iterator<Map.Entry<SportEquipment, Integer>> i = goods.entrySet().iterator();
        int count = 1;
        Category cat = Category.TENNIS;

        while (i.hasNext()) {
            Map.Entry<SportEquipment, Integer> pair = i.next();
            Category x;
            if (!((x = pair.getKey().getCategory()) == cat)) {
                sb.append(Strings.PARAGRAPH);
                cat = x;
            }
            sb.append(Integer.toString(count)).append(Strings.DROP).append(pair.getKey()).append(Strings.SEPARATOR).
                    append(pair.getValue()).append(Strings.PIECES);
            count++;
        }

        return sb.toString();
    }
}
