function solution(input) {
  const N = parseInt(input[0]);
  const expectedRanks = [];
  for (let i = 1; i <= N; i++) {
    expectedRanks.push(parseInt(input[i]));
  }

  expectedRanks.sort((a, b) => a - b);
  const ranks = Array.from({ length: N }, (_, index) => index + 1);
  let answer = 0;
  for (let i = 0; i < N; i++) {
    answer += Math.abs(expectedRanks[i] - ranks[i]);
  }
  console.log(answer);
}

const exp = require("constants");
const fs = require("fs");
const file = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(file).toString().trim().split("\n");
solution(input);
