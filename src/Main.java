import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.toString());

    public static void main(String[] args) {
        //  Tree tree = new Tree();

        // for (int i = 0; i <= 5; i++)
        //   tree.insert(i);
        //
        // logger.info(String.valueOf(tree.find(5)));
        // logger.info(String.valueOf(tree.find(6)));

        HashMap map = new HashMap();
        map.push(1, 2);
        map.push(3, 4);

        logger.info(String.valueOf(map.find(1)));
        logger.info(String.valueOf(map.find(3)));

        map.remove(1);
        map.push(2, 5);

        logger.info(String.valueOf(map.find(1))); // Ожидается null, так как сущность с ключом 1 была удалена
        logger.info(String.valueOf(map.find(2)));
    }
}