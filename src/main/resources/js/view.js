/**
 *
 * */

$(document).ready(function () {

    fetch('/resume/myResume').then(result => result.json()
        .then(data => obj = data)
        .then(() => {
            document.getElementById("ageOutPut").textContent += obj.age;
            document.getElementById("firstNameOutPut").textContent += obj.first_name;
            document.getElementById("lastNameOutPut").textContent += obj.last_name;
            document.getElementById("educationOutPut").textContent += obj.education;
            document.getElementById("experienceOutPut").textContent += obj.experience;
            document.getElementById("skillsOutPut").textContent += obj.skills;
            document.getElementById("aboutOutPut").textContent += obj.about;
            document.getElementById("visibilityOutPut").textContent += obj.visibility;

        }));

    $('.viewBtnDel').on('click', function (event) {
        fetch('/resume/delete', {method: 'DELETE'})
            .then(result => console.log(result))
        fetch('/image/delete', {method: 'DELETE'})
            .then(window.location.reload());
    });

});





