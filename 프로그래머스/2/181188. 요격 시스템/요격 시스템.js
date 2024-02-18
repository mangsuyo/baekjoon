function solution(targets) {
  const schedule = targets;
  schedule.sort((a, b) => {
    if (a[1] < b[1]) return -1;
    else if (a[1] > b[1]) return 1;
    else if (a[1] == b[1]) return a[0] - b[0];
  });
  let count = 1;
  const selected = [];
  selected.push(schedule[0]);
  for (let i = 1; i < schedule.length; i++) {
    if (selected.length && selected[selected.length - 1][1] <= schedule[i][0]) {
      selected.pop();
      selected.push(schedule[i]);
      count++;
    }
  }
  return (count);
}