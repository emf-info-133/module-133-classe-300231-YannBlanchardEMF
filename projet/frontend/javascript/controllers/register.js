$(document).ready(function () {
    $("#btnRegister").on("click", function (event) {
        event.preventDefault();

        let nom = $("#register-nom").val().trim();
        let prenom = $("#register-prenom").val().trim();
        let username = $("#register-username").val().trim();
        let password = $("#register-password").val().trim();
        let confirmPassword = $("#register-confirm-password").val().trim();

        console.log("Clic sur 'Register'");
        console.log("Nom :", nom);
        console.log("Prénom :", prenom);
        console.log("Nom d'utilisateur :", username);

        // Validation de base
        if (!nom || !prenom || !username || !password || !confirmPassword) {
            alert("Veuillez remplir tous les champs !");
            return;
        }

        if (password !== confirmPassword) {
            alert("Les mots de passe ne correspondent pas !");
            return;
        }

        let clientDTO = {
            nom: nom,
            prenom: prenom,
            username: username,
            password: password
        };

        registerClient(clientDTO, registerSuccess, registerError);
    });

    function registerSuccess(response) {
        alert("Inscription réussie !");
        window.location.href = "login.html";
    }

    function registerError(xhr, status, error) {
        console.error("Erreur AJAX:", xhr.responseText);
        alert("Erreur lors de l'inscription : " + xhr.responseText);
    }
});
