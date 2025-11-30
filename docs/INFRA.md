# INFRA.md — 콘서트 예약 서비스 인프라 구성도

## 1. 전체 구성

본 서비스는 대기열, 예약, 포인트 결제 기능을 안정적으로 제공하기 위해 다음 요소들로 구성됩니다:

- Spring Boot Application (API 서버)
- MySQL (예약/포인트/좌석 데이터 저장)
- Redis (대기열 토큰·좌석 임시배정 TTL 저장)
- Load Balancer (트래픽 분산)
- Reverse Proxy / Nginx (선택)
- Docker 기반 실행 환경

## 2. 인프라 아키텍처 다이어그램

```
Client
   │
   ▼
Load Balancer
   │
   ▼
Nginx (Optional)
   │
   ▼
Spring Boot Application (Containers)
   ├── Instance 1
   ├── Instance 2
   └── Instance N
   │
   ├── MySQL (예약/포인트 저장)
   └── Redis (좌석 락 · 대기열)
```

## 3. 구성 요소 설명

### 1) Application (Spring Boot)
- 핵심 비즈니스 로직 처리 (대기열, 예약, 결제)
- 다중 인스턴스로 실행되어 수평 확장 가능
- 트래픽 증가 시 Load Balancer로 분산 처리

### 2) Database – MySQL
- 예약, 좌석, 포인트, 결제 내역을 저장
- 트랜잭션 기반으로 좌석 중복 예약 방지

### 3) Cache – Redis
- 대기열 토큰 저장 및 순번 관리
- 좌석 임시배정 TTL 관리 (5분), 분산 락 처리

### 4) Load Balancer
- 여러 서버 인스턴스로 트래픽 자동 분산
- 장애 인스턴스 헬스체크 제거

### 5) Logging / Monitoring
- 예약 실패 · 결제 오류 등 주요 이벤트 로깅
- Grafana/CloudWatch 등으로 지표 관찰
