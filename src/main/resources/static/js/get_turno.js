window.addEventListener('load', function () {
    (function(){
        const url = '/turnos';
        const settings = {
            method: 'GET'
        }
        fetch(url,settings)
            .then(response => response.json())
            .then(data => {
                for (turno of data) {

                    let deleteButton = '<button' +
                        ' id=' + '\"' + 'btn_delete_' + turno.id + '\"' +
                        ' type="button" onclick="deleteBy('+turno.id+')" class="btn btn-danger btn_delete">' +
                        '&times' +
                        '</button>';

                    let get_More_Info_Btn = '<button' +
                        ' id=' + '\"' + 'btn_id_' + turno.id + '\"' +
                        ' type="button" onclick="findBy('+turno.id+')" class="btn btn-info btn_id">' +
                        turno.id +
                        '</button>';

                    let tr_id = 'tr_' + turno.id;
                    let turnoRow = '<tr id=\"' + tr_id + "\"" + '>' +
                        '<td>' + get_More_Info_Btn + '</td>' +
                        '<td class=\"td_full_name_paciente\">' + turno.paciente.nombre.toUpperCase() + ' ' + turno.paciente.apellido.toUpperCase() + '</td>' +
                        '<td class=\"td_full_name_odontologo\">' + turno.odontologo.nombre.toUpperCase() + ' ' + turno.odontologo.apellido.toUpperCase() + '</td>' +
                        '<td class=\"td_date_turno\">' + turno.fecha + '</td>' +
                        '<td>' + deleteButton + '</td>' +
                        '</tr>';
                    $('#turnoTable tbody').append(turnoRow);
                }

            }).catch(error =>{
            alert("ERROR: ", error);
            console.log("ERROR: ", error);
        })

    })();        
    
    (function(){
        let pathname = window.location.pathname;
        if (pathname == "/turnos.html") {
            $(".nav .nav-item a:last").addClass("active");
        }
    })();
});