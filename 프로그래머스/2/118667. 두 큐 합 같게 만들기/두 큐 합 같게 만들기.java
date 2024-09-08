import java.util.*;

class Solution {
	public int solution(int[] queue1, int[] queue2) {
		int answer = 0;
		int[] sums = getTotal(queue1, queue2);
		long sumA = sums[0];
		long sumB = sums[1];

		int lenA = queue1.length;
		int lenB = queue2.length;

		int loop = 0;

		if ((sumA + sumB) % 2 != 0)
			return -1;

		Queue<Integer> queueA = new ArrayDeque<>();
		Queue<Integer> queueB = new ArrayDeque<>();

		for (int i = 0; i < queue1.length; i++) {
			queueA.add(queue1[i]);
		}

		for (int i = 0; i < queue2.length; i++) {
			queueB.add(queue2[i]);
		}

		while (!queueA.isEmpty() && !queueB.isEmpty()) {
			if(loop > (lenA + lenB) * 3){
				break;
			}
			if (sumA == sumB)
				return answer;
			else if (sumA > sumB) {
				int num = queueA.poll();
				queueB.add(num);
				sumA -= num;
				sumB += num;
				answer += 1;
			} else {
				int num = queueB.poll();
				queueA.add(num);
				sumA += num;
				sumB -= num;
				answer += 1;
			}
			loop += 1;
		}

		return -1;

	}

	public int[] getTotal(int[] queue1, int[] queue2) {
		int sumA = 0;
		int sumB = 0;

		for (int i = 0; i < queue1.length; i++) {
			sumA += queue1[i];
		}

		for (int i = 0; i < queue2.length; i++) {
			sumB += queue2[i];
		}

		return new int[] {sumA, sumB, sumA + sumB};
	}
}
