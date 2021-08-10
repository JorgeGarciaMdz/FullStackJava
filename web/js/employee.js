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
function newReserve() {
    let menu = `<div class="container">
                    <div class="row">
                    <div class="col-md-2"><button class="btn btn-secondary" onclick="showRooms(1)">Simple</button></div>
                
                    <div class="col-md-2"><button class="btn btn-secondary" onclick="showRooms(2)">Doble</button></div>
                
                    <div class="col-md-2"><button class="btn btn-secondary" onclick="showRooms(3)">Tripe</button></div>
                
                    <div class="col-md-2"><button class="btn btn-secondary" onclick="showRooms(4)">Multiple</button></div>
                    </div>
                </div><div id="rooms"></div>`;
    document.getElementById("root").innerHTML = menu;
}

async function showRooms(capacity) {
    table = `<table class="table">
<thead>
  <tr>
    <th scope="col"># Puerta</th>
    <th scope="col">Piso</th>
    <th scope="col">Capacidad</th>
    <th scope="col">Precio</th>
    <th scope=""></th>
  </tr>
</thead>
<tbody>`

    await  fetch('http://localhost:8080/Garcia_Jorge_COM1/api/v1/room?capacity=' + capacity)
            .then(response => response.json())
            .then(data => {
                console.log(data);
                data.forEach(element => {
                    table += `<tr>
            <th scope="row">` + element.door + `</th>
            <td>` + element.floor + `</td>
            <td>` + element.capacity + `</td>
            <td>` + element.price + `</td>
            <td><button class="btn btn-primary" onclick="detalleRoom(`+ element.id +` )">Detalle</button></td>"
          </tr>`
                });
                table += ` </tbody>
        </table><div id="detailsRoom"></div>`;
        document.getElementById("rooms").innerHTML = table;
            });
}

function detalleRoom(room_id) {
    fetch('http://localhost:8080/Garcia_Jorge_COM1/api/v1/reservation?room_id=' + room_id)
        .then( response => response.json())
        .then( data => {
            console.log(data);
        })
}