window.onload = function() {
    let i = 0;
    const imgs = document.querySelectorAll('.banner-img');

    function cambiarImagenes() {
        imgs[i].classList.remove('active');
        i = (i + 1) % imgs.length;
        imgs[i].classList.add('active');
    }

    setInterval(cambiarImagenes, 5000);
};

   document.getElementById('recoverPasswordButton').addEventListener('click', function() {
            const username = document.getElementById('username').value;
            const resultMessage = document.getElementById('resultMessage');

            // Realizar la solicitud POST a la API
            fetch(`/api/recover-password?username=${username}`, {
                method: 'POST'
            })
            .then(response => response.text())
            .then(data => {
                resultMessage.innerText = data; // Muestra el mensaje devuelto
            })
            .catch(error => {
                console.error('Error:', error);
                resultMessage.innerText = "An error occurred while processing your request.";
            });
        });

