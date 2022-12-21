import {
    getParameterByName,
    clearElementChildren,
    createLinkCell,
    createButtonCell,
    createTextCell,
    setTextNode,
    setLinkNode
} from '../js/utils.js';
import {getBackendUrl} from '../js/config.js';

window.addEventListener('load', () => {
    fetchAndDisplayHotel();
    fetchAndDisplayRooms();
});

/**
 * Fetches all hotels and modifies the DOM tree in order to display them.
 */
function fetchAndDisplayRooms() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayRooms(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/hotels/' + getParameterByName('hotel') + '/rooms', true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display rooms.
 *
 * @param {{rooms: {id: number, name:string}[]}} rooms
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
 * @param {{id: number, name: string}} room
 * @returns {HTMLTableRowElement}
 */
function createTableRow(room) {
    let tr = document.createElement('tr');
    tr.appendChild(createTextCell(room.name));
    tr.appendChild(createLinkCell('view', '../room_view/room_view.html?hotel='
        + getParameterByName('hotel') + '&room=' + room.address));
    tr.appendChild(createLinkCell('edit', '../room_edit/room_edit.html?hotel='
        + getParameterByName('hotel') + '&room=' + room.address));
    tr.appendChild(createButtonCell('delete', () => deleteRoom(room)));
    return tr;
}

/**
 * Deletes entity from backend and reloads table.
 *
 * @param {number} room to be deleted
 */
function deleteRoom(room) {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 202) {
            fetchAndDisplayRooms();
        }
    };
    xhttp.open("DELETE", getBackendUrl() + '/api/hotels/' + getParameterByName('hotel')
        + '/rooms/' + room.address, true);
    xhttp.send();
}


/**
 * Fetches single hotel and modifies the DOM tree in order to display it.
 */
function fetchAndDisplayHotel() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayHotel(JSON.parse(this.responseText))
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/hotels/' + getParameterByName('hotel'), true);
    xhttp.send();
}

/**
 * Updates the DOM tree in order to display hotel.
 *
 * @param {{login: string, name: string, surname:string}} hotel
 */
function displayHotel(hotel) {
    setTextNode('name', hotel.name);
    setTextNode('age', hotel.age);
    setTextNode('phone', hotel.phoneNumber);

    setLinkNode('add', 'ADD ROOM', "../room_add/room_add.html?hotel=" + getParameterByName('hotel'));
}
