/**
 * Méthode "start" appelée après le chargement complet de la page
 */
$(document).ready(function () {
    console.log("Chargement des scripts...");
  
    $.getScript("javascripts/beans/menu.js", function () {
      console.log("menu.js chargé !");
    });
  
    $.getScript("javascripts/beans/panier.js", function () {
      console.log("panier.js chargé !");
    });
  
    $.getScript("javascripts/services/servicesHttp.js", function () {
      console.log("servicesHttp.js chargé !");
  
      console.log("Appel AJAX pour charger les menus...");
      chargerMenus(chargerMenusSuccess, chargerMenusError);
      updateNavBar();
    });
    
  });
  $(document).on("click", ".btn-increase", function () {
    let inputField = $(this).siblings(".menu-quantity-input");
    let currentValue = parseInt(inputField.val());
    inputField.val(currentValue + 1);
  
    let pkMenu = parseInt($(this).data("id"));
  
    menusList.forEach((m) => {
      console.log("menu du foreach :", m);
      console.log(
        `Comparaison : m.getPkMenu() = ${m.getPkMenu()} (${typeof m.getPkMenu()}) vs pkMenu = ${pkMenu} (${typeof pkMenu})`
      );
      if (Number(m.getPkMenu()) === pkMenu) {
        m.setQuantite(parseInt(inputField.val(), 10));
      }
    });
  });
  
  $(document).on("click", ".btn-decrease", function () {
    let inputField = $(this).siblings(".menu-quantity-input");
    let currentValue = parseInt(inputField.val());
    if (currentValue > 1) {
      inputField.val(currentValue - 1);
    }
  
    let pkMenu = parseInt($(this).data("id"));
  
    menusList.forEach((m) => {
      console.log("menu du foreach :", m);
      console.log(
        `Comparaison : m.getPkMenu() = ${m.getPkMenu()} (${typeof m.getPkMenu()}) vs pkMenu = ${pkMenu} (${typeof pkMenu})`
      );
      if (Number(m.getPkMenu()) === pkMenu) {
        m.setQuantite(parseInt(inputField.val(), 10));
      }
    });
  });
  
  $(document).on("click", ".btn-ajout-panier", function () {
    let pkMenu = parseInt($(this).data("id"));
    let quantite = parseInt(
      $(this).closest(".menu-content").find(".menu-quantity-input").val()
    );
  
    let menu = menusList.find((m) => Number(m.getPkMenu()) === Number(pkMenu));
    if (!menu) {
      console.error("Erreur : Menu introuvable dans menusList.");
      return;
    }
  
    panier.ajouterItem(menu);
  
    alert(`Menu ajouté au panier : ${quantite}x ${menu.getNom()}`);
  
    // Récupérer le panier
    let panierStocke = JSON.parse(sessionStorage.getItem("panier")) || [];
  
  
    let itemExistant = panierStocke.find((item) => item.pkMenu === pkMenu);
  
    if (itemExistant) {
      itemExistant.quantite += quantite;
    } else {
      panierStocke.push({
        pkMenu: pkMenu,
        nom: menu.getNom(),
        prix: menu.getPrix(),
        quantite: quantite,
      });
    }
  
    // Sauvegarder dans sessionStorage
    sessionStorage.setItem("panier", JSON.stringify(panierStocke));
  
    // Réinitialiser la quantité à 1
    menu.setQuantite(1);
    $(this).closest(".menu-content").find(".menu-quantity-input").val(1);
  });
  
  $(document).on("click", "#btn-login", function (event) {
    event.preventDefault();
  
    let username = $("#login-username").val().trim();
    let password = $("#login-password").val().trim();
  
    console.log("Test btn page login");
    console.log("Nom d'utilisateur :", username);
    console.log("Mot de passe :", password);
  
    if (username === "" || password === "") {
      alert("Veuillez remplir tous les champs !");
    } else {
      console.log("appel ajax login");
  
      loginUser(username, password, loginUserSuccess, loginUserError);
    }
  });
  
  // Écouteur pour le bouton d'inscription
  $(document).on("click", "#btn-register", function (event) {
    event.preventDefault();
  
    let username = $("#register-username").val().trim();
    let password = $("#register-password").val().trim();
  
    console.log("test btn page resgister");
    console.log("Nom d'utilisateur :", username);
    console.log("Mot de passe :", password);
  
    if (username === "" || password === "") {
      alert("Veuillez remplir tous les champs !");
    } else {
      console.log("appel ajax login");
  
      registerUser(username, password, registerUserSuccess, registerUserError);
    }
  });
  
  // Écouteur pour le bouton d'inscription
  $(document).on("click", "#btn-logout", function (event) {
    disconnectUser(disconnectSuccess, disconnectError);
  
  });
  
  function updateNavBar() {
    let username = sessionStorage.getItem("username");
    console.log("Utilisateur récupéré du sessionStorage:", username);
  
    if (username) {
      $("#login-container").hide();
      $("#user-info").show();
  
      $("body").addClass("user-logged-in");
  
      console.log("Affichage connecté : boutons panier visibles");
    } else {
      $("#login-container").show();
      $("#user-info").hide();
  
      $("body").removeClass("user-logged-in");
  
      console.log("Affichage déconnecté : boutons panier cachés");
    }
  }
  