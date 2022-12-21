import {getParameterByName} from '../js/utils.js';
import {getBackendUrl} from '../js/config.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    infoForm.addEventListener('submit', event => updateInfoAction(event));
});

/**
 * Action event handled for updating farm info.
 * @param {Event} event dom event
 */
function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 201) {
            location.replace("../hotel_list/hotel_list.html");
        }
    };
    xhttp.open("POST", getBackendUrl() + '/api/hotels/')

    const request = {
        'name': document.getElementById('name').value,
        'age': parseInt(document.getElementById('age').value),
        'phoneNumber': document.getElementById('phone').value
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
}