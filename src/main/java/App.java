import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

public class App {
    public static void main(String[] args) {
        List<Integer> integers1 = IntStream.of(19, 1, 3, -6854, 15, 16, 18, 19, 20, 4, 9, 10).boxed().toList();
        List<Integer> integers2 = IntStream.of(-1, 1, 3, -66585, 15, 16, 18, 19, 20, 4, 9, 1098789).boxed().toList();
        List<Integer> integers3 = IntStream.of(11555, 1, -1, 0, -2, 3, -6640, 15, 16, 18, 19, 20, 4, 9, 1011, 1012, -89899).boxed().toList();
        List<Integer> integers4 = IntStream.of(10001, 1452, 1, -89898, 10000, 15, 16, 18, 19, 21, 4, 9, 22, 10, 10002, 22, 1453, -89899).boxed().toList();

        List<List<Integer>> listsAll = List.of(integers1, integers2, integers3, integers4);

        listsAll.forEach(e -> {
            System.out.println(e);
            List<List<Integer>> lists = getContinuousSequenceLists(e);
            System.out.println(lists);
            System.out.println();
        });
    }

    private static List<List<Integer>> getContinuousSequenceLists(List<Integer> integers) {
        List<Integer> integerList = integers.stream().sorted().distinct().toList();

        List<List<Integer>> listList = new ArrayList<>();

        int i1 = integerList.size() - 1;
        ArrayList<Integer> identity = new ArrayList<>();

        AtomicBoolean isLastAdded = new AtomicBoolean(false);

        ArrayList<Integer> reduce = IntStream.range(0, i1).boxed()
                .reduce(identity, (list, i) -> {
                    list.add(integerList.get(i));
                    if (integerList.get(i) + 1 == integerList.get(i + 1)) {
                        if (i == i1 - 1) {
                            isLastAdded.set(true);
                            list.add(integerList.get(i + 1));
                        }
                        return list;
                    } else {
                        listList.add(list);
                        return new ArrayList<>();
                    }
                }, (list1, list2) -> list2);

        if (isLastAdded.get()) {
            listList.add(reduce);
        }

        return listList;
    }
}