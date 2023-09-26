# 베이스 이미지로 Python 3.8을 사용합니다.
FROM python:3.8

# 작업 디렉토리를 설정합니다.
WORKDIR /app

# FastAPI 애플리케이션의 소스 코드를 복사합니다.
COPY . /app

# 필요한 패키지를 설치합니다.
RUN pip install -r requirements.txt

# 애플리케이션을 실행합니다.
CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "80"]
