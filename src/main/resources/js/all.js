/**
 *
 * */

$(document).ready(function () {
    chekAuthUser().then(isAuth => {
        if (!isAuth) {
            $('.removeAddResume').remove();
            $('.removeView').remove();
            $('.removeLogOut').replaceWith(
                '<ul class="navbar-nav ml-auto">\n' +
                '            <li class="nav-item">\n' +
                '                <a class="nav-link" href="/login">log in</a>\n' +
                '            </li>\n' +
                '            <li class="nav-item">\n' +
                '                <a class="nav-link" href="/registration">sign in</a>\n' +
                '            </li>\n' +
                '        </ul>'
            )
        }
    });
    loadUserRole().then(roles => {
        const canRemoveResume = roles.includes('ROLE_ADMIN');
        loadResumeInformation(canRemoveResume)
    })
});

async function loadUserRole() {
    return new Promise((resolve) => {
        $.get('/users/get')
            .done((data) => resolve(data.roles || []))
            .fail(console.error)
    })
}

async function chekAuthUser() {
    return new Promise((resolve) => {
        $.get('/users/chek')
            .done((data) => resolve(data))
            .fail(console.error)
    })

}

function loadResumeInformation(showRemoveBtn) {

    let ResumeTableBody = $("#resume_table_body");
    ResumeTableBody.children().remove();
    fetch('resume/allPublishedResume')
        .then((response) => {
            response.json().then(data => {
                let row = '<div class="row">'
                for (const [index, item] of data.entries()) {
                    //Создание строки с данными юзера
                    row += createTableRow(item, showRemoveBtn);
                    if ((index + 1) % 2 === 0) {
                        row += '</div><div class="row">'
                    }
                }
                if (data.length % 2 !== 0) {
                    row += '<div class="col"></div>'
                }
                row += '</div>'
                ResumeTableBody.append(row);
                $('img').on('error', function (evt) {
                    evt.target.remove();
                })
                if (showRemoveBtn) {
                    // Удаление юзера и столбца таблицы
                    $('.eBtnDel').on('click', function (event) {
                        const href = '/resume/delete/' + event.target.dataset.resumeId;
                        fetch(href, {method: 'DELETE'})
                            .then(result => console.log(result))
                            .then(() => loadResumeInformation(showRemoveBtn))
                    });
                }
            });
        }).catch(error => {
        console.log(error);
    });
}

function createTableRow(r, showRemoveBtn) {
    return `<div class="position-relative overflow-hidden p-3 m-md-3 bg-light col">
                <div class="col-md-10 mx-auto my-5">
                    <h4 class="fw-normal">First name: </h4>
                    <p class="lead fw-normal" >${r.first_name}</p>
    
                    <h4 class="fw-normal">Last name: </h4>
                    <p class="lead fw-normal" >${r.last_name}</p>
    
                    <h4 class="fw-normal">Age: </h4>
                    <p class="lead fw-normal" >${r.age}</p>
    
                    <h4 class="fw-normal">Education: </h4>
                    <p class="lead fw-normal" >${r.education}</p>
    
                    <h4 class="fw-normal">Skills: </h4>
                    <p class="lead fw-normal" >${r.skills}</p>
    
                    <h4 class="fw-normal">Experience: </h4>
                    <p class="lead fw-normal" >${r.experience}</p>
    
                    <h4 class="fw-normal">About: </h4>
                    <p class="lead fw-normal" >${r.about}</p>
    
                    <h4 class="fw-normal">Visibility: </h4>
                    <p class="lead fw-normal">${r.visibility}</p>
    
                    <img src="${r.image_user_id}" class="rounded float-star mx-auto d-block img-fluid" width="200" height="200"  alt="image">
    ${showRemoveBtn ?
        `<button class="eBtnDel btn btn-danger m-3" type="button" data-resume-id="${r.id}">Удалить</button>` : ''}
                </div>
                <div class="product-device shadow-sm d-none d-md-block"></div>
                <div class="product-device product-device-2 shadow-sm d-none d-md-block"></div>
            </div>`
}



