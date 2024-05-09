import java.util.HashMap;
import java.util.Map;

class Solution {
    private final int REMAINDER_NUMBER = 1234567;
	private Map<Integer, Integer> memo = new HashMap<>();

    public int solution(int n) {
        return dp(n);
    }
    
	private int dp(int n){

		memo.put(0, 0);
		memo.put(1, 1);

		for(int i = 2; i <= n; i++){
			memo.put(i, (memo.get(i - 1) + memo.get(i - 2)) % REMAINDER_NUMBER);
		}

		return memo.get(n);
	}
}