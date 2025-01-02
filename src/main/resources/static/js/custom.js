function parseQuantity() {
    const itemId=document.getElementById("id").value;
    const quantity=document.getElementById("quantity").textContent;

    const json={
        quantity: quantity
    };

    fetch(`/items/${itemId}/cart/add`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(json)
    })
    .then(response => {
        if(response.ok) {
            return response.text();
        }
        throw new Error("장바구니 추가에 실패했습니다.");
    })
    .then(data => {
            console.log('장바구니에 추가되었습니다:', data);
            // 성공적인 응답 후 알림 처리 등 추가 작업
    })
    .then(error => {
        console.error('장바구니 추가 중 오류 발생: ', error);
    })
}