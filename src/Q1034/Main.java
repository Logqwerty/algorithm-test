package Q1034;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader getBufferedReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    static int getInt(String str) {
        return Integer.parseInt(str);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = getBufferedReader();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int N = getInt(stk.nextToken());
        int M = getInt(stk.nextToken());

        Map<String, Integer> rows = new HashMap<>();
        for (int i = 0; i < N; ++i) {
            String row = br.readLine();
            if (rows.containsKey(row)) {
                rows.put(row, rows.get(row) + 1);
            } else {
                rows.put(row, 1);
            }
        }

        int result = 0;
        int K = getInt(br.readLine());
        for (String row : rows.keySet()) {
            int zero = 0;
            for (int i = 0; i < M; ++i) {
                if (row.charAt(i) == '0') zero++;
            }
            if (zero > K) continue;
            if ((K - zero) % 2 == 0) {
                result = Math.max(result, rows.get(row));
            }
        }
        System.out.println(result);
        br.close();
    }
}
