import unicodedata
from fastapi import FastAPI, File, Form, HTTPException, UploadFile
from pydantic import BaseModel
from fastapi.responses import JSONResponse, FileResponse
from typing import List
import pytesseract
from PIL import Image
import base64
from starlette.datastructures import UploadFile as StarletteUploadFile
import sys
import re
import logging

def isHangul(text):
    #Check the Python Version
    pyVer3 =  sys.version_info >= (3, 0)

    if pyVer3 : # for Ver 3 or later
        encText = text
    else: # for Ver 2.x
        if type(text) is not unicodedata:
            encText = text.decode('utf-8')
        else:
            encText = text

    hanCount = len(re.findall(u'[\u3130-\u318F\uAC00-\uD7A3]+', encText))
    return hanCount > 0

app = FastAPI()

from starlette.middleware.cors import CORSMiddleware

origins = [
    "https://j9b207.p.ssafy.io",
    "http://localhost:8000",
    "http://localhost",
    "http://localhost:443",
    "http://localhost:80",
    "http://0.0.0.0:8000",
    "http://0.0.0.0",
]

app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)


class Item(BaseModel):
    category : int

def crop_kakao(image_file):
    try:
        image = Image.open(image_file)

        width, height = image.size
        top = int(height * 0.05)
        top_bottom = int(height * 0.44)
        bottom = height

        left = int(width*0.08)
        right = width - left

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
    
def crop_ssafy(image_file):
    try:
        image = Image.open(image_file)

        width, height = image.size
        top = 0
        top_bottom = int(height * 0.42)
        bottom = height

        left = int(width//2)
        right = int(width*0.95)

        # 이미지 자르기
        top_cropped_image = image.crop((left, top, right, top_bottom))
        bottom_cropped_image = image.crop((0, top_bottom, right, bottom))

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
        top = int(height * 0.05)
        top_bottom = int(height * 0.5)
        bottom = height

        left = int(width * 0.05)
        right = int(width*0.48)

        # 이미지 자르기
        top_cropped_image = image.crop((left, top, right, top_bottom))
        bottom_cropped_image = image.crop((left, top_bottom, width, bottom))

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
            if len(result[i]) == 4:
                if result[i] == "0525" or result[i] == "Gs25" or result[i] == "6525":
                    result[i] = "GS25"
            elif len(result[i]) == 7 and result[i] == "ㄷ4[금액권]":    
                result[i] = "CU[금액권]"
            elif len(result[i]) == 9 and result[i] == "0525[금액권]":
                result[i] = "GS25[금액권]"
            elif len(result[i]) == 1 and (result[i] == "「" or result[i] == "7" or result[i] == "1"):
                result[i] = "T"
            elif len(result[i]) == 3 and result[i] == "타벅스":
                result[i] = "스타벅스"
            elif result[i] == "피머니":
                result[i] = "헤피머니"
            elif result[i] == "피머니온라인상품권[3,000원]":
                result[i] = "해피머니온라인상품권[3,000원]"

        return result
    except Exception as e:
        raise HTTPException(status_code=504, detail=str(e))

# 이미지 업로드 및 OCR 수행
@app.post("gifticon")
def ocr_image(category: int = Form(...), image: UploadFile = File(...)):
    logging.info("post, def진입 ")
    try:
        logging.info("try 진입")
        result = ""
        barcode_idx = 0
        name_idx = 0
        end_date_idx = 0
        # JSON 데이터와 이미지 데이터를 함께 반환
        gifticon_barcode = ""
        gifticon_name = ""
        gifticon_end_date = ""

        # 카카오톡0,  기프티쇼1, 싸피 2
        if category == 0:
            result = crop_kakao(image.file)

            for idx, text in enumerate(result):
                if text == "교환처" or text == "환처":
                    barcode_idx = idx
                elif text == "유효기간" or text == "효기간" or text == "기간":
                    end_date_idx = idx+1

            if len(result[barcode_idx-4]) == 4 and result[barcode_idx-4].isdigit():
                if(result[barcode_idx-4] != "1111" and result[barcode_idx-4] != "1110" and result[barcode_idx-4] != "1100" and result[barcode_idx-4] != "1000"):
                    gifticon_barcode += result[barcode_idx-4]
            
            if barcode_idx == 0:
                barcode_idx = end_date_idx - 2
                for i in range(3, -1, -1):
                    if len(result[barcode_idx-i]) < 5 and result[barcode_idx-i].isdigit():
                        gifticon_barcode += result[barcode_idx-i]
            else:
                for i in range(3, 0, -1):
                    if result[barcode_idx-i].isdigit():
                        gifticon_barcode += result[barcode_idx-i]

            for i in range(2):
                gifticon_end_date += result[end_date_idx + i][:-1] + "."

            gifticon_end_date += result[end_date_idx + 2][:-1]

            if barcode_idx != 0:
                for i in range(barcode_idx):
                    if result[i] == "ㅣ":
                        continue
                    elif isHangul(result[i]) or result[i] == "T":
                        gifticon_name += result[i] + " "
                    elif result[i][0] == "(" and result[i][-1] == ")":
                        gifticon_name += result[i] + " "

        elif category == 1:
            result = crop_giftishow(image.file)
            name_end_idx = 0
            for idx, text in enumerate(result):
                if text == "사용처" or text == "용처" or text == "교환처" or text == "환처":
                    name_end_idx = idx
                elif text == "~":
                    end_date_idx = idx+1
                elif text == "유효기간" or text == "효기간" or text == "기간":
                    end_date_idx = idx+1
                elif text == "상품명":
                    barcode_idx = idx - 1
                    name_idx = idx

            for i in range(name_idx + 1, name_end_idx):
                gifticon_name += result[i] + " "

            # gifticon_name = gifticon_name[:-1]

            for i in range(4, 0, -1):
                if(len(result[barcode_idx-i]) == 4 and result[barcode_idx-i].isdigit()):
                    gifticon_barcode += result[barcode_idx-i]

            gifticon_end_date = result[end_date_idx]

        elif category == 2:
            result = crop_ssafy(image.file)
            name_end_idx = 0
            for idx, text in enumerate(result):
                if text == "교환처" or text == "환처":
                    barcode_idx = idx
                elif text == "~":
                    end_date_idx = idx+1
                elif text == "유효기간" or text == "효기간" or text == "기간":
                    end_date_idx = idx
                elif text == "상품명":
                    name_idx = idx
                elif text == "수량":
                    name_end_idx = idx

            if barcode_idx+1 != name_idx:
                for i in range(barcode_idx+1, name_idx):
                    gifticon_name += result[i] + " "
            else:
                for i in range(name_idx+1, name_end_idx):
                    gifticon_name += result[i] + " "

            if len(result[-1]) >= 12 and result[-1].isdigit():
                gifticon_barcode += result[-1]
            elif len(result[-1]) == 4 and result[-1].isdigit():
                gifticon_barcode += result[-3] + result[-2] + result[-1]

            if len(result[end_date_idx]) == 8:
                gifticon_end_date = "20" + result[end_date_idx]
            else:
                gifticon_end_date = "20" + result[end_date_idx-1]

        else:
            raise HTTPException(status_code=400, detail="카테고리 값이 유효하지 않습니다.")

        gifticon_name = gifticon_name[:-1]
        # 이미지 파일을 Base64로 인코딩
        with open("./top_crop/cropped_image.jpg", "rb") as image_file:
            image_data = base64.b64encode(image_file.read()).decode()

        data = {
            "result": result,
            "gifticonBarcode": gifticon_barcode,
            "gifticonName": gifticon_name,
            "gifticonEndDate": gifticon_end_date,
            "gifticonCropImage": image_data  # Base64로 인코딩된 이미지 데이터
        }

        return data  # JSON 데이터와 이미지 데이터를 함께 반환

    except HTTPException as e:
        raise HTTPException(status_code=e.status_code, detail=e.detail)