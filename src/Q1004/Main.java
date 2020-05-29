package Q1004;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

    // 이 함수가 문제였네... 사각형 안에 포함되는지 판단하는 함수 로직이었어...
    static boolean isContained(Pos pos, Circle circle) {
        return (pos.x - circle.x) * (pos.x - circle.x)
                + (pos.y - circle.y) * (pos.y - circle.y) <= circle.r * circle.r;
    }

    static int solution(Pos start, Pos end, Circle[] circles) {
        boolean[] contained = new boolean[circles.length];

        // 1. 출발점을 포함하는 원들의 리스트를 구하고 정렬하자.
        // 그리고 boolean[]로 어떤 원인지 체크해두자.
        List<Circle> startInCircles = new ArrayList<>();
        for(int i = 0; i < circles.length; ++i) {
            if(isContained(start, circles[i])) {
                startInCircles.add(circles[i]);
                contained[i] = true;
            }
        }
        Collections.sort(startInCircles);

        // 2. 도착점을 포함하는 원들의 리스트를 구하자.
        // 공유하는 원 중에 가장 반지름이 짧은 원을 생각하지 않았네...
        Circle outermost = null;
        List<Circle> endInCircles = new ArrayList<>();
        for(int i = 0; i < circles.length; ++i) {
            if(!isContained(end, circles[i])) continue;
            if(contained[i]) {
                if(outermost == null) outermost = circles[i];
                else if(outermost.r > circles[i].r) outermost = circles[i];
            }
            endInCircles.add(circles[i]);
        }
        Collections.sort(endInCircles);

        // 2-1. 공유하는 원이 존재한다면(그런 원들이 여러 개 있다면, r이 가장 짧은 경우),
        // 각각 해당 원 직전까지 진입/이탈 횟수를 계산하면 된다.
        if(outermost != null) {
            int out = startInCircles.indexOf(outermost);
            int in = endInCircles.indexOf(outermost);
            return out + in;
        }

        // 2-2. 그런 경우가 없다면, 각 점을 포함하는 최외곽 원이 서로 동 떨어져 있는 경우이다.
        // startInCircles.length + endInCircles.length
        return startInCircles.size() + endInCircles.size();
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
