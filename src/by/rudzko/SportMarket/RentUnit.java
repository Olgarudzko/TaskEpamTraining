package by.rudzko.SportMarket;

import by.rudzko.SportMarket.Extra.Strings;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * contains the order of the client
 *
 * @author Olga Rudzko
 * @see SportEquipment;
 */

public class RentUnit {

    private SportEquipment[] units;

    public SportEquipment[] getUnits() {
        return units;
    }

    /**
     * @param all    contains the map of available goods
     * @param choice contains numbers of chosen goods positions
     * @return the array containing the order
     */
    SportEquipment[] formOrder(Map<SportEquipment, Integer> all, ArrayList<Integer> choice) {

        units = new SportEquipment[choice.size()];

        for (int y = 0; y < choice.size(); y++) {

            int count = 1;  //a number of current iterator's position
            Iterator<Map.Entry<SportEquipment, Integer>> i = all.entrySet().iterator();

            while (i.hasNext()) {
                Map.Entry<SportEquipment, Integer> pair = i.next();

                if (choice.get(y) == count) {
                    SportEquipment eq = pair.getKey();
                    units[y] = eq;
                    if (pair.getValue() > 0) {
                        pair.setValue(pair.getValue() - 1); //decrease an amount of available goods
                    } else {
                        eq.setPrice(-1);  //flag for creating a notification about good's absence
                    }
                }
                count++;
            }
        }
        return units;
    }

    @Override
    public String toString() {

        if (units == null) {
            return Strings.NO_ORDER;
        }

        StringBuilder sb = new StringBuilder();
        double total = 0;

        for (SportEquipment val : units) {
            double cost = val.getPrice();
            if (cost >= 0) {
                sb.append(val.toString()).append(Strings.PARAGRAPH);
                total += cost;
            } else {
                String outOfStock = val.getCategory() + Strings.SPACE + val.getTitle() + Strings.OUT_OF_STOCK;
                sb.append(outOfStock).append(Strings.PARAGRAPH);    //informs the client about good's absence
                Reporter.getInstance().printProblem(outOfStock);    //makes a note about good's absence to the log
            }
        }

        sb.append(Strings.COST).append(total).append(Strings.PARAGRAPH); //prints total cost
        return sb.toString();
    }
}
