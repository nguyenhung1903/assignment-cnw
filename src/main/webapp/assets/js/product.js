getAllProducts = async () => {
  return $.ajax({
    url: "./api/Product/getAllProduct",
    type: "GET",
    success: function (data) {
      return data;
    },
    error: function (error) {
      console.log(error);
    },
  });
};

getProductById = async (id) => {
  return $.ajax({
    url: "./api/Product/getProductById?id=" + id,
    type: "GET",
    success: function (data) {
      return data;
    },
    error: function (error) {
      console.log(error);
    },
  });
};

getAllProducts().then((res) => {
  renderProduct(res);
});

const loadCartCount = ()=>{
  if (JSON.parse(localStorage.getItem("cart")).length > 0) {
    document.querySelector("#cartCount").style.display = "grid";
  } else {
    document.querySelector("#cartCount").style.display = "none";
  }
  document.querySelector("#cartCount").textContent = JSON.parse(
      localStorage.getItem("cart")
  ).length;
}

// ================================================
const renderProduct = (data) => {
  const productBox = document.querySelector("#products");
  productBox.innerHTML = "";
  data.forEach((item) => {
    const div = document.createElement("div");
    div.style.cssText =
      "display: flex; flex-direction: column; gap: 8px; color:white;";
    div.classList.add("grid-item");

    const img = document.createElement("img");
    img.style.cssText =
      "border-top-right-radius: 8px; border-top-left-radius: 8px; width: 328px; height: 218px";
    img.src = "./assets/imgs/background.jpg";
    if (item.Image.length > 10) {
      img.src = "data:image/jpeg;base64," + item.Image;
    }
    div.appendChild(img);

    const priceSpan = document.createElement("span");
    priceSpan.style.cssText = "padding: 8px 16px 0px 16px;";
    priceSpan.textContent = "$" + item.Price;
    div.appendChild(priceSpan);

    const section = document.createElement("section");
    section.style.cssText =
      "display: flex; align-items: center; justify-content: space-between; padding: 8px 16px;";
    div.appendChild(section);

    const nameSpan = document.createElement("span");
    nameSpan.textContent = item.ProductName;
    section.appendChild(nameSpan);

    const itemIdSpan = document.createElement("span");
    itemIdSpan.style.display = "none";
    itemIdSpan.id = "item-id";
    itemIdSpan.textContent = item.ProductId;
    section.appendChild(itemIdSpan);

    const itemCardSection = document.createElement("section");
    itemCardSection.classList.add("item-card");
    section.appendChild(itemCardSection);

    const incrementSpan = document.createElement("span");
    incrementSpan.classList.add("increment");
    incrementSpan.style.cssText =
      "padding: 4px 12px; border: 1px solid white; background: transparent; cursor: pointer;";
    incrementSpan.textContent = "+";
    itemCardSection.appendChild(incrementSpan);

    const valueSpan = document.createElement("span");
    valueSpan.classList.add("value");
    valueSpan.style.cssText =
      "padding: 4px 12px; border: 1px solid white; background: transparent;";
    valueSpan.textContent = "1";
    itemCardSection.appendChild(valueSpan);

    const decrementSpan = document.createElement("span");
    decrementSpan.classList.add("decrement");
    decrementSpan.style.cssText =
      "padding: 4px 12px; border: 1px solid white; background: transparent; cursor: pointer;";
    decrementSpan.textContent = "-";
    itemCardSection.appendChild(decrementSpan);

    const btnSection = document.createElement("section");
    btnSection.style.cssText = "padding: 16px;";
    div.appendChild(btnSection);

    const btnCart = document.createElement("button");
    btnCart.classList.add("btn", "btnCart");
    btnCart.style.cssText = "background: #f29a51; border: none; width: 100%;";
    btnSection.appendChild(btnCart);

    const cartIcon = document.createElement("i");
    cartIcon.classList.add("fa", "fa-cart-shopping");
    btnCart.appendChild(cartIcon);

    productBox.appendChild(div);
  });

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

  var addToCartButtons = document.querySelectorAll(".btn");

  addToCartButtons.forEach(function (button) {
    button.addEventListener("click", function () {
      var itemCard = button.closest(".grid-item");
      var itemId = itemCard.querySelector("#item-id").textContent;
      var itemQuantity = parseInt(itemCard.querySelector(".value").textContent);

      var itemData = {
        ProductId: itemId,
        Quantity: itemQuantity,
      };

      var existingCartData = JSON.parse(localStorage.getItem("cart")) || [];

      var existingItemIndex = existingCartData.findIndex(function (item) {
        return item.ProductId === itemId;
      });

      // ##########################################

      if (existingItemIndex !== -1) {
        existingCartData[existingItemIndex].Quantity += itemQuantity;
      } else {
        existingCartData.push(itemData);
      }
      localStorage.setItem("cart", JSON.stringify(existingCartData));
      loadCartCount();
      loadCart();
    });
  });
};
