package Q1002;

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

    static int getPositionCount(int x1, int y1, int r1, int x2, int y2, int r2) {
        if(x1 == x2 && y1 == y2 && r1 == r2) {
            // 두 원이 완벽히 일치하는 경우
            return -1;
        }

        int xdiff = x2 - x1;
        int ydiff = y2 - y1;
        int d = xdiff * xdiff + ydiff * ydiff;

        int rdiff = r2 - r1;
        int rdiff2 = rdiff * rdiff;
        if(d == rdiff2) {
            // 내접원 케이스
            return 1;
        } else if(d < rdiff2){
            // 한 원이 다른 원 안에 포함된 경우
            return 0;
        }

        int rsum = r2 + r1;
        int rsum2 = rsum * rsum;
        if(d == rsum2) {
            // 외접원 케이스
            return 1;
        } else if(d < rsum2) {
            // 두 원이 두 개의 접점을 만들어 내는 경우
            return 2;
        }

        // 두 원이 아예 동 떨어져 있는 경우
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = getBufferedReader();
        int T = getInt(br.readLine());

        int x1, y1, r1, x2, y2, r2;
        StringTokenizer stk;
        StringBuilder sb = new StringBuilder();
        for(int t = 0; t < T; ++t) {
            stk = new StringTokenizer(br.readLine());
            x1 = getInt(stk.nextToken());
            y1 = getInt(stk.nextToken());
            r1 = getInt(stk.nextToken());
            x2 = getInt(stk.nextToken());
            y2 = getInt(stk.nextToken());
            r2 = getInt(stk.nextToken());

            sb.append(getPositionCount(x1, y1, r1, x2, y2, r2)).append('\n');
        }

        System.out.println(sb.toString());
        br.close();
    }
}
