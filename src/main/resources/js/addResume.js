/**
 *
 * */

$(document).ready(function () {

    $('.SaveResume').on('click', function (event) {
        event.preventDefault();

        const formData = new FormData();
        const photos = document.getElementById("exampleFormControlFile1");

        formData.append("multipartImage", photos.files[0])


        fetch('/image/save', {
            method: 'POST',
            body: formData
        });


        let resume = {
            age: $('#age').val(),
            first_name: $('#first_name').val(),
            last_name: $('#last_name').val(),
            education: $('#education').val(),
            experience: $('#experience').val(),
            skills: $('#skills').val(),
            about: $('#about').val(),
            visibility: validate()
        };

        function validate() {
            if (document.getElementById('flexCheckDefault').checked) {
                return true;
            } else {
                return false;
            }
        }

        fetch('/resume', {

            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify(resume),

        }).then(() => {
            window.location.replace('/all_resume')
        });


    });


    fetch('/resume/myResume').then(result => result.json()
        .then(data => obj = data)
        .then(() => {

            document.getElementById("age").value = obj.age;
            document.getElementById("first_name").value = obj.first_name;
            document.getElementById("last_name").value = obj.last_name;
            document.getElementById("education").value = obj.education;
            document.getElementById("experience").value = obj.experience;
            document.getElementById("skills").value = obj.skills;
            document.getElementById("about").value = obj.about;

            if (obj === null) {
                console.log("false")
            }
        }));


});





