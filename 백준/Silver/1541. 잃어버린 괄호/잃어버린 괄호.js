function solution(input) {
  const sentence = input.split("-");
  let answer;
  if (!input.includes("-")) {
    answer = input
      .split("+")
      .map(Number)
      .reduce((sum, value) => (sum += value), 0);
  } else {
    for (let i = 0; i < sentence.length; i++) {
      let temp = sentence[i].includes("+")
        ? sentence[i]
            .split("+")
            .map(Number)
            .reduce((sum, value) => (sum += value), 0)
        : parseInt(sentence[i]);
      if (i == 0) answer = temp;
      else answer -= temp;
    }
  }
  console.log(answer);
}

const fs = require("fs");
const file = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(file).toString().trim();
solution(input);
