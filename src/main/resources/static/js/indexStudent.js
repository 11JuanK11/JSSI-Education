document.addEventListener('DOMContentLoaded', function() {
    const seeGradesLink = document.getElementById('see-grades-link');
    const mainContent = document.querySelector('main .welcome');

    seeGradesLink.addEventListener('click', function(event) {
        event.preventDefault();

        mainContent.innerHTML = '';

        fetchGrades();
    });

    function fetchGrades() {
        const studentId = 1;

        fetch(`/grades/student/${studentId}`)
            .then(response => {
                if (response.status === 204) {
                    console.log('No grades found for this student.');
                    renderGrades([]);
                    return;
                }

                if (!response.ok) {
                    throw new Error('Network response was not ok: ' + response.statusText);
                }

                return response.json();
            })
            .then(data => {
                renderGrades(data);
            })
            .catch(error => {
                console.error('Error fetching grades:', error);
                mainContent.innerHTML = '<p>Error fetching grades. Please try again later.</p>';
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
                <td>${grade.groupHasCourse.course.courseName}</td>
                <td>${(grade.testOne + grade.testTwo + grade.followUp) / 3} </td>
            `;
            tbody.appendChild(row);
        });

        table.appendChild(tbody);
        mainContent.appendChild(table);
    }
});
