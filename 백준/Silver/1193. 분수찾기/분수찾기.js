function solution(input) {
  let N = parseInt(input);
  let line = 1;
  while (N > line) {
    N -= line;
    line++;
  }

  let start;
  if (line % 2 == 0) {
    start = [1, line];
    for (let i = 0; i < N - 1; i++) {
      start[0]++;
      start[1]--;
    }
  } else {
    start = [line, 1];
    for (let i = 0; i < N - 1; i++) {
      start[0]--;
      start[1]++;
    }
  }
  console.log(`${start[0]}/${start[1]}`);
}
// 1/1
// 1/2 2/1
// 3/1 2/2 1/3
// 1/4 2/3 3/2 4/1

const fs = require("fs");
const file = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(file).toString().trim();
solution(input);
