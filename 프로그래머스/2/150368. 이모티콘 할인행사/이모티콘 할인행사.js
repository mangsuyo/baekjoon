function solution(users, emoticons) {
    const N = users.length;
    const M = emoticons.length;
    const combination = [];
    const discount = [10, 20, 30, 40];
    function backtrack(curr){
        if(curr.length == M){
            combination.push([...curr]);
            return;
        }
        else{
            for(let num of discount){
                curr.push(num);
                backtrack(curr);
                curr.pop();
            }         
        }
    }
    backtrack([]);
    const subscribes = [];
    
    for(const disc of combination){
        const emoticon = [];
        let subscribe = 0;
        let profit = 0;
        for(let i = 0; i < users.length; i++){
            const [ratio, limit] = users[i];
            let sum = 0;
            for(let j = 0; j < disc.length; j++){
                if(ratio <= disc[j]){
                    sum += emoticons[j] * 0.01 * (100 - disc[j]);
                }
            }
            if(sum >= limit){
                subscribe += 1;
            }
            else{
                profit += sum;
            }
        }
        subscribes.push([subscribe, profit]);
    }
        
        subscribes.sort((a, b) => {
            if(a[0] < b[0]) return 1;
            else if(a[0] == b[0]) return b[1] - a[1];
            else if(a[0] > b[0]) return -1;
        });
    
        return (subscribes[0]);    
}