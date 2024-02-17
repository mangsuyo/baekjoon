function solution(input) {
  const N = parseInt(input[0]);
  for (let i = 1; i <= N; i++) {
    const num = parseInt(input[i]);
    console.log(dp(num));
  }

  function dp(num) {
    const memo = { 1: 1, 2: 2, 3: 4 };
    for (let i = 4; i <= num; i++) {
      memo[i] = memo[i - 1] + memo[i - 2] + memo[i - 3];
    }
    return memo[num];
  }
}

const fs = require("fs");
const file = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(file).toString().trim().split("\n");
solution(input);
