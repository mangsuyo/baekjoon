function solution(friends, gifts) {
    const giver = {};
    const reciever = {};
    const hash = {};
    const result = {};
    const diff = {};
    for(let i = 0; i < friends.length; i++){
        giver[friends[i]] = {};
        reciever[friends[i]] = {};
        hash[friends[i]] = {};
        diff[friends[i]] = {};
        result[friends[i]] = 0;
        for(let j = 0; j < friends.length; j++){
            giver[friends[i]][friends[j]] = 0;
            reciever[friends[i]][friends[j]] = 0;
            hash[friends[i]][friends[j]] = 0;
            diff[friends[i]] = 0;
        }
    }
    for(let gift of gifts){
        const [A, B] = gift.split(" ");
        giver[A][B] += 1;
        reciever[B][A] += 1;
    }
    

    
    for(let i = 0; i < friends.length; i++){
        for(let j = 0; j < friends.length; j++){
            hash[friends[i]][friends[j]] = giver[friends[i]][friends[j]] - reciever[friends[i]][friends[j]];
        }
    }
    
    for(let i = 0; i < friends.length; i++){
        diff[friends[i]] = Object.values(hash[friends[i]]).reduce((sum, value) => sum += value, 0);
    }
    

    for(let i = 0; i < friends.length; i++){
        for(let j = i; j < friends.length; j++){
            if(hash[friends[i]][friends[j]] > 0){
                result[friends[i]] += 1;
            }
            else if(hash[friends[i]][friends[j]] < 0){
                result[friends[j]] += 1;
            }
            else{
                if(diff[friends[i]] > diff[friends[j]]){
                    result[friends[i]] += 1;
                }
                else if(diff[friends[i]] < diff[friends[j]]){
                    result[friends[j]] += 1;
                }
            }
        }
    }
    return (Math.max(...Object.values(result)));

}