## 회전하는 큐

### 문제 분석

문제만 제대로 이해한다면 전혀 어렵지 않은 문제이다. 

1. 첫 번째 원소를 뽑아낸다. 이 연산을 수행하면, 원래 큐의 원소가 a1, ..., ak이었던 것이 a2, ..., ak와 같이 된다.
2. 왼쪽으로 한 칸 이동시킨다. 이 연산을 수행하면, a1, ..., ak가 a2, ..., ak, a1이 된다.
3. 오른쪽으로 한 칸 이동시킨다. 이 연산을 수행하면, a1, ..., ak가 ak, a1, ..., ak-1이 된다.

위 규칙 중 2, 3 번 연산의 최솟값을 구하는 문제인데, 곰곰이 생각해보면 최소값을 구하는 것은 단순하다. 한 쪽 방향으로만 움직이는 경우가 최소값이 되는 경우이다. 왜냐하면 중간에 다른 방향으로 가는 움직임이 있다면 서로 상쇄하기 때문에 움직이지 않은 것과 동일하기 떄문이다. 

한마디로 왼쪽으로만 이동했을 때 움직이는 거리(leftDistance)와 오른쪽으로만 이동했을 때 움직이는 거리(rightDistance)를 비교해서 최소인 경우를 구하면 되는 문제이다.

하지만 이것보다 더 중요한 조건은(정확히는 내가 놓쳤던...) `뽑아내려고 하는 원소의 위치가 주어진다. (이 위치는 가장 처음 큐에서의 위치이다.) `라는 조건이다. 주어진 입력은 **가장 처음 큐에서의 위치**이다. 이것을 간과하고 회전한 큐에서의 위치로 문제를 풀다보니 계속 오답이 나왔다...

Deque의 각 원소의 값은 **가장 처음 큐에서의 위치**를 저장하고, 주어진 1, 2, 3번 연산을 적절하게 수행하도록 코드를 변경하니 통과할 수 있었다.

### 문제 풀이1

처음에는 가장 단순하게 풀었다.

1. Deque의 head가 원하는 값이랑 동일하면 1번 연산을 수행한다.
2. 동일하지 않다면 원하는 값이 head로 올 때까지 
   - Deque를 왼쪽 방향으로 회전하고, 횟수를 카운팅한다. (2번연산)
   - Deque를 오른쪽 방향으로 회전한다, 횟수를 카운팅한다. (3번연산)
3. 2번에서 계산한 횟수들을 비교하여 더 짧은 경우를 결과 값에 더하고, Deque의 상태를 해당 경우로 업데이트한다.

```java
// 1.
int wanted = getInt(stk.nextToken());
if(wanted == deque.peek()) {
  deque.poll();
  continue;
}

// 2-1.
int left = 0;
ArrayDeque<Integer> leftShift = new ArrayDeque<>(deque);
for(int j = 0; j < leftShift.size(); ++j) {
	//...
  left++;
	//...
  if(wanted == head) {
    leftShift.poll();
    break;
  }
}

// 2-2.
int right = 0;
ArrayDeque<Integer> rightShift = new ArrayDeque<>(deque);
for(int j = 0; j < rightShift.size(); ++j) {
	//...
  right++;
	//...
  if(wanted == head) {
    rightShift.poll();
    break;
  }
}

// 3.
if(left > right) {
  deque = rightShift;
  result += right;
} else {
  deque = leftShift;
  result += left;
}
```

### 문제 풀이2

풀이 1에서 마음에 안 들었던 점은 기존 Deque를 변형하지 않고 왼쪽 방향을 회전한 경우와 오른쪽 방향을 회전한 경우를 만들기 위해 Copy를 한다는 점이다. 물론 주어진 N의 크기가 매우 작았기 때문에 문제 없었지만, 이를 개선하고 싶었다.

왼쪽 방향인지 오른쪽 방향인지 결정하는 것은 회전한 거리이기 때문에, 거리를 미리 계산하여 비교하면 어느 방향인지 결정되고, 그러면 Copy없이 바로 기존 Deque를 해당 방향으로 회전하면 될 것이다.

거리를 미리 계산하기 위해 원하는 값이 현재 Deque 상의 몇 번째 위치에 있는 알아야할 필요가 있다. 다음과 같은 코드로, Deque를 한번 순회하면서 위치(wantedIndex)를 찾는다.

```java
int wantedIndex = 0;
for(int pos : position) {
  if(pos == wanted) break;
  wantedIndex++;
}
```

wantedIndex를 이용해 왼쪽 방향으로 회전해 head로 가는 거리와 오른쪽 방향으로 회전해 head로 가는 거리를 계산한다.

```java
int leftDistance = wantedIndex;
int rightDistance = deque.size() - wantedIndex;
```

leftDistance와 rightDistance를 비교하여 어느 방향으로 회전할 지 결정할 수 있다.

```java
if(leftDistance > rightDistance) {
  result += rightDistance;
  rightShift(position, rightDistance);
} else {
  result += leftDistance;
  leftShift(position, leftDistance);
}
```

### 회고

문제 조건을 잘 읽자... 제발...