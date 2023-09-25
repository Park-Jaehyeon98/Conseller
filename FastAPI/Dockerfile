FROM python:3.8

COPY ./requirements.txt /FastAPI/requirements.txt

COPY ./.env /FastAPI/.env

WORKDIR /FastAPI

RUN pip install --upgrade pip

RUN echo "Acquire::Check-Valid-Until \"false\";\nAcquire::Check-Date \"false\";" | cat > /etc/apt/apt.conf.d/10no--check-valid-until

# 패키지 업데이트 및 필요한 도구 설치
# 패키지 업데이트 및 필요한 도구 설치
RUN apt-get update && \
    apt-get install -y \
    build-essential \
    make \
    gcc \
    g++ \
    cmake


RUN pip install --no-cache-dir --upgrade -r /FastAPI/requirements.txt

COPY ./FastAPI /FastAPI

CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8000"]