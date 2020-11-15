import java.util.ArrayList;
import java.util.List;

/*
入力 正の整数の入った配列
出力 int[0]の値を次のインデックスとして、みたいにLinkedListとして捉えたときのリストの長さ

2020/11/14 ※問題はぼかす
 */

public class Q2__MoveOnList {
    private List<Integer> listA = new ArrayList<>();
    private List<Integer> alreadyShownIndex = new ArrayList<>();

    public int solution(int[] A) {
        for (int value : A) {
            listA.add(value);
        }

        if (moveOnList(0) == -1) {
            return alreadyShownIndex.size();
        } else {
            return listA.size();
        }
    }

    private Integer moveOnList(Integer index) {
        // -1が出たら一番最後の要素とする
        Integer value = listA.get(index) == -1 ? listA.size() -1 : listA.get(index);
        System.out.println("index="+index);
        System.out.println("value="+value);
        if (alreadyShownIndex.contains(value)) {
            return -1;
        }
        alreadyShownIndex.add(value);
        System.out.println(alreadyShownIndex);
        if (alreadyShownIndex.size() == listA.size()) {
            return -2;
        }
        return moveOnList(value);
    }
}

