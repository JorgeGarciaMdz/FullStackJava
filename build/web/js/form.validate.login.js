/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function () {
    'use strict';

    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    var forms = document.querySelectorAll('.login-form-needs-validation');

    // Loop over them and prevent submission
    Array.prototype.slice.call(forms)
            .forEach(function (form) {
                form.addEventListener('submit', function (event) {
                    if (!form.checkValidity()) {
                        event.preventDefault();
                        event.stopPropagation();
                    }

                    form.classList.add('was-validated');
                }, false);
            });
})();

function submitEncryption() {

    var txtPassWord = document.getElementById("login-pass").value.trim();

    
}

function a (){
    let b = "<%= session.getAttribute(" + "user" + ")%>";
    console.log( ">" + b);
}

