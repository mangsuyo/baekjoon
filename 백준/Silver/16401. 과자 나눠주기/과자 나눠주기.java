import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] numbers = br.readLine().split(" ");

        int M = Integer.parseInt(numbers[0]);
        int N = Integer.parseInt(numbers[1]);

        String[] snack = br.readLine().split(" ");

        List<Integer> snacks = new ArrayList<Integer>();

        for(int i = 0; i < N; i++){
            snacks.add(Integer.parseInt(snack[i]));
        }

        Collections.sort(snacks);
        long sum = (long) snacks.stream().mapToInt(Integer::intValue).sum();

        int st = 1;
        int en = snacks.get(snacks.size() - 1);
        int answer = 0;

        while(st <= en){
            List<Integer>mods = new ArrayList<>();
            int mid = (st + en) / 2;
            for(int length : snacks){
                if(length >= mid){
                    mods.add( length / mid);
                }
            }
            int total = 0;
            for(int mod : mods){
                total += mod;
            }

            if(total >= M){
                answer = mid;
                st = mid + 1;
            }
            else{
                en = mid - 1;
            }
        }
        System.out.println(answer);
    }
}