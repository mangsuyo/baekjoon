function solution(input) {
  const [L, C] = input[0].split(" ").map(Number);
  const chars = input[1].split(" ").sort((a, b) => a.localeCompare(b));
  const vowel = ["a", "e", "i", "o", "u"];
  const answer = [];
  const result = [];
  backtrack([], 0);
  for (let i = 0; i < answer.length; i++) {
    const temp = answer[i].join("");
    result.push(temp);
  }

  console.log(result.join("\n"));

  function backtrack(curr, start) {
    if (curr.length == L) {
      if (isValid(curr)) {
        answer.push([...curr]);
      }
      return;
    }
    for (let i = start; i < chars.length; i++) {
      curr.push(chars[i]);
      backtrack(curr, i + 1);
      curr.pop();
    }
  }
  function isValid(curr) {
    for (let i = 0; i < curr.length - 1; i++) {
      if (curr[i] > curr[i + 1]) return false;
    }
    let count = 0;
    for (let char of vowel) {
      if (curr.includes(char)) count++;
    }
    if (count == 0) return false;
    if (count > L - 2) return false;
    return true;
  }
}

const fs = require("fs");
const file = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(file).toString().trim().split("\n");
solution(input);
