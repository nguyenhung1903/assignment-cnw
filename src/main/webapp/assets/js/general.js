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