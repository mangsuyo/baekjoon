function solution(cap, n, deliveries, pickups) {
    let answer = 0;
    let have_to_deli = 0;
    let have_to_pick = 0;
    for(let i = n - 1; i >= 0; i--){
        have_to_deli += deliveries[i];
        have_to_pick += pickups[i];
        while(have_to_deli > 0 || have_to_pick > 0){
            have_to_deli -= cap;
            have_to_pick -= cap;
            answer += (i + 1) * 2;
        }
    }
    return answer;
}