let dom = {

    allowMatchingPasswords: function () {
        let firstPassword = document.querySelector("#password");
        let secondPassword = document.querySelector("#password_confirmation");
        let passwordConfirmationLabel = document.querySelector("#password-confirmation-label");
        let button = document.querySelector("#submit");


        secondPassword.addEventListener("change", function (evt) {
            if (firstPassword.value === secondPassword.value) {
                button.disabled = false;
                passwordConfirmationLabel.innerHTML = "Passwords match.";
                passwordConfirmationLabel.classList.add("color-text-green");
            } else {
                passwordConfirmationLabel.classList.remove("color-text-green");
                passwordConfirmationLabel.innerHTML = "Passwords do not match.";
                passwordConfirmationLabel.classList.add("color-text-red");
                button.disabled = true;
            }
        })
    },

    checkIfEmailRegistered: function () {

        let emailAddressLabel = document.querySelector("#email-address-label");
        let inputFieldEmailAddress = document.querySelector("#email-address");
        let button = document.querySelector("#submit");


        inputFieldEmailAddress.addEventListener(
            "change", function (evt) {

                let changedInputFieldEmailAddress = document.querySelector("#email-address");

                let url = "http://localhost:8080/check-email";
                let xhr = new XMLHttpRequest();
                xhr.open("POST", url, true);
                xhr.send(changedInputFieldEmailAddress.value);

                xhr.onreadystatechange = function () {
                    if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200) {
                        if (xhr.responseText === changedInputFieldEmailAddress.value) {
                            emailAddressLabel.classList.remove("color-text-green");
                            emailAddressLabel.innerHTML = "This email address is already registered. Please choose another one:";
                            emailAddressLabel.classList.add("color-text-red");
                            button.disabled = true;
                        } else {
                            emailAddressLabel.innerHTML = "This email address is still available:";
                            emailAddressLabel.classList.remove("color-text-red");
                            emailAddressLabel.classList.add("color-text-green");
                            button.disabled = false;
                        }

                    }
                    else {
                        console.log('Request failed.  Returned status of ' + xhr.status);
                    }
                };



            }
        );






    },

    search: function () {
        search = document.querySelector("#search");

        search.addEventListener("keyup", function () {
            axios.get('/search', {
                params: {
                    keyWord: search.value
                }
            })
                .then(function (response) {
                    document.querySelector("#products").innerHTML = response.data;
                });
        });
    },

};