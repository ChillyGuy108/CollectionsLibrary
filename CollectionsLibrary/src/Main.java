import java.util.*;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
     List<String> list=new ArrayList<>(Arrays.asList("apple", "banana", "pear", "croissante", "crab"));
        Predicate<String> startsWithC = s -> s.startsWith("c");
        System.out.println(CollectionUtils.findFirst(list, startsWithC));

    }
}