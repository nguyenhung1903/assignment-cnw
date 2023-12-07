
const handleCart = (status) => {
    if (status === 'close') {
        if (document.querySelector(".cart-container").classList.contains("open-cart")) {
            document.querySelector(".cart-container").classList.remove("open-cart")
        } else {
            document.querySelector(".cart-container").classList.add("close-cart")
        }
    } else {
        if (document.querySelector(".cart-container").classList.contains("close-cart")) {
            document.querySelector(".cart-container").classList.remove("close-cart")
        } else {
            document.querySelector(".cart-container").classList.add("open-cart")
        }

    }
}