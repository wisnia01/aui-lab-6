import {getParameterByName} from '../js/utils.js';
import {getBackendUrl} from '../js/config.js';

window.addEventListener('load', () => {
    const infoForm = document.getElementById('infoForm');
    const portraitForm = document.getElementById('portraitForm');

    infoForm.addEventListener('submit', event => updateInfoAction(event));
    portraitForm.addEventListener('submit', event => uploadPortraitAction(event));
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
function uploadPortraitAction(event) {
    event.preventDefault();

    const xhttp = new XMLHttpRequest();
    xhttp.open("PUT", getBackendUrl() + '/api/hotels/' +  'portrait', true);

    let request = new FormData();
    request.append("portrait", document.getElementById('portrait').files[0]);
    request.append("description", document.getElementById('description').value);
    request.append("portraitName", document.getElementById('portraitName').value);

    const request2 = {
        "portrait": document.getElementById('portrait').files[0],
        "description": document.getElementById('description').value,
        "portraitName": document.getElementById('portraitName').value
    };


    xhttp.send(request);

}
