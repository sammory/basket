# basket
# 요구사항 정의서 Template

## **프로젝트 주제**

- 커머스 프로젝트

## **프로젝트 구조**

![Untitled](https://github.com/sammory/basket/issues/1#issue-1508001190/Untitled.png)


## ERD

![Untitled (1)](https://user-images.githubusercontent.com/113747023/209157974-19b4bf97-06b3-4ea0-bb9b-a85ea9c772fd.png)

## 사용 기술 스택

- SpringBoot
- Java
- MySQL
- JPA

## 프로젝트 기능

- 팀이 선택한 주제별 구현해야 하는 기능에 대해 상세 요구사항을 작성해주세요.

<aside>
💡 주제별 구현 기능

- **주제 1. 커머스 과제**
    - [ ]  상품명 검색 기능
    - [ ]  상품 장바구니 기능(상품 담기/장바구니 조회/장바구니 상품 삭제)
    - [ ]  로그인 / 로그 아웃에 따른 장바구니 접근 허가 기능 구현
</aside>

**회원가입과 로그인**

- 회원가입
    - 회원가입시 이메일, 이름, 전화번호, 비밀번호 정보가 필요하다(관리자일 경우 db에서 직접 처리)
    - 회원가입시 이미 회원가입된 이메일로 회원가입을 시도하면 에러를 발생한다
- 로그인
    - 로그인시 회원가입한적 없는 이메일을 이용하여 로그인을 시도하면 에러가 발생한다
    - 로그인시 비밀번호가 일치하지 않는다면 에러가 발생한다.
    

**상품등록**

- 상품등록은 관리자만 가능하도록 한다

**장바구니 담기**

- 로그인한 유저는 상품담기를 할 수 있다
    - 로그인한 유저에게만  상품담기가 가능하록 한다

**장바구니 조회**

- 로그인한 유저는 장바구니를 기간별로 조회할 수 있다

**장바구니 상품 삭제**

- 유저는 자신이 담은 상품만 삭제할 수 있다

**장바구니 상품 수량**

- 유저는 자신이 담은 상품의 수량을 변경 할 수 있다

## **💪 주차별 개발 계획**

[주차별 개발 계획](https://www.notion.so/331a6dbb812642dc90439f639d7d6573)
