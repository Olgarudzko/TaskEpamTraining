package by.rudzko.SportMarket;

import by.rudzko.SportMarket.Extra.Strings;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/**
 * singleton for writing problems and orders down to files;
 * overwrites the source list of goods with current assortment
 *
 * @author Olga Rudzko
 */
class Reporter {

    private static Reporter reporter;

    private String address = System.getProperty(Strings.USER_DIR) + Strings.SRC;
    private PrintWriter n = null;

    static Reporter getInstance() {
        if (reporter == null)
            reporter = new Reporter();

        return reporter;
    }

    private Reporter() {
    }

    /**
     * writes problem down to the file
     *
     * @param problem description of problem
     */
    void printProblem(String problem) {

        File f = new File(address + Strings.LOG_TXT);

        try {
            n = new PrintWriter(new FileWriter(f, true));
            n.println(getTime() + Strings.SEPARATOR2 + problem);
            n.flush();

        } catch (FileNotFoundException e) {
            System.out.println(Strings.FILE_LOG_NOT_FOUND);

        } catch (IOException e) {
            System.out.println(Strings.LOGGING_FAILED + e.getLocalizedMessage());

        } finally {
            if (n != null)
                n.close();
        }
    }

    /**
     * writes order down to the file
     *
     * @param order the order to write
     */
    void printOrder(RentUnit order) {

        File f = new File(address + Strings.ORDER_TXT);

        try {
            n = new PrintWriter(new FileWriter(f, true));
            n.println(getTime() + Strings.PARAGRAPH + order);
            n.flush();

        } catch (FileNotFoundException e) {
            System.out.println(Strings.FILE_LOG_NOT_FOUND);

        } catch (IOException e) {
            System.out.println(Strings.ORDER_FAILED + e.getLocalizedMessage());

        } finally {
            if (n != null)
                n.close();
        }
    }

    /**
     * overwrites source file of available goods after completing an order
     *
     * @param map current map of available goods
     * @param ref reference to the overwriting file
     */
    void updateFile(Map<SportEquipment, Integer> map, String ref) {

        File f = new File(address + ref);
        Path path = f.toPath();

        PrintWriter n = null;
        try {
            Files.deleteIfExists(path);
            Files.createFile(path);

            n = new PrintWriter(new FileWriter(f, true));

            Iterator<Map.Entry<SportEquipment, Integer>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<SportEquipment, Integer> mapping = it.next();
                SportEquipment seq = mapping.getKey();
                n.println(seq.getCategory().toString().substring(0, 3) + Strings.SPACE + seq.getTitle() + Strings.TAB +
                        seq.getPrice() + Strings.SPACE + mapping.getValue());
                n.flush();
            }


        } catch (FileNotFoundException e) {
            System.out.println(Strings.GOODS_NOT_FOUND + address);

        } catch (IOException e) {
            System.out.println(Strings.ASSORTMENT_NOT_REFRESHED + e.getLocalizedMessage());

        } finally {
            if (n != null)
                n.close();
        }
    }

    /**
     * gets date and time of report creating
     *
     * @return the view of current date and time
     */
    private String getTime() {

        Date d = new Date(System.currentTimeMillis());
        DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.SHORT);
        return df.format(d);
    }
}
