function solution(input) {
  const [N, K] = input.map(Number);
  console.log(bfs());
  function bfs() {
    const q = [[N, 0]];
    const visited = new Set();
    visited.add(N);
    while (q.length) {
      const [curX, t] = q.shift();
      if (curX == K) return t;
      const dx = [-1, 1, curX];
      for (let i = 0; i < 3; i++) {
        const nextX = curX + dx[i];
        if (nextX >= 0 && nextX <= 100000 && !visited.has(nextX)) {
          q.push([nextX, t + 1]);
          visited.add(nextX);
        }
      }
    }
  }
}

const fs = require("fs");
const file = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(file).toString().trim().split(" ");
solution(input);
