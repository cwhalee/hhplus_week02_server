# API 명세서 - 콘서트 예약 서비스

## 1. 콘서트 조회 API
### Endpoint
GET /concerts
### Description
예매 가능한 콘서트 목록을 조회한다.
### Parameters
없음.
### Response
- Status : 200(OK)
```json
[
  {
    "concertId": 1,
    "title": "Coldplay, MUSIC OF THE SPHERES DELIVERED BY DHL",
    "genre": "Pop",
    "venue": {
      "venueId": 10,
      "name": "서울 올림픽체조경기장"
    }
  },
  {
    "concertId": 2,
    "title": "Maroon 5, INSIPIRE CONCERT SERIES",
    "genre": "Rock",
    "venue": {
      "venueId": 12,
      "name": "인스파이어 아레나"
    }
  },
  {
    "concertId": 3,
    "title": "Oasis, Live '25 SOUTH KOREA",
    "genre": "Rock",
    "venue": {
      "venueId": 15,
      "name": "서울월드컵경기장"
    }
  }
]
```

## 2. 스케줄 조회 API
### Endpoint
GET /concerts/{concertId}/schedules
### Description
특정 콘서트의 날짜와 해당 날짜의 회차를 조회한다.
### Parameters
concertId : 콘서트 ID
### Response
- Status : 200(OK)
```json
[
  {
    "scheduleId": 101,
    "date": "2025-12-24",
    "showNumber": 1,
    "startTime": "18:00"
  },
  {
    "scheduleId": 102,
    "date": "2025-12-24",
    "showNumber": 2,
    "startTime": "20:30"
  }
]
```

## 3. 좌석 조회 API
### Endpoint
GET /concerts/{concertId}/schedules/{scheduleId}/seats
### Description
특정 회차의 좌석을 조회한다.
### Parameters
concertId : 콘서트 ID  
scheduleId : 스케줄(회차) ID
### Response 
- Status : 200(OK)
```json
[
  {
    "seatId": 1,
    "section": "A",
    "row": 1,
    "number": 10,
    "price": 150000,
    "status": "AVAILABLE"
  },
  {
    "seatId": 2,
    "section": "A",
    "row": 1,
    "number": 11,
    "price": 150000,
    "status": "TEMP_HOLD"
  },
  {
    "seatId": 3,
    "section": "A",
    "row": 1,
    "number": 12,
    "price": 150000,
    "status": "RESERVED"
  }
]
```

## 4. 좌석 예약 요청 API
### Endpoint
POST /reservations
### Description
특정 스케줄의 좌석을 임시 배정(예약 요청)한다.
### Parameters
userId : 유저 ID  
scheduleId : 스케줄 ID  
seatNumber : 좌석 번호 (1 ~ 50)
### Response
- Status : 201(CREATED)
  (임시 배정된 예약 정보 JSON 예시 나중에 추가)

---

## 5. 포인트 충전 API
### Endpoint
POST /users/{userId}/points/charge
### Description
유저가 콘서트 결제를 위해 포인트를 충전한다.
### Parameters
userId : 유저 ID  
body.amount : 충전 금액
### Response
- Status : 200(OK)

---

## 6. 포인트 조회 API
### Endpoint
GET /users/{userId}/points
### Description
유저의 현재 포인트 잔액을 조회한다.
### Parameters
userId : 유저 ID
### Response
- Status : 200(OK)

---

## 7. 결제 API
### Endpoint
POST /reservations/{reservationId}/pay
### Description
임시 배정된 좌석에 대해 결제를 수행하고, 결제가 완료되면 좌석 소유권을 확정한다.
### Parameters
reservationId : 예약 ID
### Response
- Status : 200(OK)

---

## 8. 대기열 토큰 발급 API (심화 과제)
### Endpoint
POST /queue/token
### Description
유저가 서비스를 이용하기 위한 대기열 토큰을 발급받는다.
### Parameters
userId : 유저 ID
### Response
- Status : 201(CREATED)

## 9. 대기열 조회 API (심화 과제)
### Endpoint
GET /queue/status?token={token}
### Description
본인의 대기 순서/남은 시간을 조회한다.
### Parameters
token : 대기열 토큰
### Response
- Status : 200(OK)
