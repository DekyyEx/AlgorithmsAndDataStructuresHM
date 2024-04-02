/**
 * Класс Basket представляет корзину, которая содержит связный список узлов.
 */
class Basket {
    /**
     * Головной узел списка.
     */
    Node head;

    /**
     * Класс Node представляет узел в связном списке.
     */
    class Node {
        Entity entity;
        Node next;
    }

    /**
     * Метод добавляет новую сущность в корзину.
     *
     * @param entity сущность для добавления.
     * @return true, если сущность успешно добавлена, false, если сущность с таким ключом уже существует в корзине.
     */
    boolean push(Entity entity) {
        Node node = new Node();
        node.entity = entity;
        Node cur = head;
        while (cur != null) {
            if (cur.entity.key == entity.key) {
                return false;
            }
            cur = cur.next;
        }
        node.next = head;
        head = node;
        return true;
    }

    /**
     * Метод удаляет сущность с заданным ключом из корзины.
     *
     * @param key ключ сущности, которую необходимо удалить.
     * @return true, если сущность успешно удалена, false, если сущность с таким ключом не найдена в корзине.
     */
    boolean remove(int key) {
        if (head != null) {
            if (head.entity.key == key) {
                head = head.next;
                return true;
            } else {
                Node cur = head;
                while (cur.next != null) {
                    if (cur.next.entity.key == key) {
                        cur.next = cur.next.next;
                        return true;
                    }
                    cur = cur.next;
                }
            }
        }
        return false;
    }

    /**
     * Метод ищет сущность с заданным ключом в корзине и возвращает её значение.
     *
     * @param key ключ сущности, которую необходимо найти.
     * @return значение сущности, если она найдена, null, если сущность с таким ключом не найдена в корзине.
     */
    Integer find(int key) {
        Node cur = head;
        while (cur != null) {
            if (cur.entity.key == key) {
                return cur.entity.value;
            }
            cur = cur.next;
        }
        return null;
    }
}

/**
 * Класс Entity представляет сущность, которая содержит ключ и значение.
 */
class Entity {
    int key;
    int value;
}

public class HashMap {
    static final int INIT_SIZE = 16;
    Basket[] baskets;

    /**
     * Конструктор класса HashMap.
     */
    public HashMap() {
        this(INIT_SIZE);
    }

    /**
     * Конструктор класса HashMap с заданным размером корзин.
     *
     * @param size размер корзин.
     */
    public HashMap(int size) {
        baskets = new Basket[size];
    }

    /**
     * Метод получает индекс корзины для заданного ключа.
     *
     * @param key ключ.
     * @return индекс корзины.
     */
    private int getIndex(int key) {
        return key % baskets.length;
    }

    /**
     * Метод добавляет новую сущность с заданным ключом и значением в хэш-таблицу.
     *
     * @param key   ключ сущности.
     * @param value значение сущности.
     * @return true, если сущность успешно добавлена, false, если сущность с таким ключом уже существует в хэш-таблице.
     */
    public boolean push(int key, int value) {
        int index = getIndex(key);
        if (baskets[index] == null) {
            baskets[index] = new Basket();
        }
        Entity entity = new Entity();
        entity.key = key;
        entity.value = value;
        return baskets[index].push(entity);
    }

    /**
     * Метод удаляет сущность с заданным ключом из хэш-таблицы.
     *
     * @param key ключ сущности, которую необходимо удалить.
     * @return true, если сущность успешно удалена, false, еслисущность с таким ключом не найдена в хэш-таблице.
     */
    public boolean remove(int key) {
        int index = getIndex(key);
        if (baskets[index] != null) {
            return baskets[index].remove(key);
        }
        return false;
    }

    /**
     * Метод ищет сущность с заданным ключом в хэш-таблице и возвращает её значение.
     *
     * @param key ключ сущности, которую необходимо найти.
     * @return значение сущности, если она найдена, null, если сущность с таким ключом не найдена в хэш-таблице.
     */
    public Integer find(int key) {
        int index = getIndex(key);
        if (baskets[index] != null) {
            return baskets[index].find(key);
        }
        return null;
    }
}