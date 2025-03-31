## 온라인 서점 프로젝트
### 요구사항 정의서
<img width="683" alt="스크린샷 2024-01-08 235016" src="https://github.com/eeeunjiii/book-store-project/assets/91798213/ea7db68b-6d2e-4eac-9991-e6f0b8c1f697">
---

### ERD
<img width="503" alt="ERD" src="https://github.com/eeeunjiii/book-store-project/assets/91798213/9fc75e59-3724-4565-a647-d6096f80797c">

- Order ↔ Member: N:1 양방향 (Order에 user.id FK)
- Cart ↔ Member: 1:1 양방향 (Cart에 user.id FK)
- CartItem → Cart: N:1 단방향 (CartItem에 cart.id FK)
- OrderItem → Order: N:1 단방향 (OrderItem에 order.id FK)
- CartItem → Item: 1:1 단방향 (CartItem에 item.id FK)
- OrderItem → Item: 1:1 단뱡향 (OrderItem에 item.id FK)
---

### 프로젝트 구조
- controller
    - MemberController: 회원가입, 로그인
    - ItemController: 도서 목록 페이지, 도서 상세 정보 페이지 구현
    - OrderController: 주문에 대한 구현
    - CartController: 장바구니에 대한 구현
- entity
    - Member: 회원 정보
    - Item: 도서 정보
    - Cart: 장바구니 정보
    - CartItem: 장바구니에 담은 도서 정보
    - Order: 주문 정보
    - OrderItem: 주문한 도서 정보
- dto
    - MemberDto: 회원 정보를 전달할 때 사용
    - ItemDto: 도서 정보를 전달할 때 사용
    - CartDto: 장바구니 정보를 전달할 때 사용
    - CartItemDto: 장바구니에 담은 도서 정보를 전달할 때 사용
    - OrderDto: 주문 정보를 전달할 때 사용
    - OrderItemDto: 주문한 도서 정보를 전달할 때 사용
- repository
    - MemberRepository: 회원 정보를 저장, 조회, 삭제
    - ItemRepository: 도서 정보를 저장, 조회, 삭제, 수정
    - CartRepository: 장바구니 목록 조회, 도서 삭제
    - CartItemRepository: 도서 개수 수정
    - OrderRepository: 주문 목록 조회, 주문한 도서 삭제 (주문 취소)
    - OrderItemRepository: 도서 배송 상태 수정, 주문할 도서 개수 수정
- service
    - MemberService: 회원가입, 로그인 서비스
    - CartService: 장바구니 서비스, 장바구니에 도서 추가, 삭제, 조회
    - OrderService: 도서 주문 서비스, 주문한 도서 주문 취소, 주문 목록 조회
    - ItemService: 도서 전체 목록 서비스
- exception
    - 회원 가입 시 이미 존재하는 회원인 경우 발생하는 예외
    - 로그인 정보가 틀린 경우 발생하는 예외
    - 재고가 없는 도서를 장바구니에 넣었을 때 발생하는 예외
    - 재고가 없는 도서를 주문하려 할 때 발생하는 예외
    - 회원 정보 중 비밀번호를 수정하려 할 때, 기존 비밀번호가 틀렸을 때 발생하는 예외
    - 주문한 날짜로부터 7일 이상이 지났을 때 주문 취소를 하려 할 때 발생하는 예외
- constant
    - DeliveryStatus: 배송 상태 (준비중, 배송중, 배송완료, 주문 취소)
