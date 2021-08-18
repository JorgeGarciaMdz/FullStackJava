var show_reservation = true;

function showAllReservation() {
    if (show_reservation) {
        let fecha = `<div class="row">
                        <div class="input-group mb-3">
                            <label class="input-group-text" for="start">Fecha Desde</label>
                            <input type="date" id="startDate" name="trip-start" value="2021-08-01"
                                min="2021-01-01" max="2022-01-01" class="form-control">
                        </div>
                        <div class="input-group mb-3">
                            <label class="input-group-text" for="end">Fecha Hasta</label>
                            <input type="date" id="endDate" name="trip-start" value="2022-08-01"
                                min="2021-01-01" max="2022-01-01" class="form-control">
                        </div>
                        <div class="col">
                            <button class="btn btn-info" onclick="checkReservations()" > Consultar </button>
                        </div>
                    </div>
                    <div class="modal" tabindex="-1" id="modal-error-date">
                      <div class="modal-dialog">
                        <div class="modal-content">
                          <div class="modal-header">
                            <h5 class="modal-title">Error de Fechas</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                          </div>
                          <div class="modal-body">
                            <p>La Fecha Desde no puede ser mayor a Fecha Hasta</p>
                          </div>
                          <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="button" class="btn btn-primary">Save changes</button>
                          </div>
                        </div>
                      </div>
                    </div>

                <div class="row" id="tableReservation"></div>`;
        document.getElementById("root").innerHTML = fecha;
        show_reservation = false;
    } else {
        document.getElementById("root").innerHTML = "";
        show_reservation = true;
    }
}

function checkReservations() {
    let date = getDates();
    console.log(date);
}


function getDates() {
    let startDate = document.getElementById("startDate").value;
    let endDate = document.getElementById("endDate").value;

    if (startDate > endDate) {
        let modal_date= new bootstrap.Modal(document.getElementById("modal-error-date"), {keyboard: false});
        modal_date.show();
        return null;
    } else {
        return {
            startDate: startDate,
            endDate: endDate
        }
    }
}