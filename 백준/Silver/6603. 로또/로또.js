function solution(input) {
  let iter = 0;
  const result = [];
  while (input[iter][0] != 0) {
    const nums = input[iter].split(" ").map(Number);
    const answer = [];
    nums.shift();
    backtrack([], 0);
    iter++;
    result.push([...answer]);
    function backtrack(curr, start) {
      if (curr.length === 6) {
        answer.push([...curr]);
        return;
      }
      for (let i = start; i < nums.length; i++)
        if (!curr.includes(nums[i])) {
          curr.push(nums[i]);
          backtrack(curr, i + 1);
          curr.pop();
        }
    }
  }
  const final = [];
  for (let i = 0; i < result.length; i++) {
    for (let j = 0; j < result[i].length; j++) {
      final.push(result[i][j].join(" "));
    }
    if (i != result.length - 1) {
      final.push("");
    }
  }

  console.log(final.join("\n"));
}

const fs = require("fs");
const file = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(file).toString().trim().split("\n");
solution(input);
