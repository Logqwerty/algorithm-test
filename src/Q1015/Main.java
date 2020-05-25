package Q1015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static class Pair implements Comparable<Pair> {
        int index;
        int value;
        Pair(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Pair p) {
            return this.value - p.value;
        }
    }

    static BufferedReader getBufferedReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    static int getInt(String str) {
        return Integer.parseInt(str);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = getBufferedReader();
        int N = getInt(br.readLine());
        StringTokenizer stk = new StringTokenizer(br.readLine());
        Pair[] A = new Pair[N];
        for(int i = 0; i < N; ++i) {
            int value = getInt(stk.nextToken());
            // A[i] = (현재 인덱스와 값)
            A[i] = new Pair(i, value);
        }

        // 값을 기준으로 오름차순 정렬
        // 값이 같다면 인덱스를 기준으로 오름차순 정렬
        Arrays.sort(A);

        int[] P = new int[N];
        for(int i = 0; i < N; ++i) {
            Pair pair = A[i];
            // P[이전위치] = i;
            P[pair.index] = i;
        }
        StringBuilder sb = new StringBuilder();
        for(int val : P) {
            sb.append(val).append(' ');
        }
        System.out.println(sb.toString());
        br.close();
    }
}


