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