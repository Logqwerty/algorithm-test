package Q1024;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader getBufferedReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    static int getInt(String str) {
        return Integer.parseInt(str);
    }

    static String solution(int N, int L) {
        int l, x = -1;
        for(l = L; l <= 100; ++l) {
            int temp = N + ((l - 1) * l) / 2;
            if(temp % l == 0) {
                x = temp / l;
                break;
            }
        }

        if(l > 100 || x - l + 1 < 0) {
            return "-1";
        }
        StringBuilder sb = new StringBuilder();
        while(--l >= 0) {
            sb.append(x - l).append(' ');
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = getBufferedReader();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int N = getInt(stk.nextToken());
        int L = getInt(stk.nextToken());

        System.out.println(solution(N, L));
        br.close();
    }
}
