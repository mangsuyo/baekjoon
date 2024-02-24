function solution(input) {
  const [N, ...arr] = input;
  const answer = [];
  for (let i = 0; i < parseInt(N); i++) {
    const str = arr[i];
    const stack1 = [];
    const stack2 = [];
    for (let j = 0; j < str.length; j++) {
      const char = str[j];
      if (char === "<") {
        if (stack1.length === 0) continue;
        else stack2.push(stack1.pop());
      } else if (char === ">") {
        if (stack2.length === 0) continue;
        else stack1.push(stack2.pop());
      } else if (char === "-") {
        if (stack1.length === 0) continue;
        else stack1.pop();
      } else if (
        ("a" <= char && char <= "z") ||
        ("A" <= char && char <= "Z") ||
        ("0" <= char && char <= "9")
      ) {
        stack1.push(char);
      }
    }
    while (stack2.length > 0) {
      stack1.push(stack2.pop());
    }
    answer.push(stack1.join(""));
  }
  console.log(answer.join("\n"));
}

const fs = require("fs");
const file = process.platform === "linux" ? "/dev/stdin" : "input.txt";
const input = fs.readFileSync(file).toString().split("\n");
solution(input);
