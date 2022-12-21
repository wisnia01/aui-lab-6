//FIXXXXXXXXX

import {getParameterByName, setTextNode} from '../js/utils.js';
import {getBackendUrl} from '../js/config.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => updateInfoAction(event));

    fetchAndDisplayHotel();
});

/**
 * Fetches currently logged person's persons and updates edit form.
 */
function fetchAndDisplayHotel() {
    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            let response = JSON.parse(this.responseText);
            for (const [key, value] of Object.entries(response)) {
                let input = document.getElementById(key);
                if (input) {
                    input.value = value;
                    setTextNode(key, value);
                }
            }
        }
    };
    xhttp.open("GET", getBackendUrl() + '/api/hotels/' + getParameterByName('hotel'), true);
    xhttp.send();
}

/**
 * Action event handled for updating person info.
 * @param {Event} event dom event
 */
function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            fetchAndDisplayHotel();
        }
    };
    xhttp.open("PUT", getBackendUrl() + '/api/hotels/' + getParameterByName('hotel'), true);

    const request = {
        'age': parseInt(document.getElementById('age').value),
        'phoneNumber': document.getElementById('phoneNumber').value
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
}