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

function parseOrderItemFromItem() {
    const itemId=document.getElementById("id").value;
    const quantity=document.querySelector(".data-quantity").innerText;;

    const url=`/items/order?itemId=${itemId}&quantity=${quantity}`;
    window.location.href=url;
}

function parseOrderItemFromCart() {
    const json=[];
    const items=document.querySelectorAll(".data-item");

    items.forEach((item) => {
        const id=item.getAttribute("data-id").trim();
        const quantity=item.nextElementSibling.getAttribute("data-quantity").trim();

        json.push({
            itemId: parseInt(id, 10),
            quantity: parseInt(quantity, 10),
        });
    });

    fetch(`/items/cart/order`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(json)
    })
    .then(response => response.json())
    .then(result => console.log(result))
    .catch(error => console.log('Error: ', error))
}

document.addEventListener('DOMContentLoaded', () => {
    document.querySelectorAll('.item-checkbox').forEach((checkbox) => {
        console.log('Checkbox found: ', checkbox);
        checkbox.addEventListener('change', (event) => {
            const itemId=event.target.dataset.id;
            const quantityElement=event.target.closest('.cart-item').querySelector('.data-quantity');
            const quantity = quantityElement ? parseInt(quantityElement.dataset.quantity, 10) : 0;

            const titleElement = event.target.closest('.cart-item').querySelector('.data-item a');
            const title = titleElement.dataset.title;

            console.log('title: ', title);

            const json={
                itemId: parseInt(itemId, 10),
                quantity: quantity,
                title: title
            };

            console.log("json: ", json);

            if(event.target.checked) {
                fetch(`/items/add/order`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(json),
                }).then((response) => {
                    if(response.ok) {
                        console.log('Success add to order');
                    }
                }).catch((error) => {
                    console.log('Error adding item to order:', error);
                });
            } else {
                fetch(`/items/delete/order`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(json),
                }).then((response) => {
                    if(response.ok) {
                        console.log('Success remove from order');
                    }
                }).catch((error) => {
                    console.log('Error removing item from order:', error);
                });
            }
        });
    });
})

function deleteCartItem(event) {
    event.preventDefault();
    const itemId=event.target.dataset.id;

    if(confirm('정말로 이 항목을 삭제하시겠습니까?')) {
        const cartItem=event.target.closest('.cart-item');
        const itemPrice=parseInt(
            cartItem.querySelector('#price').textContent
                    .replace('가격: ', '')
                    .replace(',', '')
                    .replace('원', '')
                    .trim()
        );
        const quantity=parseInt(cartItem.querySelector('.data-quantity').dataset.quantity);

        fetch(`/items/${itemId}/cart/delete`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },

        }).then((response) => {
            if(response.ok) {
                console.log('Success remove from cart');
                cartItem.remove();
                updateTotalPrice(-itemPrice*quantity);
            } else {
                console.log("Failed to remove from cart");
            }
        }).catch((error) => console.error('Error:', error));
    }
}

document.querySelectorAll('.delete-item').forEach((deleteButton) => {
    deleteButton.addEventListener('click', deleteCartItem);
})

function updateTotalPrice(change) {
    const totalPriceElement=document.querySelector('.cart-total-price');
    let currentTotal=parseInt(totalPriceElement.textContent
            .replace(',', '')
            .replace(' 원', '')
            .trim());

    currentTotal+=change;
    totalPriceElement.textContent=new Intl.NumberFormat().format(currentTotal)+' 원';
}