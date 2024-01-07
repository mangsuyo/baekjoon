function solution(input) {
  const [N, M] = input.map(Number);
  const arr = Array.from({ length: N }, (_, index) => index + 1);
  const answer = [];
  let result = [];

  function isAscending(curr) {
    for (let i = 0; i < curr.length - 1; i++) {
      if (curr[i] > curr[i + 1]) return false;
    }
    return true;
  }
  function backtrack(curr) {
    if (curr.length === M && isAscending(curr)) {
      answer.push([...curr]);
      return;
    }
    for (let num of arr) {
      if (!curr.includes(num)) {
        curr.push(num);
        backtrack(curr);
        curr.pop();
      }
    }
  }
  backtrack([]);
  for (let i = 0; i < answer.length; i++) {
    result.push(answer[i].join(" "));
  }
  console.log(result.join("\n"));
}

const fs = require("fs");
const file = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(file).toString().trim().split(" ");
solution(input);
