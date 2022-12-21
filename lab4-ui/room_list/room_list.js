import {clearElementChildren, createLinkCell, createButtonCell, createTextCell} from '../js/utils.js';
import {getBackendUrl} from '../js/config.js';

window.addEventListener('load', () => {
    fetchAndDisplayRooms();
});

/**
 * Fetches all rooms and modifies the DOM tree in order to display them.
 */
function fetchAndDisplayRooms() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayRooms(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/rooms', true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display rooms.
 *
 * @param {{rooms: string[]}} rooms
 */
function displayRooms(rooms) {
    let tableBody = document.getElementById('tableBody');
    clearElementChildren(tableBody);
    rooms.rooms.forEach(room => {
        tableBody.appendChild(createTableRow(room));
    })
}

/**
 * Creates single table row for entity.
 *
 * @param {string} room
 * @returns {HTMLTableRowElement}
 */
function createTableRow(room) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(room["name"]));
    tr.appendChild(createLinkCell('view', '../room_view/room_view.html?room=' + room["number"]));
    tr.appendChild(createLinkCell('edit', '../room_edit/room_edit.html?room=' + room["number"]));
    tr.appendChild(createButtonCell('delete', () => deleteRoom(room)));
    return tr;
}

/**
 * Deletes entity from backend and reloads table.
 *
 * @param {string } room to be deleted
 */
function deleteRoom(room) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayRooms();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/rooms/' + room["number"], true);
    xhttp.send();
}
