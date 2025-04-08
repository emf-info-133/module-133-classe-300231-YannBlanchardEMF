$(document).ready(function () {
    const userId = 1; // 🔁 À adapter si nécessaire

    function chargerMenus() {
        getMenuList(function (menus) {
            const $table = $("#tableBodyMenus");
            $table.empty();

            menus.forEach(menu => {
                const $row = $(`
                    <tr data-id="${menu.pk_menu}" data-nom="${menu.nom}" data-prix="${menu.prix_unitaire}">
                        <td>${menu.nom}</td>
                        <td>${menu.prix_unitaire} CHF</td>
                    </tr>
                `);
                $table.append($row);
            });
        }, function () {
            alert("Erreur lors du chargement des menus.");
        });
    }

    // Sélection d'une ligne pour remplir le formulaire
    $(document).on("click", "#tableBodyMenus tr", function () {
        const nom = $(this).data("nom");
        const prix = $(this).data("prix");
        const id = $(this).data("id");

        $("#inputNomMenu").val(nom);
        $("#inputPrixUnitaire").val(prix);
        $("#inputPkMenu").val(id);
    });

    // Ajouter un menu
    $("#btnAdd").on("click", function () {
        const nom = $("#inputNomMenu").val().trim();
        const prix = parseInt($("#inputPrixUnitaire").val());

        if (!nom || isNaN(prix)) {
            alert("Veuillez remplir correctement les champs.");
            return;
        }

        addMenu(nom, prix, userId, function () {
            alert("Menu ajouté !");
            chargerMenus();
        }, function () {
            alert("Erreur lors de l'ajout du menu.");
        });
    });

    // Modifier un menu
    $("#btnModify").on("click", function () {
        const nom = $("#inputNomMenu").val().trim();
        const prix = parseInt($("#inputPrixUnitaire").val());
        const pk_menu = parseInt($("#inputPkMenu").val());

        if (!pk_menu || !nom || isNaN(prix)) {
            alert("Veuillez sélectionner un menu à modifier.");
            return;
        }

        modifyMenu(pk_menu, nom, prix, userId, function () {
            alert("Menu modifié !");
            chargerMenus();
        }, function () {
            alert("Erreur lors de la modification.");
        });
    });

    // Supprimer un menu
    $("#btnDelete").on("click", function () {
        const pk_menu = parseInt($("#inputPkMenu").val());

        if (!pk_menu) {
            alert("Veuillez sélectionner un menu à supprimer.");
            return;
        }

        if (!confirm("Voulez-vous vraiment supprimer ce menu ?")) return;

        deleteMenu(pk_menu, userId, function () {
            alert("Menu supprimé !");
            chargerMenus();
        }, function () {
            alert("Erreur lors de la suppression.");
        });
    });

    // Initialisation
    chargerMenus();
});
