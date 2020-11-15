import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
入力 正の整数
出力 順列の組み合わせの数　ただし、頭に0が来るのはノーカン

2020/11/14 ※問題はぼかす
 */

public class Q3__Permutation {
    public int solution(int N) {
        String stringN = String.valueOf(N);
        int howManyDigits = stringN.length();

        // Stringを区切ってlistに入れる
        List<String> stringNArray = new ArrayList<>();
        for (int i = 0; i < stringN.length(); i++) {
            stringNArray.add(stringN.substring(i, i + 1));
        }

        HashMap<String, Integer> numberCounts = new HashMap<>();
        // キーがなければ１を入れる　すでにあれば値を更新する
        for (String c : stringNArray) {
            if (!numberCounts.containsKey(c)) {
                numberCounts.put(c, 1);
            } else {
                numberCounts.put(c, numberCounts.get(c) + 1);
            }
        }

        // 分子 全体の数
        int numerator = factorial(howManyDigits);
        // 分母 総数分の内重複分の組み合わせを省く
        int denominator = 1;
        for (Integer value : numberCounts.values()) {
            if (value > 1) {
                denominator *= factorial(value);
            }
        }

        if (numberCounts.containsKey("0") && N != 0) {
            return numerator / denominator - numberCounts.get("0");
        }

        return numerator / denominator;
    }

    // 順列計算
    private int factorial(int n){
        if (n == 0) {
            return 1;
        }
        return n * factorial(n-1);
    }
}
