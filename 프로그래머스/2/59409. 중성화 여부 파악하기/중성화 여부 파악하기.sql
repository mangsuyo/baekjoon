-- 코드를 입력하세요
SELECT ANIMAL_ID,NAME, 
# IF (SEX_UPON_INTAKE LIKE '%Neutered%' OR '%Spayed%', 'O', 'X') AS 중성화 
IF (SEX_UPON_INTAKE LIKE '%Intact%', 'X', 'O') AS 중성화 

FROM ANIMAL_INS
ORDER BY ANIMAL_ID