import {getParameterByName} from '../js/utils.js';
import {getBackendUrl} from '../js/config.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => updateInfoAction(event));

    fetchAndDisplayRoom();
});

/**
 * Fetches currently logged hotel's rooms and updates edit form.
 */
function fetchAndDisplayRoom() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            for (const [key, value] of Object.entries(response)) {
                let input = document.getElementById(key);
                if (input) {
                    input.value = value;
                }
            }
        }
    };
    if(getParameterByName('hotel')) {
        xhttp.open("GET", getBackendUrl() + '/api/hotels/' + getParameterByName('hotel') + '/rooms/' + getParameterByName('room'), true);
    } else {
        xhttp.open("GET", getBackendUrl() + '/api/rooms/' + getParameterByName('room'))
    }
    xhttp.send();
}

/**
 * Action event handled for updating room info.
 * @param {Event} event dom event
 */
function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplayRoom();
        }
    };
    xhttp.open("PUT", getBackendUrl() + '/api/hotels/' + getParameterByName('hotel') + '/rooms/'
        + getParameterByName('room'), true);

    const request = {
        'name': document.getElementById('name').value,
        'area': parseInt(document.getElementById('area').value)
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
}