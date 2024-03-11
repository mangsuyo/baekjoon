function solution(topping) {

    
    let start = 0;
    let end = topping.length - 1;
    let cnt = 0;
    let mid;
    let N = 0;
    let indexes = [];
    while(start <= end){
        const A = new Set();
        const B = new Set();
        mid = Math.floor((start + end) / 2);
        for(let i = 0; i < mid; i++){
            A.add(topping[i]);
        }
        for(let i = mid; i <= end; i++){
            B.add(topping[i]);
        }
        
        if(A.size > B.size){
            end = mid - 1;
        }
        else if(A.size < B.size){
            start = mid;
        }
        else {
            N = A.size;
            break;
        }
    }
    
    if(N != 0){
        const A = new Set();
        for(let i = start; i <= end; i++){
            A.add(topping[i]);
            if(A.size == N) cnt += 1;
            if(A.size > N) return cnt;
        }
    }
    
    return cnt;
}