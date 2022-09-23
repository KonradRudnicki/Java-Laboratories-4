import java.util.Collections;

public class Test {
    public static void main(String[] args) {
        TwoWayCycledOrderedListWithSentinel<String> list = new TwoWayCycledOrderedListWithSentinel<>();
        TwoWayCycledOrderedListWithSentinel<String> listToAdd = new TwoWayCycledOrderedListWithSentinel<>();

        list.add("a");
        list.add("b");
        list.add("c");
        list.add("f");


        listToAdd.add("d");
        listToAdd.add("e");
        listToAdd.add("f");

        list.add(listToAdd);
        System.out.println("----");
        System.out.println(list);
        System.out.println("----");
        System.out.println(list.toStringReverse());
        System.out.println(list.size);

    }
}