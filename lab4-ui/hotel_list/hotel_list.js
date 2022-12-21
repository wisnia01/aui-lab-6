import {clearElementChildren, createLinkCell, createButtonCell, createTextCell} from '../js/utils.js';
import {getBackendUrl} from '../js/config.js';

window.addEventListener('load', () => {
    fetchAndDisplayHotels();
});

/**
 * Fetches all hotels and modifies the DOM tree in order to display them.
 */
function fetchAndDisplayHotels() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayHotels(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/hotels', true);
    console.log(getBackendUrl());
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display hotels.
 *
 * @param {{hotels: string[]}} hotels
 */
function displayHotels(hotels) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    hotels.hotels.forEach(hotel => {
        tableBody.appendChild(createTableRow(hotel));
    })
}

/**
 * Creates single table row for entity.
 *
 * @param {string} hotel
 * @returns {HTMLTableRowElement}
 */
function createTableRow(hotel) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(hotel["name"]));
    tr.appendChild(createLinkCell('view', '../hotel_view/hotel_view.html?hotel=' + hotel["name"]));
    tr.appendChild(createLinkCell('edit', '../hotel_edit/hotel_edit.html?hotel=' + hotel["name"]));
    tr.appendChild(createButtonCell('delete', () => deleteHotel(hotel)));
    return tr;
}

/**
 * Deletes entity from backend and reloads table.
 *
 * @param {string } hotel to be deleted
 */
function deleteHotel(hotel) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayHotels();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/hotels/' + hotel["name"], true);
    xhttp.send();
}
