import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/*
入力 ---
出力 ---

再帰処理の中でAPI叩いて計算する。APIのコール制限あるので、計算を最適化しないといけないけど、いまいまはよくわからない
try-with-resourceに切り替えて、あとJSONを扱うようにしたい
2020/11/15 ※問題はぼかす
 */

public class Q4__CallApi__ComplexCalculate {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String seed = sc.next();
        System.out.println(seed);
        int N = sc.nextInt();
        System.out.println(f(seed, N));
    }

    private static int f(String seed, int N) {
        if (N == 0) {
            return 1;
        } else if (N == 2) {
            return 2;
        } else if (N % 2 == 0) {
            return f(seed, (N - 2)) + f(seed, (N - 2)) + f(seed, (N - 3)) + f(seed, (N - 4));
        } else {
            return callApi(seed, N);
        }
    }

    private static int callApi(String seed, int N) {
        String strUrl = String.format("http://xxx", String.valueOf(N), seed);
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        BufferedReader reader = null;
        String outputString = "";

        try {
            URL url = new URL(strUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            int status = urlConnection.getResponseCode();
            if (status == HttpURLConnection.HTTP_OK) {
                inputStream = urlConnection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder output = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    output.append(line);
                }
                outputString = output.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String answerStr = outputString.substring(outputString.indexOf("\"result\":")).replaceAll("[^0-9]", "");
        return Integer.parseInt(answerStr);
    }
}