$(document).ready(function () {

    // Charger dynamiquement les menus via l'API
    getMenuList(renderMenus, showMenuError);

    function renderMenus(menus) {
        const $menuGrid = $("#menuGrid");
        $menuGrid.empty(); // Nettoyage avant injection

        if (!menus || menus.length === 0) {
            $menuGrid.append("<p>Aucun menu disponible pour le moment.</p>");
            return;
        }

        menus.forEach(menu => {
            const cardId = `menu-${menu.pk_menu}`;
            const $card = $(`
                <div class="menu-card" id="${cardId}" data-id="${menu.pk_menu}">
                    <div class="menu-image"></div>
                    <h4 class="menu-nom">${menu.nom}</h4>
                    <p class="price">${menu.prix_unitaire} CHF</p>
                    <div class="qty-controls">
                        <button class="btn-minus" id="minus-${menu.pk_menu}">-</button>
                        <span id="qty-${menu.pk_menu}" class="qty">0</span>
                        <button class="btn-plus" id="plus-${menu.pk_menu}">+</button>
                    </div>
                    <button class="commander-btn" id="order-${menu.pk_menu}">Commander</button>
                </div>
            `);

            $menuGrid.append($card);
        });
    }

    function showMenuError(xhr, status, error) {
        console.error("Erreur lors du chargement des menus :", error);
        $("#menuGrid").html("<p>Erreur lors du chargement des menus.</p>");
    }

    // Incrémenter la quantité
    $(document).on("click", ".btn-plus", function () {
        const menuId = $(this).attr("id").split("-")[1];
        const $qty = $(`#qty-${menuId}`);
        let count = parseInt($qty.text());
        $qty.text(count + 1);
    });

    // Décrémenter la quantité
    $(document).on("click", ".btn-minus", function () {
        const menuId = $(this).attr("id").split("-")[1];
        const $qty = $(`#qty-${menuId}`);
        let count = parseInt($qty.text());
        if (count > 0) {
            $qty.text(count - 1);
        }
    });

    // Commander
    $(document).on("click", ".commander-btn", function () {
        const menuId = $(this).attr("id").split("-")[1];
        const $card = $(`#menu-${menuId}`);
        const nom = $card.find(".menu-nom").text();
        const prix = parseFloat($card.find(".price").text());
        const quantite = parseInt($(`#qty-${menuId}`).text());

        if (quantite <= 0) {
            alert("Veuillez choisir une quantité supérieure à 0.");
            return;
        }

        const commande = {
            menuId: menuId,
            nom: nom,
            prix: prix,
            quantite: quantite
        };

        console.log("Commande : ", commande);
        alert(`Commande ajoutée : ${commande.quantite} x ${commande.nom}`);
        // Tu peux maintenant appeler la fonction `commander(commandeDTO, success, error)` si tu veux l’envoyer au backend
    });

});
