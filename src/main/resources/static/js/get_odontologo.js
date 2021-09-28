window.addEventListener('load', function () {
    (function(){
        const url = '/odontologos';
        const settings = {
            method: 'GET'
        }
        fetch(url,settings)
            .then(response => response.json())
            .then(data => {
                for (odontologo of data) {

                    let deleteButton = '<button' +
                        ' id=' + '\"' + 'btn_delete_' + odontologo.id + '\"' +
                        ' type="button" onclick="deleteBy(' + odontologo.id + ')" class="btn btn-danger btn_delete">' +
                        '&times' +
                        '</button>';

                    let get_More_Info_Btn = '<button' +
                        ' id=' + '\"' + 'btn_id_' + odontologo.id + '\"' +
                        ' type="button" onclick="findBy(' + odontologo.id + ')" class="btn btn-info btn_id">' +
                        odontologo.id +
                        '</button>';

                    let tr_id = 'tr_' + odontologo.id;
                    let odontologoRow = '<tr id=\"' + tr_id + "\"" + '>' +
                        '<td>' + get_More_Info_Btn + '</td>' +
                        '<td class=\"td_first_name\">' + odontologo.nombre.toUpperCase() + '</td>' +
                        '<td class=\"td_last_name\">' + odontologo.apellido.toUpperCase() + '</td>' +
                        '<td class=\"td_matricula\">' + odontologo.matricula + '</td>' +
                        '<td>' + deleteButton + '</td>' +
                        '</tr>';
                    document.querySelector('#odontologoTable tbody').innerHTML += odontologoRow;
                }
            }).catch(error =>{
            alert("ERROR: ", error);
            console.log("ERROR: ", error);
        })

    })();        
    
    (function(){
        let pathname = window.location.pathname;
        if (pathname == "/odontologos.html") {
            document.querySelector(".nav .nav-item a:last").classList.add("active");
        }
    })();
});