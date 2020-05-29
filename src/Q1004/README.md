## 어린 왕자

### 문제 분석

어린 왕자가 출발점(start)에서 도착점(end)까지 도달하는데 필요한 진입/이탈 최소 횟수를 구하는 문제이다.

문제에서 주어지는 행성은 크게 4가지로 분류할 수 있다.

1. start만 포함하는 행성
2. end만 포함하는 행성
3. start와 end를 동시에 포함하는 행성
4. start와 end를 포함하지 않는 행성

우리는 진입/이탈 최소 횟수를 구해야 하기 때문에 4번의 경우는 아예 관심을 둘 필요가 없다. 자세히 보면 3번의 경우도 관심을 둘 필요가 없다. 왜냐하면 start와 end를 동시에 포함한다는 것은 해당 행성을 굳이 진입/이탈할 필요 없이 start에서 end로 갈 수 있기 때문이다.

### 문제 풀이1

처음에는 약간 복잡하게 풀었다. 

1. start를 포함하는 모든 행성의 리스트(startList), end를 포함하는 모든 행성의 리스트(endList)를 구한다.
   - 그리고 각 리스트를 r이 작은 순서대로 정렬한다.
2. start와 end를 모두 포함하는 행성들 중 가장 r이 작은 행성을 계산해둔다.
3. 만약 2번 과정에서 그런 행성을 찾는다면, 각 리스트에서 해당 행성의 위치를 찾아서 최소 진입/이탈 횟수를 계산했다.
   - `(startList에서 해당 행성의 위치)` + `(endList에서 해당 행성의 위치)`
4. 만약 그런 행성이 없다면, start를 포함하는 가장 외곽원과 end를 포함하는 가장 외곽원이 서로 동 떨어져 있다는 것이고, 간단하게 최소 진입/이탈 횟수를 계산할 수 있다.
   - `(startList의 길이)` + `(endList의 길이)`

### 문제 풀이2

다 풀고 보니 굳이 풀이1 처럼 복잡하게 풀 필요가 없다는 걸 느꼈다. 풀이 1의 3번, 4번 과정에서 구한 값은 다음 두 가지 경우에 해당하는 행성들의 수를 센 것이다.

1. start만 포함하는 행성 => 이탈할 때 최소한으로 거쳐야 하는 행성들
2. end만 포함하는 행성 => 진입할 때 최소한으로 거쳐하는 행성들

즉, `1번 조건을 만족하는 행성` + `2번 조건을 만족하는 행성` = `최소 진입/이탈 횟수` 가 된다. 괜히 start를 포함하는 모든 리스트와 end를 포함하는 모든 리스트를 구하고, start와 end를 모두 포함하는 행성이 있고, 그 중에 가장 작은 r을 가지는 행성이 뭔지 구할 필요가 없는 것이다...

당연히 문제 풀이는 매우 심플해졌다.

```java
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
```
