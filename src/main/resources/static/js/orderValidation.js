function orderForm() {
    const x = document.forms["orderForm"]["productId"].value;
    if (x === "") {
        alert("Товар не выбран");
        return false;
    }
}