-- 코드를 작성해주세요
SELECT A.ITEM_ID, ITEM_NAME FROM ITEM_INFO A, ITEM_TREE B
WHERE B.PARENT_ITEM_ID IS NULL AND B.ITEM_ID = A.ITEM_ID