/**
 * Класс Tree представляет собой реализацию левостороннего красно-чёрного дерева.
 * Каждая нода имеет цвет (красный или черный).
 * Корень дерева всегда черный.
 * Новая нода всегда красная.
 * Красные ноды могут быть только левым дочерним элементом.
 * У красной ноды все дочерние элементы черного цвета.
 */
public class Tree {
    Node root;

    /**
     * Класс Node представляет узел дерева.
     * Узел содержит значение (value), ссылки на левого и правого потомков (left и right) и цвет (color).
     */
    static class Node {
        int value;
        Node left;
        Node right;
        Color color;
    }

    /**
     * Перечисление Color представляет возможные цвета узлов дерева (красный и черный).
     */
    enum Color {
        BLACK,
        RED
    }

    /**
     * Вставляет новый элемент в дерево с балансировкой.
     *
     * @param value значение, которое необходимо добавить в дерево.
     */
    public void insert(int value) {
        root = insertNode(root, value);
        root.color = Color.BLACK;
    }

    /**
     * Вспомогательный метод для вставки нового элемента в дерево.
     *
     * @param node  текущий узел, с которым происходит сравнение значения для вставки.
     * @param value значение, которое необходимо добавить в дерево.
     * @return новый узел, который был добавлен в дерево.
     */
    private Node insertNode(Node node, int value) {
        if (node == null) {
            Node newNode = new Node();
            newNode.value = value;
            newNode.color = Color.RED;
            return newNode;
        }

        if (value == node.value) {
            return node;
        } else if (value < node.value) {
            node.left = insertNode(node.left, value);
        } else {
            node.right = insertNode(node.right, value);
        }

        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }

        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }

        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        return node;
    }

    /**
     * Ищет узел с заданным значением в дереве.
     *
     * @param value значение, которое необходимо найти.
     * @return узел с заданным значением или null, если такого узла нет в дереве.
     */
    public Node find(int value) {
        return findNode(root, value);
    }

    /**
     * Вспомогательный метод для поиска узла с заданным значением в дереве.
     *
     * @param node  текущий узел, с которым происходит сравнение значения для поиска.
     * @param value значение, которое необходимо найти.
     * @return узел с заданным значением или null, если такого узла нет в дереве.
     */
    private Node findNode(Node node, int value) {
        if (node == null || node.value == value) {
            return node;
        }

        if (value < node.value) {
            return findNode(node.left, value);
        } else {
            return findNode(node.right, value);
        }
    }

    /**
     * Проверяет, является ли узел красным.
     *
     * @param node узел, который необходимо проверить.
     * @return true, если узел красный, иначе false.
     */
    private boolean isRed(Node node) {
        return node != null && node.color == Color.RED;
    }

    /**
     * Выполняет левый поворот вокруг заданного узла.
     *
     * @param node узел, вокруг которого выполняется поворот.
     * @return новый корневой узел после поворота.
     */
    private Node rotateLeft(Node node) {
        Node newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;
        newRoot.color = node.color;
        node.color = Color.RED;
        return newRoot;
    }

    /**
     * Выполняет правый поворот вокруг заданного узла.
     *
     * @param node узел, вокруг которого выполняется поворот.
     * @return новый корневой узел после поворота.
     */
    private Node rotateRight(Node node) {
        Node newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;
        newRoot.color = node.color;
        node.color = Color.RED;
        return newRoot;
    }

    /**
     * Переключает цвета узлов.
     *
     * @param node узел, цвет которого необходимо переключить.
     */
    private void flipColors(Node node) {
        node.color = (node.color == Color.RED) ? Color.BLACK : Color.RED;
        node.left.color = Color.BLACK;
        node.right.color = Color.BLACK;
    }
}