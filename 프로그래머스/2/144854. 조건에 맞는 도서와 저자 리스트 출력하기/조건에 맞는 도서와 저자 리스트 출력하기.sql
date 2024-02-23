-- 코드를 입력하세요
SELECT BOOK_ID,	AUTHOR_NAME, DATE_FORMAT(PUBLISHED_DATE, '%Y-%m-%d') FROM BOOK AS A, AUTHOR AS B
WHERE A.AUTHOR_ID = B.AUTHOR_ID AND CATEGORY = '경제'
ORDER BY PUBLISHED_DATE