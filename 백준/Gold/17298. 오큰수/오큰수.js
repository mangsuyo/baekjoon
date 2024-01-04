function solution(input) {
  const N = parseInt(input.shift());
  const nums = input[0].split(" ").map(Number);
  const stack = [[nums[0], 0]];
  const result = Array(N).fill(-1);
  for (let i = 1; i < N; i++) {
    while (stack.length && stack[stack.length - 1][0] < nums[i]) {
      const [number, index] = stack.pop();
      result[index] = nums[i];
    }
    stack.push([nums[i], i]);
  }
  console.log(result.join(" "));
}

const fs = require("fs");
const file = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(file).toString().trim().split("\n");
solution(input);
