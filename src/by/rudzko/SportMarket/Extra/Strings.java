package by.rudzko.SportMarket.Extra;

public interface Strings {

    //Patterns and formatters
    String REGEX_FOR_CHOICE = "[0-9]{1,2}(\\p{Blank}+[0-9]{1,2}){0,2}";
    String REGEX = "\\p{Blank}+";
    String SEPARATOR = "\t/";
    String TAB = "\t";
    String SEPARATOR2 = ":\t";
    String DROP = ". ";
    String PARAGRAPH = "\n";
    String SPACE = " ";
    String CURRENCY = ", $";
    String TRY = "TRY";
    String PIECES = " pieces\n";
    String COST = "___________________\nTOTAL: $";

    //Paths and parts of them
    String USER_DIR = "user.dir";
    String SRC = "/src/";
    String ORDER_TXT = "by/rudzko/SportMarket/Extra/Orders.txt/";
    String GOODS_ADDRESS = "by/rudzko/SportMarket/Extra/Goods.txt/";
    String LOG_TXT = "by/rudzko/SportMarket/Extra/Log.txt/";

    //Categories
    String TENNIS = "TEN";
    String CHESS = "CHE";
    String SWIMMING = "SWI";
    String GYMNASTICS = "GYM";
    String FOOTBALL = "FOO";

    //Communication with a client
    String GREETING = "Hello, dear friend! Today we can offer you:\n";
    String MARKET_CLOSED = "Unfortunately, market is currently unavailable. Please, try later.";
    String CHOOSE_MODE = "Press Enter for random mode or type TRY to choose goods.";
    String CHOOSE_GOODS = "Please choose not more than three goods by typing appropriate numbers separated with spaces.";
    String NUMBERS_DEMANDED = "One, two or three numbers separated by spaces are demanded!";
    String POSITION_NOT_EXIST = " position doesn't exist. It was excluded from your order.";
    String OUT_OF_STOCK = " out of stock.";
    String WRONG_INPUT = "Wrong input. 3-6 letters allowed.";
    String RANDOM_MODE_ACTIVATED = "Random mode activated.\n";
    String ORDER_RECEIVED = "We received your order:\n";
    String THANK_YOU = "\nThank you for choosing us!";
    String CURRENT_LIST = "Current list of available goods:\n";
    String HISTORY_OF_ORDERS = "History of orders:\n";
    String ENTER_SEARCH = "Enter an single item you are looking for. 3-6 letters allowed.";
    String ITEM_IS_NOT_FOUND = "Item is not found.";

    //Problems
    String ENUM_NOT_EXIST = " doesn't exist. GOODS file, line ";
    String LACK_OF_DATA = "Lack of data in GOODS file, line ";
    String WRONG_NUMBER_DATA = "Wrong number data in GOODS file, line ";
    String FILE_LOG_NOT_FOUND = "File LOG is not found!";
    String LOGGING_FAILED = "Attention! Logging failed! ";
    String NULL_NEGATIVE = "Negative or null amount or too short name in line ";
    String GOODS_NOT_FOUND = "List of goods is not found at ";
    String ORDER_FAILED = "Attention! Order writing failed!";
    String LIST_NOT_CREATED = "List of goods is not created.";
    String LIST_IS_EMPTY = "List of goods is empty.";
    String NO_ORDER = "No order.";
    String ASSORTMENT_NOT_REFRESHED = "Assortment wasn't refreshed!";
    String ORDERS_NOT_FOUND = "File ORDERS is not found.";
}
