# 스프링 부트를 사용한 게시판 API 만들기
공부한 것들을 토대로 게시판 구현

### 사용한 기술
Back-End : SpringBoot, JPA, Junit5, Gradle<br>
Front-End : thymeLeaf, Javascript, html/css<br>
Database : H2(추후 DB 변경예정)

thymeLeaf를 통한 View 작성 <br>
배운 내용을 토대로 테스트 코드 작성

## API 설명
- 글 목록 : `GET` `/api/v1/board`
- 글 상세 : `POST` `/api/v1/board/{id}`
- 글 등록 : `POST` `/api/v1/board/save`
- 글 수정 : `PUT` `/api/v1/board/{id}`
- 글 삭제 : `DELETE` `/api/v1/board/{id}`

(추후)업데이트 예정

## ViewPage
- 글 목록 : `URL` `/`
- 글 등록 : `URL` `/saveBoard`
- 글 상세 : `URL` `/updateBoard/{id}`

(추후)업데이트 예정
