class Queue{
    constructor(){
        this.items={};
        this.rear = 0;
        this.front = 0;
        this.size = 0;
    }
    
    enqueue(value){
        this.items[this.rear++] = value;
        this.size++;
    }
    
    dequeue(){
        const value = this.items[this.front];
        delete this.items[this.front++];
        this.size--;
        return value;
    }
}

function solution(land) {
    const row = land.length;
    const col = land[0].length;
    const dr = [-1, 1, 0, 0];
    const dc = [0, 0, -1, 1];
    const visited = Array.from({length: row}, () => Array(col).fill(0));
    let sum = Array.from({length : col}).fill(0);
    for(let i = 0; i< row; i++){
        for(let j = 0; j < col; j++){
            if(land[i][j] == 1 && visited[i][j] == 0) bfs(i, j);
        }
    }
    return (Math.max(...sum));
    

    function bfs(x, y){
        const queue = new Queue();
        queue.enqueue([x, y]);
        visited[x][y] = 1;
        const coordinate = new Set();
        coordinate.add(y);
        let cnt = 0;
        while(queue.size){
            const [curR, curC] = queue.dequeue();
            cnt += 1;
            for(let i = 0; i < 4; i++){
                const nextR = curR + dr[i];
                const nextC = curC + dc[i];
                if(0 <= nextR && nextR < row && 0 <= nextC && nextC < col){
                    if(visited[nextR][nextC] == 0 && land[nextR][nextC] == 1){
                        queue.enqueue([nextR,nextC]);
                        visited[nextR][nextC] = 1;
                        if(!coordinate.has(nextC)) coordinate.add(nextC);
                    }
                }
            }
        }    
        coordinate.forEach((c) => sum[c] += cnt);
    }
}





