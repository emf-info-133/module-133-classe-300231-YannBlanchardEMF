// servicesHttp.js

/**
 * Connexion d’un client
 */
function loginClient(loginInfo, successCallback, errorCallback) {
    $.ajax({
        url: '/gateway/login',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(loginInfo),
        success: successCallback,
        error: errorCallback
    });
}

/**
 * Inscription d’un client
 */
function registerClient(clientInfo, successCallback, errorCallback) {
    $.ajax({
        url: '/gateway/register',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(clientInfo),
        success: successCallback,
        error: errorCallback
    });
}

/**
 * Passer une commande (client)
 */
function commander(dto, successCallback, errorCallback) {
    $.ajax({
        url: '/gateway/commande',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(dto),
        success: successCallback,
        error: errorCallback
    });
}

/**
 * Récupérer un utilisateur (client)
 */
function getUserById(dto, successCallback, errorCallback) {
    $.ajax({
        url: '/gateway/user',
        type: 'GET',
        contentType: 'application/json',
        data: JSON.stringify(dto),
        success: successCallback,
        error: errorCallback
    });
}

/**
 * Récupérer tous les utilisateurs (clients)
 */
function getAllUsers(successCallback, errorCallback) {
    $.ajax({
        url: '/gateway/users',
        type: 'GET',
        success: successCallback,
        error: errorCallback
    });
}

/**
 * Ajouter un menu (entreprise)
 */
function addMenu(nom, prix_unitaire, userId, successCallback, errorCallback) {
    $.ajax({
        url: `/gateway/addMenu?nom=${encodeURIComponent(nom)}&prix_unitaire=${prix_unitaire}&userId=${userId}`,
        type: 'POST',
        success: successCallback,
        error: errorCallback
    });
}

/**
 * Modifier un menu (entreprise)
 */
function modifyMenu(pk_menu, nom, prix_unitaire, userId, successCallback, errorCallback) {
    $.ajax({
        url: `/gateway/modifyMenu?pk_menu=${pk_menu}&nom=${encodeURIComponent(nom)}&prix_unitaire=${prix_unitaire}&userId=${userId}`,
        type: 'POST',
        success: successCallback,
        error: errorCallback
    });
}

/**
 * Supprimer un menu (entreprise)
 */
function deleteMenu(pk_menu, userId, successCallback, errorCallback) {
    $.ajax({
        url: `/gateway/deleteMenu?pk_menu=${pk_menu}&userId=${userId}`,
        type: 'POST',
        success: successCallback,
        error: errorCallback
    });
}

/**
 * Récupérer la liste des menus (entreprise)
 */
function getMenuList(successCallback, errorCallback) {
    $.ajax({
        url: '/gateway/getMenuList',
        type: 'GET',
        success: successCallback,
        error: errorCallback
    });
}

/**
 * Ajouter une entreprise (admin)
 */
function addEntreprise(dto, successCallback, errorCallback) {
    $.ajax({
        url: '/gateway/addEntreprise',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(dto),
        success: successCallback,
        error: errorCallback
    });
}

/**
 * Récupérer toutes les entreprises (admin)
 */
function getAllEntreprises(successCallback, errorCallback) {
    $.ajax({
        url: '/gateway/getEntreprises',
        type: 'GET',
        success: successCallback,
        error: errorCallback
    });
}

/**
 * Récupérer une entreprise par ID (admin)
 */
function getEntrepriseById(id, successCallback, errorCallback) {
    $.ajax({
        url: `/gateway/getEntreprise/${id}`,
        type: 'GET',
        success: successCallback,
        error: errorCallback
    });
}

/**
 * Modifier une entreprise (admin)
 */
function modifyEntreprise(id, dto, successCallback, errorCallback) {
    $.ajax({
        url: `/gateway/modifyEntreprise/${id}`,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(dto),
        success: successCallback,
        error: errorCallback
    });
}

/**
 * Supprimer une entreprise (admin)
 */
function deleteEntreprise(id, successCallback, errorCallback) {
    $.ajax({
        url: `/gateway/deleteEntreprise/${id}`,
        type: 'DELETE',
        success: successCallback,
        error: errorCallback
    });
}

/**
 * Ajouter un utilisateur (admin)
 */
function addUser(dto, successCallback, errorCallback) {
    $.ajax({
        url: '/gateway/addUser',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(dto),
        success: successCallback,
        error: errorCallback
    });
}

/**
 * Modifier un utilisateur (admin)
 */
function modifyUser(id, dto, successCallback, errorCallback) {
    $.ajax({
        url: `/gateway/modifyUser/${id}`,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(dto),
        success: successCallback,
        error: errorCallback
    });
}

/**
 * Supprimer un utilisateur (admin)
 */
function deleteUser(id, successCallback, errorCallback) {
    $.ajax({
        url: `/gateway/deleteUser/${id}`,
        type: 'DELETE',
        success: successCallback,
        error: errorCallback
    });
}

/**
 * Récupérer tous les utilisateurs (admin)
 */
function getAdminUsers(successCallback, errorCallback) {
    $.ajax({
        url: '/gateway/getUsers',
        type: 'GET',
        success: successCallback,
        error: errorCallback
    });
}

/**
 * Connexion d’un utilisateur (admin)
 */
function loginAdminUser(dto, successCallback, errorCallback) {
    $.ajax({
        url: '/gateway/loginUser',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(dto),
        success: successCallback,
        error: errorCallback
    });
}
