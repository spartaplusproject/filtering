# 서울시 인터넷 쇼핑몰 현황 프로젝트
소개 : 대용량 CSV 파일을 DATABASE에 저장하여 원하는 조건으로 필터링해서 사용자에게 보여주는 서비스입니다.

---
## 프로젝트 핵심 목표
1. 필터링 기능 제대로 기능하게 구현
2. 성능 최적화 성공하기

## 기능
#### IntelliJ Ultimate 또는 DBeaver를 사용하여 CSV 파일을 database 테이블에 입력하기
- IntelliJ Ultimate 사용해서 csv 파일로 데이터베이스 테이블을 생성합니다.

<details>
<summary>csv파일 DB에 업로드</summary>

1. mysql 서버 연결
2. schema.sql 실행
3. database - localhost - 임의의 스키마 - seoul_internet_shopping_mall_status table 우클릭
4. import/export - import data from files 선택
5. seoul_internet_shopping_mall_status.csv 선택
6. ok 누르기

</details>

#### 업체 리스트 조회 중 필터 기능
- ‘전체평가’ 필터 조회와 ‘업소상태’ 필터 조회(2개 필터 동시 적용, 각각 1개씩 적용) 상위 10개 리스트 보여주기를 만들었습니다.

#### Pageable 기반 업체 리스트 조회 
- ‘전체평가’ 필터 조회와 ‘업소상태’ 필터 조회(2개 필터 동시 적용) 10개씩 리스트를 보여주는 API 를 만들었습니다.
  
#### csv를 database에 입력하는 코드 만들기
- /collection 라는 API 를 통해 서버 내 특정 위치의 csv 파일을 1개 행씩 읽어서 Database에 차례대로 insert 하는 로직을 구현했습니다

#### QueryDSL 을 사용한 커서 기반 페이지네이션 및 필터
- 커서 기반 페이지네이션을 적용해 ‘전체평가’ 필터 조회와 ‘업소상태’ 필터 조회(2개 필터 동시 적용) 10개씩 리스트 보여주는 API를 만들었습니다.

#### csv를 database에 입력하는 코드 개선하기
- 100개씩 읽어서 Database에 insert하는 로직으로 개선했습니다. 실행시간이 약 1분정도 더 줄었습니다.

#### csv를 database에 입력하는 코드 더 개선하기 ( 도전기능 최적화(Indexing) )
- 100개에서 500개씩 읽어서 Database에insert 하는 로직으로 개선했습니다. 실행시간이 약 2초정도 더 줄었습니다.

<details>
  <summary>개발 환경</summary>
Java 17

Spring Boot 3.x

MySQL

Apache Commons CSV

IntelliJ Ultimate
</details>


## 역할 분담

| 이름   | 포지션   | 담당 기능                                                                                                           | Github 링크                       |
|--------|----------|-----------------------------------------------------------------------------------------------------------------------------|-----------------------------------|
| 김수연 | 리더     | 도전기능 최적화, csv를 database에 입력하는 코드 만들기, csv를 database에 입력하는 코드 개선하기, IntelliJ Ultimate 또는 DBeaver를 사용하여 CSV 파일을 database 테이블에 입력하기 | https://github.com/suyeonkim56 |
| 김정호 | 팀원   | 업체 리스트 조회 중 필터 기능, Pageable 기반 업체 리스트 조회                          | https://github.com/computer-jungho |
| 반효승 | 팀원     | QueryDSL 을 사용한 커서 기반 페이지네이션 및 필터                                            | https://github.com/hyos1    |

---
