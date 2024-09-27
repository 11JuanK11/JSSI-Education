document.addEventListener("DOMContentLoaded", function() {
    fetch("http://localhost:8080/degrees/")
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            const degreeList = document.getElementById('degree-list');
            data.forEach(degree => {
                const degreeItem = document.createElement('div');
                degreeItem.classList.add('degree-item');
                degreeItem.innerHTML = `<h2>${degree.name}</h2>`;
                degreeList.appendChild(degreeItem);
            });
        })
        .catch(error => {
            console.error('Error fetching degrees:', error);
        });
});
