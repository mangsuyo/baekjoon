function solution(input) {
  const [N, S] = input[0].split(" ").map(Number);
  const nums = input[1].split(" ").map(Number);
  let count = 0;
  const answer = [];
  function backtrack(curr, start, K) {
    if (curr.length === K) {
      const sum = curr.reduce((sum, value) => (sum += value), 0);
      if (sum === S) {
        answer.push([...curr]);
        count++;
      }
      return;
    }
    for (let i = start; i < nums.length; i++) {
      curr.push(nums[i]);
      backtrack(curr, i + 1, K);
      curr.pop();
    }
  }
  for (let k = 1; k <= N; k++) {
    backtrack([], 0, k);
  }
  console.log(count);
}

const fs = require("fs");
const file = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(file).toString().trim().split("\n");
solution(input);
