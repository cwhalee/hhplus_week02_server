# 🎵 콘서트 예약 서비스 — 서버 설계 문서 (1주차 과제)

본 레포지토리는 항해 플러스 1주차 과제인  
콘서트 예약 서비스의 서버 설계 문서(API, ERD, INFRA) 입니다.

---

# 📚 1. 프로젝트 개요

콘서트 좌석 예약 과정에서 발생할 수 있는  
**동시성 문제(중복 예약 방지), 대기열 처리, 포인트 결제 흐름**을  
효율적으로 설계하기 위한 기초적인 서버 아키텍처 설계 문서입니다.

본 프로젝트에서는 아래 3가지 문서를 작성했습니다.

- 📄 **API 명세서** (`docs/api.md`)
- 🧩 **ERD** (`docs/images/erd.png`)
- 🏗️ **인프라 구성도** (`docs/INFRA.md`)

---

# 📄 2. API 명세서 요약

자세한 문서는 👉 `docs/api.md`

| 기능 | Method | Endpoint |
|------|--------|----------|
| 예약 가능 날짜 조회 | GET | /concerts/dates |
| 좌석 조회 | GET | /concerts/{date}/seats |
| 좌석 예약 요청(임시 배정) | POST | /reservations |
| 포인트 충전 | POST | /users/{id}/points/charge |
| 포인트 조회 | GET | /users/{id}/points |
| 결제(좌석 확정) | POST | /reservations/{id}/pay |

---

# 🧩 3. ERD

👉 ERD 파일: `docs/images/erd.png`

**핵심 엔티티**

- User  
- Seat  
- Reservation  
- PointHistory  
- QueueToken

좌석 임시 배정(TTL)과 예약 확정을 반영한 구조이며  
좌석 중복 예약을 방지하기 위해 각 엔티티에 상태(status) 및 FK/PK 관계를 정의했습니다.

---

# 🏗️ 4. 인프라 구성도

👉 INFRA 파일: `docs/INFRA.md`  

구조:

```
Client
   │
   ▼
Load Balancer
   │
   ▼
Spring Boot Application
   │
   ├── MySQL (예약·포인트·좌석 데이터)
   └── Redis (좌석 임시 배정 TTL, 대기열)
```

**구성 요소**

- **Application (Spring Boot)**  
  - 예약/결제/좌석 조회 등 핵심 로직 처리

- **MySQL**  
  - 예약/좌석/포인트 데이터 저장  
  - 트랜잭션으로 좌석 중복 예약 방지

- **Redis**  
  - 좌석 임시 배정 TTL 저장  
  - 대기열 토큰 관리  
  - 빠른 조회 및 동시성 제어 담당

- **Load Balancer**  
  - 트래픽 분산 및 헬스 체크

---

# 🧪 5. 설계 시 고려할 부분

- **좌석 중복 예약 방지**  
  - Redis Lock + TTL + DB 트랜잭션 고려

- **대기열 처리 설계**  
  - QueueToken 기반 인증  
  - Redis 자료구조로 순서 보장

- **확장 가능한 아키텍처 구성**  
  - Stateless API + 캐시/DB 분리
  - 수평 확장 가능한 구조

- **문서의 일관성 유지**  
  - API → ERD → Infra 구조가 자연스럽게 연결되도록 설계

---

# 📁 6. 문서 구조

```
docs/
 ├── api.md
 ├── INFRA.md
 └── images/
      └── erd.png
```


