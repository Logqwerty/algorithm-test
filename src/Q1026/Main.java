package Q1026;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
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
        int N = getInt(br.readLine());
        int[] A = new int[N];
        int[] B = new int[N];
        StringTokenizer stk = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; ++i) {
            A[i] = getInt(stk.nextToken());
        }
        stk = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; ++i) {
            B[i] = getInt(stk.nextToken());
        }

        Arrays.sort(A);
        Arrays.sort(B);

        int S = 0;
        for(int i = 0; i < N; ++i) {
            S += A[i] * B[N - i - 1];
        }

        System.out.println(S);
        br.close();
    }
}
