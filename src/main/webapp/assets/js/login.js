const passwordRegex = /^(?=.*[a-zA-Z])(.{3,})$/;
const usernameRegex = /^[^0-9][a-zA-Z0-9]{2,}$/;

const validateLogin = () => {
  const password = document.querySelector('input[name="passwordS"]').value;
  const username = document.querySelector('input[name="usernameS"]').value;
  if (password.length >= 3) {
    if (
      document.querySelector(".pass-length").classList.contains("test-failed")
    ) {
      document.querySelector(".pass-length").classList.remove("test-failed");
    }
    document.querySelector(".pass-length").classList.add("test-ok");
  } else {
    if (password.length != 0) {
      if (
        document.querySelector(".pass-length").classList.contains("test-ok")
      ) {
        document.querySelector(".pass-length").classList.remove("test-ok");
      }
      document.querySelector(".pass-length").classList.add("test-failed");
    }
  }

  if (username.length >= 3) {
    if (
      document.querySelector(".user-length").classList.contains("test-failed")
    ) {
      document.querySelector(".user-length").classList.remove("test-failed");
    }
    document.querySelector(".user-length").classList.add("test-ok");
  } else {
    if (username.length != 0) {
      if (
        document.querySelector(".user-length").classList.contains("test-ok")
      ) {
        document.querySelector(".user-length").classList.remove("test-ok");
      }
      document.querySelector(".user-length").classList.add("test-failed");
    }
  }

  if (passwordRegex.test(password)) {
    if (
      document.querySelector(".pass-letter").classList.contains("test-failed")
    ) {
      document.querySelector(".pass-letter").classList.remove("test-failed");
    }
    document.querySelector(".pass-letter").classList.add("test-ok");
  } else {
    if (password.length != 0) {
      if (
        document.querySelector(".pass-letter").classList.contains("test-ok")
      ) {
        document.querySelector(".pass-letter").classList.remove("test-ok");
      }
      document.querySelector(".pass-letter").classList.add("test-failed");
    }
  }

  if (usernameRegex.test(username)) {
    if (
      document.querySelector(".user-letter").classList.contains("test-failed")
    ) {
      document.querySelector(".user-letter").classList.remove("test-failed");
    }
    document.querySelector(".user-letter").classList.add("test-ok");
  } else {
    if (username.length != 0) {
      if (
        document.querySelector(".user-letter").classList.contains("test-ok")
      ) {
        document.querySelector(".user-letter").classList.remove("test-ok");
      }
      document.querySelector(".user-letter").classList.add("test-failed");
    }
  }
};

var isShowLoginPopup = false;
const handlePopupLogin = () => {
  if (!isShowSignUpPopup) {
    if (document.querySelector("#login-modal").classList.contains("close")) {
      document.querySelector("#login-modal").classList.remove("close");
    } else {
      document.querySelector("#login-modal").classList.add("open");
    }
    isShowLoginPopup = true;
  }
};
var isShowSignUpPopup = false;
const handlePopupSignUp = () => {
  if (!isShowLoginPopup) {
    if (document.querySelector("#signup-modal").classList.contains("close")) {
      document.querySelector("#signup-modal").classList.remove("close");
    } else {
      document.querySelector("#signup-modal").classList.add("open");
    }
    isShowSignUpPopup = true;
  }
};

const handleClosePopup = (type) => {
  if (type === "login") {
    if (isShowLoginPopup) {
      if (document.querySelector(".login-popup").classList.contains("open")) {
        document.querySelector(".login-popup").classList.remove("open");
      } else {
        document.querySelector(".login-popup").classList.add("close");
      }
    }
    isShowLoginPopup = false;
  } else if (type === "cart") {
    if (isShowConfirmPopup) {
      if (document.querySelector(".confirm-popup").classList.contains("open")) {
        document.querySelector(".confirm-popup").classList.remove("open");
      } else {
        document.querySelector(".confirm-popup").classList.add("close");
      }
    }
    isShowConfirmPopup = false;
  } else {
    if (isShowSignUpPopup) {
      if (document.querySelector(".signup-popup").classList.contains("open")) {
        document.querySelector(".signup-popup").classList.remove("open");
      } else {
        document.querySelector(".signup-popup").classList.add("close");
      }
    }
    isShowSignUpPopup = false;
  }
};
