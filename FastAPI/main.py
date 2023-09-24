from fastapi import FastAPI, HTTPException, UploadFile
from pydantic import BaseModel
from fastapi.responses import JSONResponse
from typing import List
import pytesseract
from PIL import Image
import io

app = FastAPI()

def crop_img(image_file):
    try:
        image = Image.open(image_file)

        width, height = image.size
        top = 0
        top_bottom = int(height * 0.44)
        bottom = height

        left = 0
        right = width

        # 이미지 자르기
        top_cropped_image = image.crop((left, top, right, top_bottom))
        bottom_cropped_image = image.crop((left, top_bottom, right, bottom))

        # 새로운 파일로 저장
        top_cropped_image.save("./top_crop/cropped_image.jpg")
        bottom_cropped_image.save("./bottom_crop/cropped_image.jpg")

        ocr_result = perform_ocr()
                                 
        # 성공적으로 저장되면 경로 반환
        return ocr_result

    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))
    
def crop_giftishow(image_file):
    try:
        image = Image.open(image_file)

        width, height = image.size
        top = 0
        top_bottom = int(height * 0.5)
        bottom = height

        left = 0
        right = width

        # 이미지 자르기
        top_cropped_image = image.crop((left, top, right, top_bottom))
        bottom_cropped_image = image.crop((left, top_bottom, right, bottom))

        # 새로운 파일로 저장
        top_cropped_image.save("./top_crop/cropped_image.jpg")
        bottom_cropped_image.save("./bottom_crop/cropped_image.jpg")

        ocr_result = perform_ocr()

        return ocr_result
                                 
        # 성공적으로 저장되면 경로 반환

    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))

# OCR 함수 정의
def perform_ocr():
    try:
        # 이미지를 PIL Image로 열기
        image = Image.open("./bottom_crop/cropped_image.jpg")
        
        # OCR 수행
        config = ('-l kor+eng --oem 3 --psm 11')
        ocr_result = pytesseract.image_to_string(image, config=config)
        ocr_result = ocr_result.replace("\n", " ")

        result = ocr_result.split()
        
        
        for i in range(0, len(result)):
            if len(result[i]) == 4 and (result[i] == "0525" or result[i] == "Gs25" or result[i] == "6525"):
                print(result[i])
                result[i] = "GS25"
            elif len(result[i]) == 9 and result[i] == "0525[금액권]":
                result[i] = "GS25[금액권]"
            elif len(result[i]) == 1 and (result[i] == "「" or result[i] == "7" or result[i] == "1"):
                result[i] = "T"
            elif len(result[i]) == 3 and result[i] == "타벅스":
                result[i] = "스타벅스"

        return result
    except Exception as e:
        raise HTTPException(status_code=500, detail=str(e))

# 이미지 업로드 및 OCR 수행
@app.post("/gifticon")
def ocr_image(image: UploadFile):
    try:
        result = ""
        result = crop_img(image.file)
        for i in range(len(result)):
            if len(result[i]) == 4 and result[i] == "기프티쇼":
                result = crop_giftishow(image.file)
                break
        
        return JSONResponse(content={"message": "OCR 결과", "text": result}, status_code=200)
    except HTTPException as e:
        return JSONResponse(content={"error": e.detail}, status_code=e.status_code)