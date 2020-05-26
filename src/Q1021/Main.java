package Q1021;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader getBufferedReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    static int getInt(String str) {
        return Integer.parseInt(str);
    }

    static void rightShift(ArrayDeque<Integer> deque, int moved) {
        while(moved-- > 0) {
            int tail = deque.removeLast();
            deque.addFirst(tail);
        }
        deque.removeFirst();
    }

    static void leftShift(ArrayDeque<Integer> deque, int moved) {
        while(moved-- > 0) {
            int head = deque.removeFirst();
            deque.addLast(head);
        }
        deque.removeFirst();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = getBufferedReader();
        StringTokenizer stk = new StringTokenizer(br.readLine());
        int N = getInt(stk.nextToken());
        int M = getInt(stk.nextToken());

        ArrayDeque<Integer> position = new ArrayDeque<>();
        for(int i = 0; i < N; ++i) {
            position.add(i + 1);
        }

        int result = 0;
        stk = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; ++i) {
            int wanted = getInt(stk.nextToken());

            if(wanted == position.peek()) {
                position.poll();
                continue;
            }

            // 왼쪽을 이동할 지, 오른쪽으로 이동할 지 선택할 수 있지 않을까?
            // Deque 상의 wanted 위치를 찾아서 거리를 계산하고, 더 짧은 방향으로 움직이면 될 것 같다.
            int wantedIndex = 0;
            for(int pos : position) {
                if(pos == wanted) break;
                wantedIndex++;
            }

            int leftDistance = wantedIndex;
            int rightDistance = position.size() - wantedIndex;

            if(leftDistance > rightDistance) {
                result += rightDistance;
                rightShift(position, rightDistance);
            } else {
                result += leftDistance;
                leftShift(position, leftDistance);
            }
        }

        System.out.println(result);
        br.close();
    }
}

