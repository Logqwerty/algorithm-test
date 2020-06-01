package Q1051;

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

    static int N, M;

    static boolean solution(int size, int[][] rect) {
        for(int i = 0; i < N; ++i) {
            if(i + size > N) break;
            for(int j = 0; j < M; ++j) {
                if(j + size > M) break;
                int tl = rect[i][j];
                int tr = rect[i+size-1][j];
                int bl = rect[i][j+size-1];
                int br = rect[i+size-1][j+size-1];
                if(tl == tr && tr == bl && bl == br) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = getBufferedReader();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        N = getInt(stk.nextToken());
        M = getInt(stk.nextToken());

        int[][] rect = new int[N][M];
        for(int i = 0; i < N; ++i) {
            String row = br.readLine();
            for(int j = 0; j < M; ++j) {
                rect[i][j] = getInt(row.charAt(j) + "");
            }
        }

        int min = Math.min(N, M);
        int result = 1;
        for(int i = min; i >= 2; --i) {
            if(solution(i, rect)) {
                result = i;
                break;
            }
        }
        System.out.println(result * result);
        br.close();
    }
}
