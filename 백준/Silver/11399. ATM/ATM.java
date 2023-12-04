import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String[] input = br.readLine().split(" ");
        List<Integer> waits = new ArrayList<>();

        for (String wait : input) {
            waits.add(Integer.parseInt(wait));
        }
        Collections.sort(waits);

        int total = 0;
        int sum = 0;
        for(int i = 0; i < N; i++){
           sum = sum + waits.get(i);
           total += sum;
        }
        System.out.println(total);
    }
}