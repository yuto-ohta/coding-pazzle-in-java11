import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
入力 ---
出力 ---

配列の高さをスカイラインのように絵に書いたとき、それに色を塗る最小回数を算出する
2020/11/22 ※問題はぼかす
 */

public class Q5__Skyline {
    public int solution(int[] A) {
        List<Integer> listA = new ArrayList<>();
        for (int value : A) {
            listA.add(value);
        }

        int MaxHeight = Collections.max(listA);
        int strokeCounter = 0;

        // 各行の塗り回数をカウント
        for (int i = 0; i < MaxHeight; i++) {
            int rowHeight = i + 1;
            boolean isSameArea = false;
            boolean lastTimeIsSameArea = false;

            // リストAの各要素
            for (int j = 0; j < listA.size(); j++) {
                if (j != 0) {
                    lastTimeIsSameArea = listA.get(j - 1) >= rowHeight;
                }
                isSameArea = listA.get(j) >= rowHeight;

                // 前の要素番目に高さがあり、今届かなくなったときに１塗り
                if (lastTimeIsSameArea && !isSameArea) {
                    strokeCounter ++;
                }

                // 今届いていて、そのまま最後番目まで行けば１塗り
                if (isSameArea && j == listA.size() - 1) {
                    strokeCounter ++;
                }
            }
        }
        return strokeCounter;
    }
}
