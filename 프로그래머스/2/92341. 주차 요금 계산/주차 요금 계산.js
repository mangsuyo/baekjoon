function solution(fees, records) {
    const [basic_time, basic_fee, extra_time, extra_fee] = fees;
    const cars = {};
    for(let i = 0; i < records.length; i++){
        const [time, car_number, status] = records[i].split(" ");
        const [hour, minute] = time.split(":").map(Number);
        const stand_min = 60 * hour + minute;
        if(cars[car_number] === undefined){
            cars[car_number] = [stand_min, status];
        }
        else{
            cars[car_number] = [stand_min - cars[car_number][0], status];
        }
    }
    
    const end_time = 60 * 23 + 59;
    for(const car of Object.keys(cars)){
        if(cars[car][1] === 'IN'){
            cars[car] = end_time - cars[car][0]
        }
        else{
            cars[car] = cars[car][0];
        }
    }
    
    for(const car of Object.keys(cars)){
        if(cars[car] <= basic_time){
            cars[car] = basic_fee;
        }
        else{
            cars[car] = basic_fee + Math.ceil((cars[car] - basic_time) / extra_time) * extra_fee
        }
    }
    
    const car_names = Object.keys(cars).sort();
    
    const answer = [];
    for(const car of car_names){
        answer.push(cars[car]);
    }
    
    return answer
}