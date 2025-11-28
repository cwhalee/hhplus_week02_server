# 🎵 콘서트 예약 서비스 — 서버 설계 문서 (1주차 과제)

본 레포지토리는 항해 플러스 1주차 과제인  
**콘서트 예약 서비스의 서버 설계 문서(API, ERD, INFRA) 입니다.

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
- QueueToken(선택 기능)  

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

# 🧪 5. 설계 시 고려한 점

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

# 🙋‍♂️ 6. 작업하며 느낀 점

처음으로 서버 설계 문서를 작성해보면서 다음과 같은 점을 배웠습니다:

1. **API 명세는 기능 나열이 아니라 흐름을 연결하는 작업이라는 것**  
2. **ERD는 데이터 구조를 명확히 하고 서비스 흐름을 뒷받침하는 핵심이라는 것**  
3. **INFRA는 “서비스가 어떤 환경에서 돌아가는지” 설명하는 매우 중요한 문서라는 점**  
4. **구현 없이도 시스템 구조를 그려보는 경험 자체가 큰 학습이었다는 것**

이번 과제를 통해 ‘기능 단위 사고’에서 **‘서비스 단위 사고’**로 전환되는 좋은 경험을 했습니다.

---

# 📁 7. 문서 구조

```
docs/
 ├── api.md
 ├── INFRA.md
 └── images/
      └── erd.png
```

---

# 🙏 8. 리뷰 요청

- 기본 과제 3개 문서(API, ERD, INFRA)가 요구사항을 충족하는지  
- ERD 및 인프라 구조에 보완이 필요한 점이 있는지  
- 문서 간 연결성이 자연스러운지  

피드백 환영합니다!  
감사합니다 🙏
