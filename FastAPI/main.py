# FastAPI CRUD 연습

from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from typing import List
app = FastAPI()

# 메모를 저장하기 위한 데이터베이스 대체
database = []

class Memo(BaseModel):
    id: int
    content: str

# 메모 생성
@app.post("/memos/", response_model=Memo)
def create_memo(memo: Memo):
    database.append(memo)
    return memo

# 모든 메모 조회
@app.get("/memos/", response_model=List[Memo])
def read_memos(skip: int = 0, limit: int = 10):
    return database[skip : skip + limit]

# 특정 메모 조회
@app.get("/memos/{memo_id}", response_model=Memo)
def read_memo(memo_id: int):
    memo = next((m for m in database if m.id == memo_id), None)
    if memo is None:
        raise HTTPException(status_code=404, detail="Memo not found")
    return memo

# 메모 업데이트
@app.put("/memos/{memo_id}", response_model=Memo)
def update_memo(memo_id: int, updated_memo: Memo):
    existing_memo = next((m for m in database if m.id == memo_id), None)
    if existing_memo is None:
        raise HTTPException(status_code=404, detail="Memo not found")
    existing_memo.content = updated_memo.content
    return existing_memo

# 메모 삭제
@app.delete("/memos/{memo_id}", response_model=Memo)
def delete_memo(memo_id: int):
    existing_memo = next((m for m in database if m.id == memo_id), None)
    if existing_memo is None:
        raise HTTPException(status_code=404, detail="Memo not found")
    database.remove(existing_memo)
    return existing_memo