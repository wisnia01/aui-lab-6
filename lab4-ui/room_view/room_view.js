import {
    getParameterByName,
    setLinkNode,
    setTextNode
} from '../js/utils.js';
import {getBackendUrl} from '../js/config.js';

window.addEventListener('load', () => {
    fetchAndDisplayRoom();
});

/**
 * Fetches single hotel and modifies the DOM tree in order to display it.
 */
function fetchAndDisplayRoom() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            displayRoom(JSON.parse(this.responseText))
        }
    };
    if(getParameterByName('hotel')) {
        xhttp.open("GET", getBackendUrl() + '/api/hotels/' + getParameterByName('hotel') + '/rooms/' + getParameterByName('room'), true);
    } else {
        xhttp.open("GET", getBackendUrl() + '/api/rooms/' + getParameterByName('room'))
    }
    xhttp.send();
}

function displayRoom(room) {
    setTextNode('name', room.name);
    setTextNode('number', room.number);
    setTextNode('area', room.area);
    setTextNode('owner', room.owner)
    setLinkNode('edit', 'edit', '../room_edit/room_edit.html?hotel='
    + getParameterByName('hotel') + '&room=' + room.number)
}
