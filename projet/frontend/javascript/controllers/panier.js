$(document).ready(function () {
    const $panierContent = $("#panierContent");
    const $panierVide = $("#panierVide");
    const $panierTotal = $("#panierTotal");
    const $btnValiderPanier = $("#btnValiderPanier");

    let panier = JSON.parse(localStorage.getItem("panier")) || [];

    function afficherPanier() {
        $panierContent.empty();
        let total = 0;

        if (panier.length === 0) {
            $panierVide.show();
            $panierTotal.text("0.00 CHF");
            return;
        }

        $panierVide.hide();

        panier.forEach((item, index) => {
            const $item = $(`
                <div class="panier-item" id="item-${index}">
                    <p>${item.nom} x ${item.quantite}</p>
                    <p>${(item.prix * item.quantite).toFixed(2)} CHF</p>
                    <button class="supprimer-btn" data-index="${index}">Supprimer</button>
                </div>
            `);
            $panierContent.append($item);
            total += item.prix * item.quantite;
        });

        $panierTotal.text(total.toFixed(2) + " CHF");
    }

    $(document).on("click", ".supprimer-btn", function () {
        const index = $(this).data("index");
        panier.splice(index, 1);
        localStorage.setItem("panier", JSON.stringify(panier));
        afficherPanier();
    });

    $btnValiderPanier.on("click", function () {
        if (panier.length === 0) {
            alert("Votre panier est vide !");
            return;
        }

        panier.forEach(item => {
            const dto = {
                menuId: item.menuId,
                nom: item.nom,
                prix: item.prix,
                quantite: item.quantite
            };

            commander(dto, function () {
                console.log(`Commande envoyée pour ${item.nom}`);
            }, function (xhr, status, error) {
                console.error(`Erreur lors de la commande pour ${item.nom} :`, error);
            });
        });

        alert("Votre commande a bien été envoyée !");
        localStorage.removeItem("panier");
        panier = [];
        afficherPanier();
    });

    afficherPanier();
});
