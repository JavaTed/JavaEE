function check(name)
{
    var inp = document.getElementsByName(name);
    for (var i = 0; i < inp.length; i++) {
        if (inp[i].type == "radio" && inp[i].checked) {
            return true;
        }
    }
    return false;
}

function checkAll() {
    if (check('question1') &&
        check('question2') &&
        check('question3') &&
        document.getElementById('fname').value !="" &&
        document.getElementById('fsurname').value !="" &&
        document.getElementById('fage').value !="")
        return true;
    else {
        alert("All questions should be answered!");
        return false;
        }
}