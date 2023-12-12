var isShowConfirmPopup = false;
const handleShowConfirm = () => {
    if (!isShowConfirmPopup) {
        if (document.querySelector("#confirm-modal").classList.contains("close")) {
            document.querySelector("#confirm-modal").classList.remove("close");
        } else {
            document.querySelector("#confirm-modal").classList.add("open");
        }
        isShowConfirmPopup = true;
        loadOrderConfirm();
    }
};
const loadOrderConfirm = () => {
    $("#confirm-modal").load("./Order/create");
}