var show_reservation = true;
var show_reservation_employee = true;

function showAllReservation() {
  if (show_reservation) {
    tableDates("root", "checkAllReservations()", "Consultar Reservas Por Fecha");
    document.getElementById("link-show-reservation").setAttribute("class", "nav-link active")
    console.log(document.getElementById("link-show-reservation").getAttribute("class"));
    show_reservation = false;
  } else {
    document.getElementById("root").innerHTML = "";
    show_reservation = true;
  }
}

function tableDates(idDiv, functionOnClick, title = '') {
  let fecha = ` <div class="row">
                  <blockquote class="blockquote">
                    <p>${title}</p>
                  </blockquote>
                </div>
                <div class="row">
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
                            <button class="btn btn-info" onclick="${functionOnClick}" > Consultar </button>
                        </div>
                    </div>
                    <div class="row" id="table-reservation"></div>`;
  document.getElementById(idDiv).innerHTML = fecha;
}

function checkAllReservations() {
  let date = getDates();
  if (date !== null) {
    console.log(date);
    fetch('http://localhost:8080/Garcia_Jorge_COM1/api/v1/reservation' +
      `?date_from=${date.date_from}&date_to=${date.date_to}`)
      .then(request => request.json())
      .then(data => {
        getTableReservations(data, "table-reservation");
      });
  }
}

function getDates() {
  let date_from = document.getElementById("startDate").value;
  let date_to = document.getElementById("endDate").value;

  if (date_from > date_to) {
    launchModal("Fecha Desde no puede ser mayor a Fecha Hasta", "Error dia seleccionado", "table-reservation");
    return null;
  } else {
    return {
      date_from: date_from,
      date_to: date_to
    }
  }
}

function getTableReservations(data, idDiv) {
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
  data.forEach(d => {
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
  document.getElementById(idDiv).innerHTML = table += `</tbody>  </table>`;
}

function launchModal(messageBody, messageTitle, idDiv, callback = null) {
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
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal"
                            onclick="${(callback === null ? '' : callback)}">Close</button>
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
  if (show_reservation_employee) {
    fetch('http://localhost:8080/Garcia_Jorge_COM1/api/v1/employee')
      .then(request => request.json())
      .then(data => {
        console.log(data);
        document.getElementById("root").innerHTML = getTableEmployee(data);
      });
    show_reservation_employee = false;
  } else {
    document.getElementById("root").innerHTML = "";
    show_reservation_employee = true;
  }

}

function getTableEmployee(data) {
  let table = ` <div class="row">
                  <blockquote class="blockquote">
                    <p>Consultar Reservas Por Empleado</p>
                  </blockquote>
                </div>
              <div class="row"><table class="table">
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
  data.forEach(d => {
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
                <td>${(d.discharge_date !== undefined ? '---' :
        `<button class="btn btn-info" onclick="getReservationEmployee(${d.id})">Consultar Reservas</button>`)}
                </td>
              </tr>
              `;
  });
  return table += `</tbody>
                    </table></div><div class="row" id="table-reservation-employee"></div>`;
}

function getReservationEmployee(idEmployee) {
  tableDates("table-reservation-employee", `checkReservationEmployee(${idEmployee})`);
}

function checkReservationEmployee(idEmployee) {
  let date = getDates();
  if (date !== null) {
    fetch('http://localhost:8080/Garcia_Jorge_COM1/api/v1/reservation' +
      `?date_from=${date.date_from}&date_to=${date.date_to}&id_employee=${idEmployee}`)
      .then(request => request.json())
      .then(data => {
        console.log(data);
        getTableReservations(data, "table-reservation");
      });
  }
}

function showAdminEmployee() {
  fetch('http://localhost:8080/Garcia_Jorge_COM1/api/v1/employee')
    .then(response => response.json())
    .then(data => {
      document.getElementById("root").innerHTML = getAdminTableEmployee(data);
    });
}

function getAdminTableEmployee(data) {
  let table = `<div class="row">
                <div class="col-md-10">
                  <h3>Gestionar Empleados</h3>
                </div>
                <div class="col">
                  <button class="btn btn-primary" onclick="newEmployee()">Nuevo</button>
                </div>
              </div>
              <div class="row"><table class="table">
                <thead>
                  <tr>
                    <th scope="col">#</th>
                    <th scope="col">Nombre</th>
                    <th scope="col">Apellido</th>
                    <th scope="col">DNI</th>
                    <th scope="col">Fecha Nacimiento</th>
                    <th scope="col">Nombre Usuario</th>
                    <th scope="col">Tipo</th>
                    <th scope="col">Fecha Ingreso</th>
                    <th scope="col">Fecha Baja</th>
                    <th scope="col"></div>
                    <th scope="col"></div>
                  </tr>
                </thead>
                <tbody>`;
  data.forEach(d => {
    table += `<tr>
                <th scope="row">${d.id}</th>
                <td>${d.name}</td>
                <td>${d.lastname}</td>
                <td>${d.dni}</td>
                <td>${d.birthday}</td>
                <td>${d.user}</td>
                <td>${d.type}</td>
                <td>${d.admission_date}</td>
                <td>${(d.discharge_date === undefined ? '---' : d.discharge_date)}</td>
                <td>
                  <button class="btn btn-warning"  
                  onclick="editEmployee(${d.id})">Editar</button>
                </td>
                <td>
                  ${(d.discharge_date === undefined ? '<button class="btn btn-danger" onclick="downEmployee(' + d.id + ')">Baja</button>' : '---')}
                </td>
              </tr>`;
  });
  return table += `</tbody>
                  </table>
                </div>
                <div class="row" id="delete-employee"></div>`;
}

function newEmployee(data = undefined) {
  let birthday = "";
  if ( data !== undefined) {
    let date = new Date(data.birthday);
    let year = date.getFullYear();
    let month = date.getMonth() + 1;
    let day = date.getDate();
    if (month < 10) {
      birthday += year + "-0" + month;
    } else {
      birthday += year + "-" + month;
    }
    if ( day < 10 ){
      birthday += "-0" + day;
    } else {
      birthday += "-" + day;
    }
  } else {
    birthday = "1990-01-01";
  }
  form = `<div class="row form-employee">
            <div class="col">
              <label class="form-label">Nombre</label>
              <input type="text" id="form-name" class="form-control" placeholder="Nombre" aria-label="Nombre"
              value="${data === undefined ? '' : data.name}">
            </div>
            <div class="col">
              <label class="form-label">Apellido</label>
              <input type="text" id="form-lastname" class="form-control" placeholder="Apellido" aria-label="Apellido"
              value="${data === undefined ? '' : data.lastname}">
            </div>
            </div>
            <div class="row form-employee">
              <div class="col">
                <label class="form-label">DNI</label>
                <input type="text" id="form-dni" class="form-control" placeholder="DNI" aria-label="DNI"
                value="${data === undefined ? '' : data.dni}">
              </div>
              <div class="col">
                <label class="form-label">Fecha Nacimiento</label>
                <input type="date" id="form-birthday" class="form-control"
                value="${birthday}">
              </div>
            </div>
            <div class="row form-employee">
              <div class="col">
                <label class="form-label">Nombre de Usuario</label>
                <input type="text" id="form-user" class="form-control" placeholder="Nombre Usuario" aria-label="Nombre Usuario"
                value="${data === undefined ? '' : data.user}">
              </div>
              <div class="col">
                <label class="form-label">Contrase??a</label>
                <input type="text" id="form-password" class="form-control" placeholder="Contrase??a" aria-label="Contrase??a">
              </div>
            </div>
            <div class="row form-employee">
              <div class="col-md-6">
                <label class="form-label">Tipo</label>
                <select id="form-type" class="form-select">
                  <option selected>employee</option>
                  <option>administrator</option>
                </select>
              </div>
            </div>
            <div class="row form-employee">
              <div class="col">
                <button class="btn btn-primary" onclick="getDataFormEmployee(
                  ${(data === undefined? undefined: data.id)},
                  '${(data === undefined? undefined: data.admission_date)}')">Enviar</button>
              </div>
              <div class="col">
                <button class="btn btn-info" onclick="showAdminEmployee()">Volver</button>
              </div>
            </div>
            <div class="row" id="form-modal"></div>`;
  document.getElementById("root").innerHTML = form;
}

function getDataFormEmployee( id = undefined, admission_date = undefined) {
  let name = document.getElementById("form-name").value;
  let lastname = document.getElementById("form-lastname").value;
  let dni = document.getElementById("form-dni").value;
  let birthday = document.getElementById("form-birthday").value;
  let user = document.getElementById("form-user").value;
  let password = document.getElementById("form-password").value;
  let type = document.getElementById("form-type").value;
  let data = null;
  let method = 'POST';
  
  if( id === undefined ){
    data = {
      name: name,
      lastname: lastname,
      dni: dni,
      birthday: birthday,
      user: user,
      password: password,
      type: type,
    }
  } else {
    data = {
      id: id,
      name: name,
      lastname: lastname,
      dni: dni,
      birthday: birthday,
      user: user,
      password: password,
      type: type,
      admission_date: admission_date,
    }
    method = 'PUT';
  }

  let parameter = {
    method: method,
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
  }

  fetch('http://localhost:8080/Garcia_Jorge_COM1/api/v1/employee', parameter)
    .then(response => {
      console.log("status: " + response.status);
      if (response.status === 500) {
        launchModal("No se ha podido realizar la transaccion", "Error de Servidor", "form-modal", 'showAdminEmployee()');
      } else if (response.status === 400) {
        launchModal("Los datos enviados tienen errores o hay campos vacios", "Error de Datos", "form-modal", 'showAdminEmployee()');
      } else {
        launchModal("Se ha realizado la transaccion con ??xito!", "Datos Grabados", "form-modal", 'showAdminEmployee()');
      }
    });
}

function editEmployee(id_employee) {
  fetch('http://localhost:8080/Garcia_Jorge_COM1/api/v1/employee?id=' + id_employee)
    .then(response => response.json())
    .then(body => {
      newEmployee(body);
    });
}

function downEmployee(id_employee) {
  fetch('http://localhost:8080/Garcia_Jorge_COM1/api/v1/employee?id='+ id_employee, {method: 'DELETE'})
    .then( response => {
      if (response.status === 200) {
        launchModal("Se dio de baja al empleado", "Baja Exitosa","delete-employee", "showAdminEmployee()");
      } else {
        launchModal("Ha ocurrido un error al procesar la solicitud", "Error de Procesamiento", "delete-employee");
      }
    })
}