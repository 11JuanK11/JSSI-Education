//--------------------------------see grades----------------------------------------------------------------

document.addEventListener('DOMContentLoaded', function() {
    const seeGradesLink = document.getElementById('see-grades-link');
    const mainContent = document.querySelector('main .welcome');

    seeGradesLink.addEventListener('click', function(event) {
        event.preventDefault();

        mainContent.innerHTML = ''; // Limpiar el contenido anterior

        fetchGrades(); // Llamar a la función para obtener calificaciones
    });

    function fetchGrades() {
        const studentId = 1; // Cambia esto según cómo obtengas el studentId

        fetch(`/grades/student/${studentId}`)
            .then(response => {
                // Manejo de respuesta vacía
                if (response.status === 204) {
                    console.log('No grades found for this student.');
                    renderGrades([]); // Llama a renderGrades con un arreglo vacío
                    return; // Salir de la función
                }

                // Asegúrate de que la respuesta es OK antes de intentar convertir a JSON
                if (!response.ok) {
                    throw new Error('Network response was not ok: ' + response.statusText);
                }

                return response.json(); // Solo llama a json() si la respuesta es válida
            })
            .then(data => {
                renderGrades(data); // Renderiza las calificaciones recibidas
            })
            .catch(error => {
                console.error('Error fetching grades:', error);
                mainContent.innerHTML = '<p>Error fetching grades. Please try again later.</p>'; // Mensaje de error
            });
    }

    function renderGrades(grades) {
        if (grades.length === 0) {
            mainContent.innerHTML = '<p>No grades available for this student.</p>';
            return;
        }

        const table = document.createElement('table');
        table.classList.add('table', 'table-striped');

        const thead = document.createElement('thead');
        thead.innerHTML = `
            <tr>
                <th>Course</th>
                <th>Grade</th>
            </tr>
        `;
        table.appendChild(thead);

        const tbody = document.createElement('tbody');
        grades.forEach(grade => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${grade.courseName}</td>
                <td>${grade.gradeValue}</td>
            `;
            tbody.appendChild(row);
        });

        table.appendChild(tbody);
        mainContent.appendChild(table); // Añadir la tabla al contenido principal
    }
});
