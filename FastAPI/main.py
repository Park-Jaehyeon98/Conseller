# FastAPI CRUD 연습

from fastapi import FastAPI, HTTPException, UploadFile
from pydantic import BaseModel
from fastapi.responses import JSONResponse
from typing import List
import sys
import pytesseract
import os
app = FastAPI()

UPLOAD_DIR = "images/"

# 메모를 저장하기 위한 데이터베이스 대체
database = []

class Memo(BaseModel):
    id: int
    content: str

# 이미지 전송
@app.post("/gifticon/")
def create_image(image: UploadFile):
    try:
        file_path = f"{UPLOAD_DIR}{image.filename}"

      

        with open(file_path, "wb") as image_file:
                image_file.write(image.file.read())

        print("message: 이미지 업로드가 완료되었습니다.")
        config = ('-l kor+eng --oem 3 --psm 11')

        # print(pytesseract.image_to_string(file_path, lang='eng'))
        print(pytesseract.image_to_string(file_path, config=config))

        if os.path.exists(file_path):
            os.remove(file_path)
            print(f"message '{image.filename}' 삭제 완료.")
        else:
            print(f"error: '{image.filename}'을 찾을 수 없습니다.")
        
        return JSONResponse(content={"message": "이미지 업로드 후 삭제가 완료되었습니다."}, status_code=200)
    except Exception as e:
        return JSONResponse(content={"error": str(e)}, status_code=500)


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