/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




function getEmployee() {
    console.log("Realizando get ");
    fetch('http://localhost:8080/Garcia_Jorge_COM1/api/v1/employee/')
        .then(response => response.json())
        .then(data => console.log(data));
}

async function newReserve() {
    button = 
    `<div class="container">
        <div class="row">
            <div class="col-md-2">
                <button type="button" class="btn btn-secondary" onclick="getRooms(1)">Simple</button>
            </div>
            <div class="col-md-2">
                <button type="button" class="btn btn-secondary" onclick="getRooms(2)">Doble</button>
            </div>
            <div class="col-md-2">
                <button type="button" class="btn btn-secondary" onclick="getRooms(3)">Triple</button>
            </div>
            <div class="col-md-2">
                <button type="button" class="btn btn-secondary" onclick="getRooms(4)">Multiple</button>
            </div>
        </div>
    </div> <div id="room"></div>`;
    document.getElementById("root").innerHTML = button;
}

async function getRooms( capacity ) {
    alert("d");
}

