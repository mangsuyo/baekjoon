import java.util.*;

class Solution {

	public int solution(int[] queue1, int[] queue2) {
		Queue<Integer> queueA = new ArrayDeque<>();
		Queue<Integer> queueB = new ArrayDeque<>();

		long limit = queue1.length * 3;
		int answer = -1;
		long sumA = 0;
		long sumB = 0;

		for (int num : queue1) {
			queueA.add(num);
			sumA += num;
		}

		for (int num : queue2) {
			queueB.add(num);
			sumB += num;
		}

		if((sumA + sumB) % 2 != 0){
			return -1;
		}

		while (!queueA.isEmpty() && !queueB.isEmpty()) {
			if(answer > limit) return -1;
			answer += 1;
			if (sumA < sumB) {
				int num = queueB.poll();
				queueA.add(num);
				sumB -= num;
				sumA += num;
			} else if (sumA > sumB) {
				int num = queueA.poll();
				queueB.add(num);
				sumA -= num;
				sumB += num;
			} else {
				return answer;
			}
		}
		return -1;
	}
}
