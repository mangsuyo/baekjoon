class Solution {
    public int[] solution(String s) {
    int count = 0;
    int remove = 0;
    while (!s.equals("1")) {
      int length = 0;
      for (int i = 0; i < s.length(); i++) {
        if (s.charAt(i) == '1') {
          length += 1;
        } else {
          remove += 1;
        }
      }
      String binaryString = Integer.toBinaryString(length);
      s = binaryString;
      count += 1;
    }

    int[] answer = {count, remove};
    return answer;
    }
}