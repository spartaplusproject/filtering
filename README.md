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

## 트러블 슈팅
<details>
  <summary>김수연 트러블 슈팅</summary>
  
  처음엔 JDBC 기반으로 CSV 데이터를 데이터베이스에 삽입하는 방식을 사용했습니다. 그런데 프로젝트 중반, 팀원 기능과 통합하면서 발생한 DB 관련 에러를 보고, 이게 삽입 방식의 문제라고 착각했습니다. 그래서 JPA 방식으로 리팩토링을 진행했어요. 그리고 리팩토링한 API를 실행하니 save()를 통해 1건씩 JPA로 삽입하는 방식이라, 13만 건 기준 2시간이 넘게 걸렸습니다. 이전엔 10분도 안 걸리던 작업이었는데 말이죠.

이상하다 싶어 다시 문제를 들여다봤고, 결국 원인은 삽입 방식이 아닌 Entity에 필요한 컬럼(id)이 누락되어 있었던 것이었습니다. 해당 컬럼을 추가하니 필터 기능 에러는 바로 해결됐고, 삽입 방식도 다시 JDBC로 되돌리면서 성능 문제도 해결됐습니다.
이러한 문제를 겪고 나니, 원인을 넓게 보고 검증하는 과정이 정말 중요하다는 걸 배웠습니다. 또한, 이 경험을 통해, 기술은 항상 "좋고 나쁨"이 아니라 "적재적소"가 핵심이라는 걸 다시 한 번 느끼게 되었습니다.
</details>

<details>
  <summary>김정호 트러블 슈팅</summary>
  
</details>

<details>
  <summary>반효승 트러블 슈팅</summary>
  
</details>

