
const loadCart = () => {
    const cartItems = localStorage.getItem("cart");
    if (cartItems) {
        // Parse cart items from JSON to JavaScript objects
        const parsedCartItems = JSON.parse(cartItems);
        // Get the section element by ID
        const section = document.getElementById("cartSection");
        section.innerHTML = "";
        const total = document.getElementById("cartTotal");
        // Iterate over the cart items and create HTML elements

        parsedCartItems.forEach((item) => {
            addItemToCart(section, item);
        });
    }
}
$(document).ready(function () {
    loadCartCount();
    loadCart();
});

function addItemToCart(section, item) {

    getProductById(item.ProductId).then((product) => {

        const div = document.createElement("div");

        div.style.display = "flex";

        div.style.alignItems = "center";

        div.style.width = "100%";

        div.style.justifyContent = "space-between";

        // Create the inner section element

        const innerSection = document.createElement("section");

        innerSection.style.display = "flex";

        innerSection.style.alignItems = "center";

        innerSection.style.gap = "8px";

        // Create the image element

        const img = document.createElement("img");

        if (product.Image.length > 10) {
            img.src = "data:image/jpeg;base64," + product.Image;
        } else {
            img.src = "./assets/imgs/background.jpg";
        }

        img.alt = "";

        img.style.width = "100px";

        img.style.height = "100px";
        img.style.borderRadius = "8px";

        // Create the details section element

        const detailsSection = document.createElement("section");

        detailsSection.style.display = "flex";

        detailsSection.style.flexDirection = "column";

        detailsSection.style.alignItems = "self-start";

        detailsSection.style.gap = "16px";

        // Create the name span element

        const nameSpan = document.createElement("span");

        nameSpan.textContent = "Name : " + product.ProductName;

        // Create the quantity span element

        const quantitySpan = document.createElement("span");

        quantitySpan.textContent = item.Quantity + " x " + product.Price;

        // Append the name and quantity elements to the details section

        detailsSection.appendChild(nameSpan);

        detailsSection.appendChild(quantitySpan);

        // Append the image element and details section to the inner section

        innerSection.appendChild(img);

        innerSection.appendChild(detailsSection);
        const iconBox = document.createElement("button");
        iconBox.style.padding = "16px";
        iconBox.style.display = "grid";
        iconBox.style.placeItems = "center";
        iconBox.style.borderRadius = "8px";
        iconBox.style.border = "1px solid gray";
        iconBox.style.cursor = "pointer";
        iconBox.style.backgroundColor = "white";
        iconBox.classList.add("cart-delete-btn");
        const icon = document.createElement("i");
        icon.classList.add("fa-regular");
        icon.classList.add("fa-trash-can");
        icon.classList.add(item.ProductId);
        icon.style.cursor = "pointer";

        const parsedCartItems = JSON.parse(localStorage.getItem("cart"));

        iconBox.addEventListener("click", () => {
            const newCart = parsedCartItems.filter(
                (obj) => obj.ProductId !== item.ProductId
            );
            console.log(newCart);
            localStorage.setItem("cart", JSON.stringify(newCart));

            loadCartCount();
            loadCart();
        });

        // Append the inner section to the main div
        iconBox.appendChild(icon);
        div.appendChild(innerSection);
        div.appendChild(iconBox);

        // Append the main div to the section

        section.appendChild(div);
    });
}


const handleCart = (status) => {
    if (status === 'close') {
        if (document.querySelector(".cart-container").classList.contains("open-cart")) {
            document.querySelector(".cart-container").classList.remove("open-cart")
        } else {
            document.querySelector(".cart-container").classList.add("close-cart")
        }
        loadCart();

    } else {
        if (document.querySelector(".cart-container").classList.contains("close-cart")) {
            document.querySelector(".cart-container").classList.remove("close-cart")
        } else {
            document.querySelector(".cart-container").classList.add("open-cart")
        }
    }
}