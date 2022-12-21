import {getParameterByName} from '../js/utils.js';
import {getBackendUrl} from '../js/config.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');

    UpdateOwnerField();

    infoForm.addEventListener('submit', event => updateInfoAction(event));
});

/**
 * Action event handled for updating room info.
 * @param {Event} event dom event
 */
function updateInfoAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 201) {
            location.replace("../hotel_view/hotel_view.html?hotel=" + getParameterByName("hotel"));
        }
    };
    xhttp.open("POST", getBackendUrl() + '/api/rooms/')

    const request = {
        'name': document.getElementById('name').value,
        'area': parseInt(document.getElementById('area').value),
        'number': document.getElementById('number').value,
        'owner': getParameterByName("hotel")
    };

    xhttp.setRequestHeader('Content-Type', 'application/json');

    xhttp.send(JSON.stringify(request));
}

function UpdateOwnerField() {
    document.getElementById('owner').value = getParameterByName("hotel");
}