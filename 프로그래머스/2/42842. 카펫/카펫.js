function solution(brown, yellow) {
    let total = brown + yellow;
    
    yellowWidthAndHeight = [];
    for(let i = 1; i <= yellow; i++){
        if(yellow % i == 0){
            yellowWidthAndHeight.push(i);
        }
    }
    
    
    for(let i = 0; i < yellowWidthAndHeight.length; i++){
        const heigth = yellowWidthAndHeight[i];
        const width = yellowWidthAndHeight[yellowWidthAndHeight.length - 1 - i];
        let brownWidth = width + 2;
        let brownHeight = heigth + 2;
        if(total == (brownWidth * brownHeight)){
            return [brownWidth, brownHeight];
        }        
    }
}