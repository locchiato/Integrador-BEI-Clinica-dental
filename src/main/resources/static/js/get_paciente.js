window.addEventListener('load', function () {
    (function(){
        const url = '/pacientes';
        const settings = {
            method: 'GET'
        }
        fetch(url,settings)
            .then(response => response.json())
            .then(data => {
                for (paciente of data) {

                    let deleteButton = '<button' +
                        ' id=' + '\"' + 'btn_delete_' + paciente.id + '\"' +
                        ' type="button" onclick="deleteBy('+paciente.id+')" class="btn btn-danger btn_delete">' +
                        '&times' +
                        '</button>';

                    let get_More_Info_Btn = '<button' +
                        ' id=' + '\"' + 'btn_id_' + paciente.id + '\"' +
                        ' type="button" onclick="findBy('+paciente.id+')" class="btn btn-info btn_id">' +
                        paciente.id +
                        '</button>';

                    let tr_id = 'tr_' + paciente.id;
                    let pacienteRow = '<tr id=\"' + tr_id + "\"" + '>' +
                        '<td>' + get_More_Info_Btn + '</td>' +
                        '<td class=\"td_first_name\">' + paciente.nombre.toUpperCase() + '</td>' +
                        '<td class=\"td_last_name\">' + paciente.apellido.toUpperCase() + '</td>' +
                        '<td class=\"td_dni\">' + paciente.dni + '</td>' +
                        '<td class=\"td_dom_street\">' + paciente.domicilio.calle + '</td>' +
                        '<td class=\"td_dom_number\">' + paciente.domicilio.numero + '</td>' +
                        '<td class=\"td_dom_locality\">' + paciente.domicilio.localidad + '</td>' +
                        '<td class=\"td_dom_province\">' + paciente.domicilio.provincia + '</td>' +
                        '<td>' + deleteButton + '</td>' +
                        '</tr>';
                    $('#pacienteTable tbody').append(pacienteRow);
                }

            }).catch(error =>{
            alert("ERROR: ", error);
            console.log("ERROR: ", error);
        })

    })();        
    
    (function(){
        let pathname = window.location.pathname;
        if (pathname == "/pacientes.html") {
            $(".nav .nav-item a:last").addClass("active");
        }
    })();
});