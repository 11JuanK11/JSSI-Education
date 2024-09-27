document.getElementById('recoverPasswordButton').addEventListener('click', function() {
    const username = document.getElementById('username').value;
    const resultMessage = document.getElementById('resultMessage');


    fetch(`/api/recover-password?username=${username}`, {
        method: 'POST'
    })
    .then(response => response.text())
    .then(data => {
        resultMessage.innerText = data;

        if (!data.includes("User not found")) {

            document.getElementById('newPasswordContainer').style.display = 'block';
            document.getElementById('changePasswordButton').style.display = 'block';
        }
    })
    .catch(error => {
        console.error('Error:', error);
        resultMessage.innerText = "An error occurred while processing your request.";
    });
});

document.getElementById('changePasswordButton').addEventListener('click', function() {
    const username = document.getElementById('username').value;
    const newPassword = document.getElementById('newPassword').value;
    const resultMessage = document.getElementById('resultMessage');


    fetch(`/api/change-password?username=${username}&newPassword=${newPassword}`, {
        method: 'POST'
    })
    .then(response => response.text())
    .then(data => {
        resultMessage.innerText = data;


        document.getElementById('username').value = '';
        document.getElementById('newPassword').value = '';
    })
    .catch(error => {
        console.error('Error:', error);
        resultMessage.innerText = "An error occurred while processing your request.";
    });
});
