window.addEventListener('load', function () {
    const formulario = document.querySelector('#update_turno_form');
    formulario.addEventListener('submit', function (event) {
        const formData = {
            id: document.querySelector('#id').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            dni: document.querySelector('#dni').value,
            domicilio: {
                calle: document.querySelector('#calle').value,
                numero: document.querySelector('#numero').value,
                localidad: document.querySelector('#localidad').value,
                provincia: document.querySelector('#provincia').value
            }
        };
        const url = '/turnos';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
        fetch(url,settings)
            .then(response => response.json())

    })
})

function findBy(id) {
    const url = '/turnos'+"/"+id;
    const settings = {
        method: 'GET'
    }
    fetch(url,settings)
        .then(response => response.json())
        .then(data => {
            let turno = data;
            document.querySelector('#id').value = turno.id;
            document.querySelector('#nombre').value = turno.nombre;
            document.querySelector('#apellido').value = turno.apellido;
            document.querySelector('#dni').value = turno.dni;
            document.querySelector('#calle').value = turno.domicilio.calle;
            document.querySelector('#numero').value = turno.domicilio.numero;
            document.querySelector('#localidad').value = turno.domicilio.localidad;
            document.querySelector('#provincia').value = turno.domicilio.provincia;

            document.querySelector('#div_turno_updating').style.display = "block";
        }).catch(error => {
        alert("Error: " + error);
    })
}