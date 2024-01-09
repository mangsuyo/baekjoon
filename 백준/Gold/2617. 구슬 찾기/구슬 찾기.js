function solution(input) {
  const [N, M] = input[0].split(" ").map(Number);
  const beads = [];
  let counts = 0;
  for (let i = 1; i <= M; i++) {
    beads.push(input[i].split(" ").map(Number));
  }

  let graph = {};
  for (let i = 1; i <= N; i++) {
    graph[i] = [];
  }

  for (let i = 0; i < beads.length; i++) {
    const [light, heavy] = beads[i];
    graph[light].push(heavy);
  }
  for (let i = 1; i <= N; i++) {
    const answer = bfs(i);
    if (answer >= Math.floor(N / 2) + 1) {
      counts++;
    }
  }

  graph = {};
  for (let i = 1; i <= N; i++) {
    graph[i] = [];
  }

  for (let i = 0; i < beads.length; i++) {
    const [light, heavy] = beads[i];
    graph[heavy].push(light);
  }

  for (let i = 1; i <= N; i++) {
    const answer = bfs(i);
    if (answer >= Math.floor(N / 2) + 1) {
      counts++;
    }
  }

  function bfs(key) {
    const queue = [key];
    const visited = [key];
    let answer = 0;
    while (queue.length > 0) {
      let curK = queue.shift();
      for (const num of graph[curK]) {
        if (!visited.includes(num)) {
          queue.push(num);
          visited.push(num);
          answer += 1;
        }
      }
    }
    return answer;
  }
  console.log(counts);

  //    4 > 2 >? 3 ?> 5 > 1
  //    5개 일때 일단 자기가 (N / 2) + 1 == 3개보다 무겁다? 확실히 아웃임
  //    혹은 자기가 (N / 2) + 1 == 3개보다 가볍다? 확실히 아웃임
}

const fs = require("fs");
const file = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(file).toString().trim().split("\n");
solution(input);
