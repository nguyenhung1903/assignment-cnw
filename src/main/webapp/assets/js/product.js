getAllProducts = async () => {
    return await $.ajax({
        url: "./api/Product/getAllProductIds",
        type: "GET",
        success: function (data) {
            return data
        },
        error: function (error) {
            console.log(error)
        }
    });
}

getAllProducts().then(res => {
    renderProduct(res)
})

const renderProduct = (data) => {
    const productBox = document.querySelector("#products")
    productBox.innerHTML = "";
    data.forEach(item => {
        const div = document.createElement("div");
        div.style.cssText = 'display: flex; flex-direction: column; gap: 8px; color:white;';
        div.classList.add("grid-item");

        const img = document.createElement("img");
        img.style.cssText = 'border-top-right-radius: 8px; border-top-left-radius: 8px; width: 100%; height: 100%;';
        img.src = './assets/imgs/background.jpg';
        if (item.Image.length > 10) {
            img.src = "data:image/jpeg;base64," + item.Image;
        }
        div.appendChild(img);

        const priceSpan = document.createElement("span");
        priceSpan.style.cssText = 'padding: 8px 16px 0px 16px;';
        priceSpan.textContent = "$" + item.Price;
        div.appendChild(priceSpan);

        const section = document.createElement("section");
        section.style.cssText = 'display: flex; align-items: center; justify-content: space-between; padding: 8px 16px;';
        div.appendChild(section);

        const nameSpan = document.createElement("span");
        nameSpan.textContent = item.ProductName;
        section.appendChild(nameSpan);

        const itemIdSpan = document.createElement("span");
        itemIdSpan.style.display = 'none';
        itemIdSpan.id = 'item-id';
        itemIdSpan.textContent = item.ProductId;
        section.appendChild(itemIdSpan);

        const itemCardSection = document.createElement("section");
        itemCardSection.classList.add("item-card");
        section.appendChild(itemCardSection);

        const incrementSpan = document.createElement("span");
        incrementSpan.classList.add("increment");
        incrementSpan.style.cssText = 'padding: 4px 12px; border: 1px solid white; background: transparent; cursor: pointer;';
        incrementSpan.textContent = '+';
        itemCardSection.appendChild(incrementSpan);

        const valueSpan = document.createElement("span");
        valueSpan.classList.add("value");
        valueSpan.style.cssText = 'padding: 4px 12px; border: 1px solid white; background: transparent;';
        valueSpan.textContent = '1';
        itemCardSection.appendChild(valueSpan);

        const decrementSpan = document.createElement("span");
        decrementSpan.classList.add("decrement");
        decrementSpan.style.cssText = 'padding: 4px 12px; border: 1px solid white; background: transparent; cursor: pointer;';
        decrementSpan.textContent = '-';
        itemCardSection.appendChild(decrementSpan);

        const btnSection = document.createElement("section");
        btnSection.style.cssText = 'padding: 16px;';
        div.appendChild(btnSection);

        const btnCart = document.createElement("button");
        btnCart.classList.add("btn", "btnCart");
        btnCart.style.cssText = 'background: #f29a51; border: none; width: 100%;';
        btnSection.appendChild(btnCart);

        const cartIcon = document.createElement("i");
        cartIcon.classList.add("fa", "fa-cart-shopping");
        btnCart.appendChild(cartIcon);

        productBox.appendChild(div);
    });

    if (JSON.parse(localStorage.getItem('cart')) && JSON.parse(localStorage.getItem('cart')).length >= 1) {
        document.querySelector("#cartCount").style.display = "grid"
        document.querySelector("#cartCount").textContent = JSON.parse(localStorage.getItem('cart')).length
    } else if (JSON.parse(localStorage.getItem('cart')) && JSON.parse(localStorage.getItem('cart')).length === 0) {
        document.querySelector("#cartCount").style.display = "none"
    }

    const cartItems = localStorage.getItem('cart');
    if (cartItems) {

        // Parse cart items from JSON to JavaScript objects

        const parsedCartItems = JSON.parse(cartItems);

        // Get the section element by ID

        const section = document.getElementById('cartSection');
        section.innerHTML = ''
        const total = document.getElementById('cartTotal');
        // Iterate over the cart items and create HTML elements

        parsedCartItems.forEach((item) => {
            // Create the main div element

            const div = document.createElement('div');

            div.style.display = 'flex';

            div.style.alignItems = 'center';

            div.style.width = '100%';

            div.style.justifyContent = 'space-between';

            // Create the inner section element

            const innerSection = document.createElement('section');

            innerSection.style.display = 'flex';

            innerSection.style.alignItems = 'center';

            innerSection.style.gap = '8px';

            // Create the image element

            const img = document.createElement('img');

            img.src = './assets/imgs/background.jpg';

            img.alt = '';

            img.style.width = '30%';

            img.style.height = '50%';
            img.style.borderRadius = '8px'

            // Create the details section element

            const detailsSection = document.createElement('section');

            detailsSection.style.display = 'flex';

            detailsSection.style.flexDirection = 'column';

            detailsSection.style.alignItems = 'self-start';

            detailsSection.style.gap = '16px';

            // Create the name span element

            const nameSpan = document.createElement('span');

            nameSpan.textContent = "Name : " + item.name;

            // Create the quantity span element

            const quantitySpan = document.createElement('span');

            quantitySpan.textContent = item.quantity + " x " + item.price;

            // Append the name and quantity elements to the details section

            detailsSection.appendChild(nameSpan);

            detailsSection.appendChild(quantitySpan);

            // Append the image element and details section to the inner section

            innerSection.appendChild(img);

            innerSection.appendChild(detailsSection);
            const iconBox = document.createElement('button')
            iconBox.style.padding = '16px';
            iconBox.style.display = 'grid';
            iconBox.style.placeItems = 'center';
            iconBox.style.borderRadius = '8px';
            iconBox.style.border = '1px solid gray'
            iconBox.style.cursor = "pointer";
            iconBox.style.backgroundColor = 'white'
            iconBox.classList.add("cart-delete-btn")
            const icon = document.createElement('i')
            icon.classList.add("fa-regular");
            icon.classList.add("fa-trash-can");
            icon.classList.add(item.ProductId);
            icon.style.cursor = "pointer";
            iconBox.addEventListener("click", () => {
                const newCart = parsedCartItems.filter(obj => obj.ProductId !== item.ProductId)
                console.log(newCart)
                localStorage.setItem('cart', JSON.stringify(newCart));
                renderProduct(data)
            })

            // Append the inner section to the main div
            iconBox.appendChild(icon)
            div.appendChild(innerSection);
            div.appendChild(iconBox);

            // Append the main div to the section

            section.appendChild(div);
        });
    }

    var itemCards = document.getElementsByClassName("item-card");

    Array.from(itemCards).forEach(function (card) {
        var valueElement = card.querySelector(".value");
        var incrementButton = card.querySelector(".increment");
        var decrementButton = card.querySelector(".decrement");

        var value = 1;

        incrementButton.addEventListener("click", function () {
            value++;
            valueElement.textContent = value;
        });

        decrementButton.addEventListener("click", function () {
            if (value > 1) {
                value--;
                valueElement.textContent = value;
            }
        });
    });

    var addToCartButtons = document.querySelectorAll('.btn');

    addToCartButtons.forEach(function (button) {
        button.addEventListener('click', function () {
            var itemCard = button.closest('.grid-item');
            var itemPrice = itemCard.querySelector('span').textContent;
            var itemName = itemCard.querySelector('span:first-child').textContent;
            var itemId = itemCard.querySelector('#item-id').textContent;
            var itemQuantity = parseInt(itemCard.querySelector('.value').textContent);
            var itemImage = itemCard.querySelector('img').src;

            var itemData = {
                id: itemId,
                name: itemName,
                price: itemPrice,
                quantity: itemQuantity,
                image:itemImage
            };

            var existingCartData = JSON.parse(localStorage.getItem('cart')) || [];


            var existingItemIndex = existingCartData.findIndex(function (item) {
                return item.name === itemName;
            });

            if (existingItemIndex !== -1) {

                existingCartData[existingItemIndex].quantity += itemQuantity;
            } else {

                existingCartData.push(itemData);
            }
            localStorage.setItem('cart', JSON.stringify(existingCartData));
            if (JSON.parse(localStorage.getItem('cart')).length > 0) {
                document.querySelector("#cartCount").style.display = "grid"
            }
            else {
                document.querySelector("#cartCount").style.display = "none"
            }
            document.querySelector("#cartCount").textContent = JSON.parse(localStorage.getItem('cart')).length;
            renderProduct(data);
        });
    });
}