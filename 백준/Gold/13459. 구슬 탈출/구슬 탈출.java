import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[] dr = new int[]{-1, 1, 0, 0};
    static int[] dc = new int[]{0, 0, -1, 1};

    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] line = reader.readLine().split(" ");
        N = Integer.parseInt(line[0]);
        M = Integer.parseInt(line[1]);

        char[][] graph = new char[N][M];
        Node red = null;
        Node blue = null;
        for(int i = 0; i < N; i++){
            String[] s = reader.readLine().split("");
            for(int j = 0; j < M; j++){
                graph[i][j] = s[j].charAt(0);
                if(graph[i][j] == 'R'){
                    red = new Node(i, j);
                }
                else if(graph[i][j] == 'B'){
                    blue = new Node(i, j);
                }
            }
        }

        Queue<Ball> queue = new ArrayDeque<>();
        boolean[][][][] visited = new boolean[N][M][N][M];
        queue.offer(new Ball(red, blue, 0));
        visited[red.r][red.c][blue.r][blue.c] = true;

        while(!queue.isEmpty()){
            Ball cur = queue.poll();
            if(cur.count > 10){
                System.out.println(0);
                return;
            }
            for(int i = 0; i < 4; i++){
                int nextRR = cur.red.r;
                int nextRC = cur.red.c;
                int nextBR = cur.blue.r;
                int nextBC = cur.blue.c;
                boolean isRedHall = false;
                boolean isBlueHall = false;
                while(graph[nextRR][nextRC] != '#') {
                    nextRR += dr[i];
                    nextRC += dc[i];
                    if (graph[nextRR][nextRC] == 'O') {
                        isRedHall = true;
                    }
                }
                while(graph[nextBR][nextBC] != '#') {
                    nextBR += dr[i];
                    nextBC += dc[i];
                    if (graph[nextBR][nextBC] == 'O') {
                        isBlueHall = true;
                    }
                }

                if(isBlueHall) continue;

                if(isRedHall && cur.count < 10){
                    System.out.println(1);
                    return;
                }
                nextRR -= dr[i];
                nextRC -= dc[i];
                nextBR -= dr[i];
                nextBC -= dc[i];

                if(nextRR == nextBR && nextRC == nextBC){
                    if(i == 0){
                        if(cur.red.r < cur.blue.r) nextBR -= dr[i];
                        else nextRR -= dr[i];
                    }
                    else if(i == 1){
                        if(cur.red.r < cur.blue.r) nextRR -= dr[i];
                        else nextBR -= dr[i];
                    }
                    else if(i == 2){
                        if(cur.red.c < cur.blue.c) nextBC -= dc[i];
                        else nextRC -= dc[i];
                    }
                    else if(i == 3){
                        if(cur.red.c < cur.blue.c) nextRC -= dc[i];
                        else nextBC -= dc[i];
                    }
                }

                if(!visited[nextRR][nextRC][nextBR][nextBC]) {
                    queue.offer(new Ball(new Node(nextRR, nextRC), new Node(nextBR, nextBC), cur.count + 1));
                    visited[nextRR][nextRC][nextBR][nextBC] = true;
                }
            }
        }

        System.out.println(0);



    }

    static class Ball{
        Node red, blue;
        int count;

        public Ball(Node red, Node blue, int count) {
            this.red = red;
            this.blue = blue;
            this.count = count;
        }
    }

    static class Node{
        int r;
        int c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}