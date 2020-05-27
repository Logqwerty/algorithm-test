package Q1003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader getBufferedReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    static int getInt(String str) {
        return Integer.parseInt(str);
    }

    static int[][] count = new int[41][2];

    static int[] fibonacci(int n) {
        if(count[n][0] != 0 && count[n][1] != 0) return count[n];
        if(n == 0) return count[0];
        if(n == 1) return count[1];

        int[] temp1 = fibonacci(n - 1);
        int[] temp2 = fibonacci(n - 2);

        count[n][0] = temp1[0] + temp2[0];
        count[n][1] = temp1[1] + temp2[1];
        return count[n];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = getBufferedReader();

        count[0][0] = 1; count[0][1] = 0;
        count[1][0] = 0; count[1][1] = 1;

        StringBuilder sb = new StringBuilder();
        int T = getInt(br.readLine());
        for(int t = 0; t < T; ++t) {
            int n = getInt(br.readLine());
            fibonacci(n);
            sb.append(count[n][0]).append(' ').append(count[n][1]).append('\n');
        }

        System.out.println(sb.toString());
        br.close();
    }
}
