import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/*
入力 マイナス、０を含む整数値の配列
出力 配列に含まれていない最も小さい正の整数
2020/11/14
 */

class Q1__MinNaturalNumberNotContain {
    public static void main(String[] args) {
        int[] sample = {23, 45, 5, 35, 1, 2, 4, 5};
        System.out.println(solution(sample));
    }

    public static int solution(int[] A) {
        List<Integer> arrayList = new ArrayList<>();
        for (int value : A) {
            arrayList.add(value);
        }

        List<Integer> listNotMinus = arrayList.stream().filter(num -> num >= 0).sorted().collect(Collectors.toList());

        int answer = 1;
        if (listNotMinus.isEmpty() || !listNotMinus.contains(1)) {
            return answer;
        }

        answer = -1;
        for (int i = 0; i < listNotMinus.size(); i++) {
            if (!listNotMinus.contains(listNotMinus.get(i) + 1)) {
                answer = listNotMinus.get(i) + 1;
                break;
            }
        }

        if (answer == -1) {
            answer = listNotMinus.get(listNotMinus.size() -1) + 1;
        }

        return answer;
    }
}