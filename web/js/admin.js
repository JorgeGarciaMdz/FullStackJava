var show_reservation = true;
var show_reservation_employee = true;

function showAllReservation() {
    if (show_reservation) {
        let fecha = `<div class="row">
                        <div class="col-md-4">
                            <div class="input-group mb-3">
                            <label class="input-group-text" for="start">Fecha Desde</label>
                            <input type="date" id="startDate" name="trip-start" value="2021-08-01"
                                min="2021-01-01" max="2022-01-01" class="form-control">
                        
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="input-group mb-3">
                                <label class="input-group-text" for="end">Fecha Hasta</label>
                                <input type="date" id="endDate" name="trip-start" value="2021-12-01"
                                    min="2021-01-01" max="2022-01-01" class="form-control">
                            </div>
                        </div>
                        <div class="col">
                            <button class="btn btn-info" onclick="checkReservations()" > Consultar </button>
                        </div>
                    </div>
                    <div class="row" id="table-reservation"></div>`;
        document.getElementById("root").innerHTML = fecha;
        document.getElementById("link-show-reservation").setAttribute("class", "nav-link active")
        console.log(document.getElementById("link-show-reservation").getAttribute("class"));
        show_reservation = false;
    } else {
        document.getElementById("root").innerHTML = "";
        show_reservation = true;
    }
}

function checkReservations() {
    let date = getDates();
    if (date === null) {
        launchModal("Fecha Desde no puede ser mayor a Fecha Hasta", "Error dia seleccionado", "table-reservation");
    } else {
        console.log(date);
        fetch('http://localhost:8080/Garcia_Jorge_COM1/api/v1/reservation'+ 
              `?date_from=${date.date_from}&date_to=${date.date_to}`)
            .then(request => request.json())
            .then(data => {
              document.getElementById("table-reservation").innerHTML = getTableReservations(data);
            });
    }
}

function getDates() {
    let date_from = document.getElementById("startDate").value;
    let date_to = document.getElementById("endDate").value;

    if (date_from > date_to) {
        return null;
    } else {
        return {
            date_from: date_from,
            date_to: date_to
        }
    }
}

function getTableReservations(data) {
    let table = `
                <table class="table">
                  <thead>
                    <tr>
                      <th scope="col">#</th>
                      <th scope="col">Habitacion</th>
                      <th scope="col">Ingreso</th>
                      <th scope="col">Egreso</th>
                      <th scope="col">Nombre y Apellido</th>
                      <th scope="col">DNI</th>
                      <th scope="col">Profesion</th>
                    </tr>
                  </thead>
                  <tbody> `;
    data.forEach( d => {
        table += `
                <tr>
                  <th scope="row">${d.id}</th>
                  <th scope="row">${d.room.door}</th>
                  <td>${d.date_in}</td>
                  <td>${d.date_out}</td>
                  <td>${d.guest.name} - ${d.guest.lastname}</td>
                  <td>${d.guest.dni}</td>
                  <td>${d.guest.profession}</td>
                </tr>
                `;
    });
    return table += `</tbody>
                    </table>`;
}

function launchModal(messageBody, messageTitle, idDiv) {
    let modal = `</div>
                    <div class="modal fade" id="modal-generic" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                      <div class="modal-dialog">
                        <div class="modal-content">
                          <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">${messageTitle}</h5>
                            <!--<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button> -->
                          </div>
                          <div class="modal-body">
                            ${messageBody}
                          </div>
                          <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                          </div>
                        </div>
                      </div>
                    </div>
                </div>`;
    document.getElementById(idDiv).innerHTML = modal;
    modalDia = new bootstrap.Modal(document.getElementById('modal-generic'), { keyborad: false });
    modalDia.toggle();
}

// Mostrando todos los empleados activos
function showAllEmployee() {
  fetch('http://localhost:8080/Garcia_Jorge_COM1/api/v1/employee')
    .then( request => request.json())
    .then( data => {
      console.log(data);
      document.getElementById("root").innerHTML = getTableEmployee(data);
    });
  
}

function getTableEmployee( data ) {
  let table = `<div class="row"><table class="table">
                <thead>
                  <tr>
                    <th scope="col">#</th>
                    <th scope="col">Usuario</th>
                    <th scope="col">Nombre</th>
                    <th scope="col">Apellido</th>
                    <th scope="col">DNI</th>
                    <th scope="col">Fecha de Naciminento</th>
                    <th scope="col">Fecha Ingreso</th>
                    <th scope="col">Tipo</th>
                    <th></th>
                  </tr>
                </thead>
                <tbody> `;
  data.forEach( d => {
      table += `
              <tr>
                <th scope="row">${d.id}</th>
                <th scope="row">${d.user}</th>
                <td>${d.name}</td>
                <td>${d.lastname}</td>
                <td>${d.dni}</td>
                <td>${d.birthday}</td>
                <td>${d.admission_date}</td>
                <td>${d.type}</td>
                <td><button class="btn btn-info" onclick="getReservationEmployee(${d.id})">Consultar Reservas</button></td>
              </tr>
              `;
  });
  return table += `</tbody>
                    </table></div><div class="row" id="table-reservation-employee"></div>`;
}

function getReservationEmployee(id) {
  
}