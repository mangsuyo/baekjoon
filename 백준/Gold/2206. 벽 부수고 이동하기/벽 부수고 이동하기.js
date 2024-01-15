class Queue {
  constructor() {
    this.rear = 0;
    this.front = 0;
    this.size = 0;
    this.items = {};
  }
  enqueue(value) {
    this.items[this.rear++] = value;
    this.size++;
  }
  dequeue() {
    const item = this.items[this.front];
    delete this.items[this.front++];
    this.size--;
    return item;
  }
}

function solution(input) {
  const [row, col] = input[0].split(" ").map(Number);
  const graph = [];
  for (let i = 1; i <= row; i++) {
    graph.push(input[i].split(""));
  }

  const visited = Array.from({ length: row }, () =>
    Array.from({ length: col }, () => Array.from({ length: 2 }).fill(false))
  );

  console.log(bfs());
  function bfs() {
    const queue = new Queue();
    queue.enqueue([0, 0, 1, 0]);
    const dr = [-1, 1, 0, 0];
    const dc = [0, 0, -1, 1];
    visited[0][0][0] = true;
    while (queue.size > 0) {
      const [curR, curC, count, isBreak] = queue.dequeue();
      if (curR == row - 1 && curC == col - 1) {
        return count;
      }
      for (let i = 0; i < 4; i++) {
        const nextR = curR + dr[i];
        const nextC = curC + dc[i];
        if (0 <= nextR && nextR < row && 0 <= nextC && nextC < col) {
          if (visited[nextR][nextC][isBreak] == false) {
            let nextIsBreak = isBreak;
            if (graph[nextR][nextC] === "1") {
              if (nextIsBreak === 0) nextIsBreak = 1;
              else continue;
            }
            queue.enqueue([nextR, nextC, count + 1, nextIsBreak]);
            visited[nextR][nextC][nextIsBreak] = true;
          }
        }
      }
    }
    return -1;
  }
}

const fs = require("fs");
const file = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(file).toString().trim().split("\n");
solution(input);
