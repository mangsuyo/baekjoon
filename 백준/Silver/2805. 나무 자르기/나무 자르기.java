import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");

        int N = Integer.parseInt(str[0]);
        int M = Integer.parseInt(str[1]);

        String[] str_heights = br.readLine().split(" ");

        List<Integer> heights = new ArrayList<>();
        for(String height : str_heights){
            heights.add(Integer.parseInt(height));
        }

        Collections.sort(heights);

        int left = 0; // 절단기 높이의 최소값
        int right = Collections.max(heights); // 절단기 높이의 최대값

        int result = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            long total = 0;

            for (int height : heights) {
                if (height > mid) {
                    total += height - mid;
                }
            }

            if (total >= M) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(result);
    }
}
