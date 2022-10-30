- 기능 요구사항
- [x] 상품을 생성한다.
    - [x] 상품번호, 상품명, 판매가격, 재고수량
    - [x] 예외
        - [x] 판매가격이 음수
        - [x] 재고수량이 음수
- [ ] 상품을 주문한다.
    - [x] 주문 상품을 생성한다.
      - [x] 예외
        - [x] 주문 수량이 음수
        - [x] productId가 없으면
    - [ ] 상품번호, 주문수량을 입력받는다.
    - [ ] 한번에 여러개의 상품을 주문할 수 있다.
    - [ ] 반복적으로 계속 주문할 수 있다.
    - [x] 주문 금액이 `50,000`원 이하일 경우 배송료 `2,500` 원 추가
    - [ ] 주문 금액, 결제 금액 (배송비 포함) 화면에 출력
    - [ ] 예외
        - [ ] 상품의 재고가 부족하면 `SoldOutException`
- [ ] 프로그램 컨트롤
    - [ ] 데이터 불러오기
    - [ ] `o` 입력시 상품 출력 (상품번호, 상품명, 판매가격, 재고수)
    - [ ] 주문하기
        - [ ] 상품번호 입력
        - [ ] 수량 입력
    - [ ] `empty` 입력 (`space + ENTER`) 이 되었을 경우 주문이 완료되고 결제
        - [ ] 주문 내역 출력
            - [ ] 상품 리스트
            - [ ] 주문 금액
            - [ ] 지불 금액 (주문 금액 + 배송비 - 2,500) - 주문 금액 5만원 이상시 배송비 면제
    - [ ] 다시 `o`, `q` 입력 가능 상태
    - [ ] `q` 또는 `quit` 입력시 프로그램 종료
    - [ ] 예외
      - [ ] 사용자 입력 잘못되면 예외
