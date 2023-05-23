## 1. kakao 개발자 사이트 등록
https://developers.kakao.com/product/kogpt

## 2. back 설정 파일
back/src/main/resources/ 에 application.properties or yml 파일 생성 후 아래 코드 추가

````
info:
  token: {your token}
  url: https://api.kakaobrain.com/v1/inference/kogpt/generation
````
