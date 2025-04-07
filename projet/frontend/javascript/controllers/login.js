$(document).ready(function () {

    $("#btnLogin").on("click", function (event) {
      event.preventDefault();
  
      let username = $("#login-username").val().trim();
      let password = $("#login-password").val().trim();
  
      if (username === "" || password === "") {
        alert("Veuillez remplir tous les champs !");
        return;
      }
  
      let loginInfo = {
        username: username,
        password: password
      };
  
      loginClient(loginInfo, loginSuccess, loginError);
    });
  
    function loginSuccess(response) {
      if (response && response.id) {
        alert("Connexion réussie !");
        sessionStorage.setItem("loggedUser", JSON.stringify(response));
        window.location.href = "home.html";
      } else {
        alert("Identifiants incorrects.");
      }
    }
  
    function loginError(xhr, status, error) {
      console.error("Erreur login :", error);
      alert("Erreur lors de la tentative de connexion.");
      sessionStorage.clear();
      window.location.href = "index.html";
    }
  
  });
  