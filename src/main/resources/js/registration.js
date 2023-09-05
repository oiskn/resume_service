/**
 *
 * */

$(document).ready(function () {
    //add new user
    $('.AddBtn').on('click', function (event) {
        event.preventDefault();
        let user = {
            name: $('#name').val(),
            login: $('#login').val(),
            password: $('#password').val(),
            roles: ["ROLE_USER"]
        };
        console.log(user);
        fetch('/users/save', {

            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify(user),
        }).then(() => {
            window.location.replace('/all_resume')
        });
        $('input').val('');
    });
});





