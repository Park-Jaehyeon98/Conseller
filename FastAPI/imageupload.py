# FastAPI CRUD 연습

from fastapi import FastAPI, HTTPException, UploadFile
from pydantic import BaseModel
from fastapi.responses import JSONResponse, FileResponse
from typing import List
import io
import sys
import pytesseract
import os
app = FastAPI()

UPLOAD_DIR = "images/"

# 이미지 전송
@app.post("/gifticon/")
def create_image(image: UploadFile):
    def image_endpoint(*, vector):
    # Returns a cv2 image array from the document vector
    cv2img = my_function(vector)
    res, im_png = cv2.imencode(".png", cv2img)
    return StreamingResponse(io.BytesIO(im_png.tobytes()), media_type="image/png")

