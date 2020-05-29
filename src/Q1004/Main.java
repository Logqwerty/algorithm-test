package Q1004;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static class Pos {
        int x;
        int y;
        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Circle implements Comparable<Circle> {
        int x;
        int y;
        int r;
        public Circle(int x, int y, int r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }

        @Override
        public int compareTo(Circle c) {
            return this.r - c.r;
        }
    }

    static BufferedReader getBufferedReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    static int getInt(String str) {
        return Integer.parseInt(str);
    }

    static boolean isContained(Pos pos, Circle circle) {
        int x = (pos.x - circle.x);
        int y = (pos.y - circle.y);
        int r = circle.r;
        return x * x + y * y <= r * r;
    }

    static int solution(Pos start, Pos end, Circle[] circles) {
        // 다 풀고 보니 결국 우리가 원하는 경우는 단 두 가지 조건에 해당하는 원들이다.
        // 1. start만 포함하고 end는 포함하지 않는 경우 => 최소 이탈 횟수
        // 2. end만 포함하고 start는 포함하지 않는 경우 => 최소 진입 횟수
        // 위 두 가지를 만족하는 원의 갯수가 결국 원하는 값이다.

        int count = 0;
        for(Circle circle : circles) {
            boolean isStartContained = isContained(start, circle);
            boolean isEndContained = isContained(end, circle);
            // 1. start만 포함하는 경우
            if(!isEndContained && isStartContained) count++;
            // 2. end만 포함하는 경우
            if(isEndContained && !isStartContained) count++;
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = getBufferedReader();
        StringBuilder sb = new StringBuilder();
        StringTokenizer stk;

        int T = getInt(br.readLine());
        for(int t = 0; t < T; ++t) {
            stk = new StringTokenizer(br.readLine());
            Pos start = new Pos(getInt(stk.nextToken()), getInt(stk.nextToken()));
            Pos end = new Pos(getInt(stk.nextToken()), getInt(stk.nextToken()));

            int C = getInt(br.readLine());
            Circle[] circles = new Circle[C];
            for(int c = 0; c < C; ++c) {
                stk = new StringTokenizer(br.readLine());
                circles[c] = new Circle(
                    getInt(stk.nextToken()),
                    getInt(stk.nextToken()),
                    getInt(stk.nextToken())
                );
            }
            sb.append(solution(start, end, circles)).append('\n');
        }
        System.out.println(sb.toString());
        br.close();
    }
}
