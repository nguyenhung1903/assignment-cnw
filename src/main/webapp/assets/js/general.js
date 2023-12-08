var prevScrollPos = window.pageYOffset;
window.onscroll = function () {
    var currentScrollPos = window.pageYOffset;
    if (currentScrollPos === 0) {
        document.getElementById("navbar").style.background = "transparent";
    }
    else {
        document.getElementById("navbar").style.background = "#111111";

    }
    if (prevScrollPos > currentScrollPos) {
        // Scrolling up
        document.getElementById("navbar").classList.remove("hidden-navbar");
    } else {
        // Scrolling down
        document.getElementById("navbar").classList.add("hidden-navbar");
    }
    prevScrollPos = currentScrollPos;
};

$(document).ready(async function () {
    $("#categoryField").on("change", async function () {
        let categoryId = $("#categoryField").val();
        let data = await getAllProducts();
        let result = data.filter(function (item) {
            return (categoryId === "0" || item.CategoryId === categoryId);
        });

        $("#search-input").val("");

        renderProduct(result);
    });

    $("#search-input").on("input", async function () {
        let keyword = $("#search-input").val();
        let categoryId = $("#categoryField").val();

        let data = await getAllProducts();

        let result = data.filter(function (item) {
            return item.ProductName.toLowerCase().includes(keyword.toLowerCase()) && (categoryId === "0" || item.CategoryId === categoryId);
        });

        renderProduct(result);
    });
});