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
    let table = `<table class="table">
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

    await fetch('http://localhost:8080/Garcia_Jorge_COM1/api/v1/room?capacity=' + capacity)
        .then(response => response.json())
        .then(data => {
            console.log(data);
            data.forEach(element => {
                table += `<tr>
            <th scope="row">` + element.door + `</th>
            <td>` + element.floor + `</td>
            <td>` + element.capacity + `</td>
            <td>` + element.price + `</td>
            <td><button class="btn btn-primary" onclick="detailsRoom( ${element.id}, '${element.door}' )">Detalle</button></td>
          </tr>`
            });
            table += ` </tbody>
        </table><div id="detailsRoom"></div>`;
            document.getElementById("rooms").innerHTML = table;
        });
}

var room_id;
function detailsRoom(id_room, room_door) {
    room_id = id_room;
    let calendar = `<br><p class="lead">
    Habitacion: <b>${room_door}</b> <br/> Para realizar una reserva seleccione dia de entrada y dia de salida` + `
    </p>
<table class="table table-bordered border-primary">
<thead>
  <tr>
    <th scope="col">Domingo</th>
    <th scope="col">Lunes</th>
    <th scope="col">Martes</th>
    <th scope="col">Miercoles</th>
    <th scope="col">Jueves</th>
    <th scope="col">Viernes</th>
    <th scope="col">Sabado</th>  
  </tr>
</thead>
<tbody>`;
    fetch('http://localhost:8080/Garcia_Jorge_COM1/api/v1/reservation?room_id=' + id_room)
        .then(response => response.json())
        .then(data => {

            document.getElementById("detailsRoom").innerHTML = calendar;
            console.log(data);
            calendar += generateCalendar() + `</table><div id="form-reserve"></div>`;
            document.getElementById("detailsRoom").innerHTML = calendar;
            selectedDay(data);
        });
}

function generateCalendar() {
    let date = new Date();
    let first_day = new Date(date.getFullYear(), date.getMonth(), 1).getDay();
    let last_day = 33 - new Date(date.getFullYear(), date.getMonth() + 1, 0).getDay();
    let tbody = ``;
    count = 1;
    for (i = 0; i < 5; i++) {
        tbody += `<tr>`;
        for (j = 0; j < 7; j++) {
            if (count >= first_day && count <= last_day) {
                tbody += `<td id="` + count + `" onclick="getDay(` + count + `)" >` + (count) + `</td>`;
            } else {
                tbody += `<td>` + `</td>`;
            }
            count++;
        }
        tbody += `</tr>`;
    }
    tbody += `</tbody>`;
    return tbody;
}

function selectedDay(data) {
    data.forEach(d => {
        console.log(new Date(d.date_in).getDate());
        console.log(new Date(d.date_out).getDate());
        for (i = new Date(d.date_in).getDate(); i < new Date(d.date_out).getDate() + 1; i++) {
            document.getElementById(i).style.backgroundColor = "blue";
            document.getElementById(i).onclick = null;
        }
    });
}

var day_in = null;
var day_out = null;
var style_cell = null;
function getDay(day) {
    if (day_in === null) {
        day_in = day;
        alert("Seleccione otro dia");
    } else
        day_out = day;
    if (day_in !== null && day_out !== null && day_in < day_out) {
        document.getElementById("form-reserve").innerHTML = `<div class="container">
            <div class="row">
                <div class="col-md-4">Fecha ingreso: ${day_in}</div>
                <div class="col-md-4">Fecha egreso: ${day_out}</div>
                <div class="col-md-3"><button class="btn btn-danger" onclick="deshacerForm()">Deshacer</button></div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <label>Nombre:</label>
                    <input type="text" name="name" id="name-form" required>
                </div>
                <div class="col-md-6">
                    <label>Apellido:</label>
                    <input type="text" name="lastname" id="lastname-form" required>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <label>Dni:</label>
                    <input type="text" name="dni" id="dni-form" required>
                </div>
                <div class="col-md-6">
                    <label>Profesion:</label>
                    <input type="text" name="profesion" id="profesion-form" required>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4"><label>Fecha Nacimiento</label></div>
                <div class="col-md-1"><input type="text" width="10px" id="dia-form" placeholder="30" size="13" required></div>
                <div class="col-md-1"><input type="text" width="10px" id="mes-form" placeholder="12" size="15" required></div>
                <div class="col-md-1"><input type="text" width="10px" id="anio-form"placeholder="1999" size="10" required></div>
            </div>
            <div class="row">
                <div class="col-md-3"><button class="btn btn-primary" onclick="submitReserve()">Realizar Reserva</button></div>
            </div>
             </div>`
    } else if (day_in !== null && day_out !== null) {
        alert("Fecha ingreso " + day_in + "no puede ser mayor que Fecha de egreso " + day_out);
        day_in = null;
        day_out = null;
    }
}

function deshacerForm() {
    document.getElementById("form-reserve").innerHTML = ``;
    day_in = null;
    day_out = null;
}

function submitReserve() {
    let isvalid = true;
    let reserve;
    let name = document.getElementById("name-form").value;
    let lastname = document.getElementById("lastname-form").value;
    let dni = document.getElementById("dni-form").value;
    let day = document.getElementById("dia-form").value;
    let month = document.getElementById("mes-form").value;
    let year = document.getElementById("anio-form").value;
    let profession = document.getElementById("profesion-form").value;
    let birthday = new Date(year, month - 1, day);
    console.log("id employee " + employee_id + "  room_id " + room_id);
    if (name.length === 0) {
        invalid("name-form");
        isvalid = false;
    }
    if (lastname.length === 0) {
        invalid("lastname-form");
        isvalid = false;
    }
    if (dni.length === 0) {
        invalid("dni-form");
        isvalid = false;
    }
    if (day.length === 0) {
        invalid("dia-form");
        isvalid = false;
    }
    if (month.length === 0) {
        invalid("mes-form");
        isvalid = false;
    }
    if (year.length === 0) {
        invalid("anio-form");
        isvalid = false;
    }
    if (profession.length === 0) {
        invalid("profesion-form");
        isvalid = false;
    }

    if (isvalid) {
        f = new Date();
        console.log(f.getFullYear() + "  " + f.getMonth());
        input = formatDate(new Date(f.getFullYear(), f.getMonth(), day_in).toDateString());
        output = formatDate(new Date(f.getFullYear(), f.getMonth(), day_out).toDateString());
        reserve =
        {
            date_in: input,
            date_out: output,
            room_id: room_id,
            employee_id: employee_id,
            name: name,
            lastname: lastname,
            dni: dni,
            birthday: birthday,
            profession: profession
        }
        console.log(reserve);
        fetch('http://localhost:8080/Garcia_Jorge_COM1/api/v1/reservation/data', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(reserve)

        }).then(response => response.json())
            .then(data => {
                console.log(data);
                if (data !== null) {
                    let table = `<br><table class="table">
                                    <thead>
                                      <tr>
                                        <th scope="col"># Id</th>
                                        <th scope="col">Nombre</th>
                                        <th scope="col">Apellido</th>
                                        <th scope="col">DNI</th>
                                        <th scope="col">Profesion</th>
                                        <th scope="col">Fecha Ingreso</th>
                                        <th scope="col">Fecha Egreso</th>
                                      </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                          <th scope="row"> ${data.id}</th>
                                          <td>${data.name}</td>
                                          <td>${data.lastname}</td>
                                          <td>${data.dni}</td>
                                          <td>${data.profession}</td>
                                          <td>${data.date_in}</td>
                                          <td>${data.date_out}</td>
                                        </tr>
                                    </tgody></table>`;
                    document.getElementById("form-reserve").innerHTML = table;
                }
            });
    }
}

function invalid(id) {
    document.getElementById(id).style = "border: 2px dashed red; background-image: linear-gradient(to right, pink, lightgreen);";
    document.getElementById(id).setAttribute("placeholder", "requerido");
}

function formatDate(date) {
    newdate = date.split(" ");
    date = '';
    date += newdate[1] + ' ' + newdate[2] + ', ' + newdate[3] + ' 00:00:00 AM';
    return date;
}

async function showCreateReserve(data) {


    await data.forEach(guest => {
        table += `<tr>
                  <th scope="row"></th>
                  <td>Mark</td>
                  <td>Otto</td>
                  <td>@mdo</td>
                  <td>@mdo</td>
                </tr>`
    });
    table += `</tbody></table>`;
    document.getElementById("form-reserve").innerHTML = table;
}


