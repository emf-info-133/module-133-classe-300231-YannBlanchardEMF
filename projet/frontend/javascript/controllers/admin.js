$(document).ready(function () {

    // ========================== ENTREPRISES ==========================
    function chargerEntreprises() {
      getAllEntreprises(function (entreprises) {
        const $table = $("#entrepriseTable");
        const $select = $("#entrepriseSelect");
        $table.empty();
        $select.empty();
        entreprises.forEach(ent => {
          $table.append(`
            <tr data-id="${ent.id}" data-nom="${ent.nom}" data-adresse="${ent.adresse}">
              <td>${ent.nom}</td>
              <td>${ent.adresse}</td>
            </tr>
          `);
          $select.append(`<option value="${ent.id}">${ent.nom}</option>`);
        });
      }, function () {
        alert("Erreur lors du chargement des entreprises.");
      });
    }
  
    $(document).on("click", "#entrepriseTable tr", function () {
      $("#entrepriseNom").val($(this).data("nom"));
      $("#entrepriseAdresse").val($(this).data("adresse"));
      $("#entrepriseId").val($(this).data("id"));
    });
  
    $("#btnAddEntreprise").on("click", function () {
      const nom = $("#entrepriseNom").val();
      const adresse = $("#entrepriseAdresse").val();
      if (!nom || !adresse) return alert("Champs vides !");
      const dto = { nom, adresse };
      addEntreprise(dto, function () {
        alert("Entreprise ajoutée !");
        chargerEntreprises();
      }, () => alert("Erreur lors de l'ajout."));
    });
  
    $("#btnModifyEntreprise").on("click", function () {
      const id = $("#entrepriseId").val();
      const nom = $("#entrepriseNom").val();
      const adresse = $("#entrepriseAdresse").val();
      if (!id || !nom || !adresse) return alert("Champs manquants !");
      const dto = { nom, adresse };
      modifyEntreprise(id, dto, function () {
        alert("Entreprise modifiée !");
        chargerEntreprises();
      }, () => alert("Erreur lors de la modification."));
    });
  
    $("#btnDeleteEntreprise").on("click", function () {
      const id = $("#entrepriseId").val();
      if (!id) return alert("Sélectionnez une entreprise !");
      if (!confirm("Confirmer la suppression ?")) return;
      deleteEntreprise(id, function () {
        alert("Entreprise supprimée !");
        chargerEntreprises();
      }, () => alert("Erreur lors de la suppression."));
    });
  
  
    // ========================== UTILISATEURS ==========================
    function chargerUtilisateurs() {
      getAdminUsers(function (users) {
        const $table = $("#userTable");
        $table.empty();
        users.forEach(user => {
          const entreprise = user.entreprise ? user.entreprise.nom : "PAS D’ENTREPRISE";
          const adminText = user.admin ? "ADMIN" : "PAS ADMIN";
          $table.append(`
            <tr data-id="${user.id}" data-nom="${user.nom}" data-admin="${user.admin}" data-entreprise="${user.entreprise ? user.entreprise.id : ''}">
              <td>${user.nom}</td>
              <td>${entreprise}</td>
              <td>${adminText}</td>
            </tr>
          `);
        });
      }, function () {
        alert("Erreur chargement utilisateurs.");
      });
    }
  
    $(document).on("click", "#userTable tr", function () {
      $("#userNom").val($(this).data("nom"));
      $("#userId").val($(this).data("id"));
      $("#isAdmin").prop("checked", $(this).data("admin"));
      const entrepriseId = $(this).data("entreprise");
      $("#entrepriseSelect").val(entrepriseId);
    });
  
    $("#btnAddUser").on("click", function () {
      const nom = $("#userNom").val();
      const entrepriseId = $("#entrepriseSelect").val();
      const admin = $("#isAdmin").is(":checked");
      if (!nom) return alert("Nom manquant !");
      const dto = { nom, fk_entreprise: entrepriseId || null, admin };
      addUser(dto, function () {
        alert("Utilisateur ajouté !");
        chargerUtilisateurs();
      }, () => alert("Erreur ajout utilisateur."));
    });
  
    $("#btnModifyUser").on("click", function () {
      const id = $("#userId").val();
      const nom = $("#userNom").val();
      const entrepriseId = $("#entrepriseSelect").val();
      const admin = $("#isAdmin").is(":checked");
      if (!id || !nom) return alert("Champs manquants !");
      const dto = { nom, fk_entreprise: entrepriseId || null, admin };
      modifyUser(id, dto, function () {
        alert("Utilisateur modifié !");
        chargerUtilisateurs();
      }, () => alert("Erreur modification utilisateur."));
    });
  
    $("#btnDeleteUser").on("click", function () {
      const id = $("#userId").val();
      if (!id) return alert("Sélectionnez un utilisateur !");
      if (!confirm("Supprimer cet utilisateur ?")) return;
      deleteUser(id, function () {
        alert("Utilisateur supprimé !");
        chargerUtilisateurs();
      }, () => alert("Erreur suppression utilisateur."));
    });
  
    // Init
    chargerEntreprises();
    chargerUtilisateurs();
  });
  