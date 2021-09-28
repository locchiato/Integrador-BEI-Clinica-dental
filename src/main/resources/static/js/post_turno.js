window.addEventListener('load', function () {
    const formulario = document.querySelector('#add_new_turno');
    formulario.addEventListener('submit', function (event) {
        event.preventDefault();
        console.log("Sending post")
        let formData = {
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            dni: document.querySelector('#dni').value,
            domicilio: {
                calle: document.querySelector("#calle").value,
                numero: document.querySelector('#numero').value,
                localidad: document.querySelector('#localidad').value,
                provincia: document.querySelector('#provincia').value,
            }
        }
        const url = '/turnos';

        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
        fetch(url,settings).then(response => response.json()).then(data => {
            let successAlert = '<div class="alert alert-success alert-dismissible">' +
                '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                '<strong></strong> Paciente agregado </div>'

            agregarMensaje(successAlert);
            resetUploadForm();

        }).catch(error =>{
            let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                '<strong> Error intente nuevamente</strong> </div>'

            agregarMensaje(errorAlert);
            resetUploadForm();
        })

    });

    function agregarMensaje(mensaje) {
        document.querySelector("#response").innerHTML = mensaje;
        document.querySelector("#response").style.display = "block";
    }

    function resetUploadForm(){
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";
        document.querySelector('#dni').value = "";
        document.querySelector('#calle').value = "";
        document.querySelector('#numero').value = "";
        document.querySelector('#localidad').value= "";
        document.querySelector('#provincia').value= "";

    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            $(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/turnos.html") {
            $(".nav .nav-item a:last").addClass("active");
        }
    })();
});