class Solution {
    public int solution(int n) {
    int count = 0;

    for (int i = 1; i <= n; i++) {
      int sum = 0;
      for (int j = i; j <= n; j++) {
        sum += j;
        if (sum == n) {
          count += 1;
          break;
        }
        if(sum > n){
          break;
        }
      }
    }
    System.out.println(count);
    return count;
    }
}