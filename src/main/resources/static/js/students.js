window.addEventListener("load", function () {
    const createBtn = document.getElementById('createBtn');
    const searchBtn = document.getElementById('searchBtn');
    const updateBtn = document.getElementById('updateBtn');
    const deleteBtn = document.getElementById('deleteBtn');
    const formContainer = document.getElementById('formContainer');

    function renderForm(htmlContent) {
        formContainer.innerHTML = htmlContent;
    }

      function loadDegrees() {
            return fetch('/degrees/')
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Error fetching degrees');
                    }
                    return response.json();
                });
        }

// ---------------------------------------------- create ----------------------------------------------

createBtn.addEventListener('click', function () {
    renderForm(`
    <div class="form">
        <h2><strong>Add Student</strong></h2>
        <form id="studentForm">
            <div class="mb-3">
                <select id="idType" class="form-select" required>
                    <option value="" disabled selected>ID Type</option>
                    <option>National ID Card</option>
                    <option>Foreign ID Card</option>
                    <option>Passport</option>
                </select>
            </div>
            <div class="mb-3">
                <input type="text" class="form-control" id="idInput" placeholder="ID" required>
            </div>
            <div class="mb-3">
                <input type="text" class="form-control" id="nameInput" placeholder="Name" required>
            </div>
            <div class="mb-3">
                <input type="text" class="form-control" id="lastnameInput" placeholder="Lastname" required>
            </div>
            <div class="mb-3">
                <input type="text" class="form-control" id="userInput" placeholder="User" required>
            </div>
            <div class="mb-3">
                <input type="password" class="form-control" id="passwordInput" placeholder="Password" required>
            </div>
            <div class="mb-3">
                <input type="email" class="form-control" id="emailInput" placeholder="Email address" required>
            </div>
            <div class="mb-3">
                <select id="degreeSelect" class="form-select" required>
                    <option value="" disabled selected>Select Degree</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Add</button>
        </form>
    </div>
    `);

    fetch('/degrees/')
        .then(response => response.json())
        .then(data => {
            const degreeSelect = document.querySelector('#degreeSelect');
            data.forEach(degree => {
                const option = document.createElement('option');
                option.value = degree.id;
                option.text = degree.name;
                degreeSelect.appendChild(option);
            });
        })
        .catch(error => console.error('Error loading degrees:', error));

    document.getElementById("studentForm").onsubmit = function (e) {
        e.preventDefault();

        const studentData = {
            user: {
                idNumber: document.querySelector('#idInput').value,
                name: document.querySelector('#nameInput').value,
                lastname: document.querySelector('#lastnameInput').value,
                userName: document.querySelector('#userInput').value,
                password: document.querySelector('#passwordInput').value,
                email: document.querySelector('#emailInput').value
            },
            degree: {
                id: document.querySelector('#degreeSelect').value
            }
        };

        console.log(JSON.stringify(studentData));

        const url = '/students/';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(studentData)
        };

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                alert('Student added successfully');
                document.getElementById('studentForm').reset();
            })
            .catch(error => {
                alert('Error adding student: ' + error);
                console.error(error);
            });
    };
});

// ---------------------------------------------- findOne ----------------------------------------------
searchBtn.addEventListener('click', function () {
    renderForm(`
        <div class="form">
            <h2><strong>Search Student by ID number</strong></h2>
            <form id="searchStudentForm">
                <div class="mb-3">
                    <input type="text" class="form-control" id="idInput" placeholder="Enter ID number" required>
                </div>
                <button type="submit" class="btn btn-primary">Search</button>
            </form>
        </div>
    `);

    document.getElementById("searchStudentForm").onsubmit = function (e) {
        e.preventDefault();
        const idNumber = document.querySelector('#idInput').value;

        const url = `/students/${idNumber}`;
        fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Student not found');
                }
                return response.json();
            })
            .then(student => {

                renderForm(`
                    <div class="form">
                        <h2><strong>Student Details</strong></h2>
                        <p><strong>Name:</strong> ${student.user.name}</p>
                        <p><strong>Lastname:</strong> ${student.user.lastname}</p>
                        <p><strong>Email:</strong> ${student.user.email}</p>
                        <p><strong>Username:</strong> ${student.user.userName}</p>
                        <p><strong>ID Number:</strong> ${student.user.idNumber}</p>
                        <p><strong>Degree:</strong> ${student.degree ? student.degree.name : 'No degree assigned'}</p>
                    </div>
                `);
            })
            .catch(error => {
                alert(error.message);
                console.error(error);
            });
    };
});


    // Borrar estudiante
    deleteBtn.addEventListener('click', function () {
        renderForm(`
            <div class="form">
                <h2><strong>Delete Student</strong></h2>
                <form id="deleteStudentForm">
                    <div class="mb-3">
                        <input type="text" class="form-control" id="dniInput" placeholder="Enter ID number" required>
                    </div>
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
            </div>
        `);

        // Manejar el envío del formulario de eliminación
        document.getElementById('deleteStudentForm').addEventListener('submit', function (e) {
            e.preventDefault();
            const dni = document.getElementById('dniInput').value;

            // Realizar la eliminación del estudiante
            fetch(`/api/students/${dni}`, {
                method: 'DELETE',
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error deleting student');
                }
                alert('Student deleted successfully!');
                renderForm(''); // Limpiar el formulario o redirigir a la lista de estudiantes
            })
            .catch((error) => {
                console.error('Error:', error);
                alert('Error deleting student.');
            });
        });
    });

    // Actualizar estudiante
    updateBtn.addEventListener('click', function () {
        renderForm(`
            <div class="form">
                <h2><strong>Update Student</strong></h2>
                <form id="searchForm">
                    <div class="mb-3">
                        <input type="text" class="form-control" id="dniInput" placeholder="Enter ID number" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Search</button>
                </form>
            </div>
        `);


        document.getElementById('searchForm').addEventListener('submit', function (e) {
            e.preventDefault();
            const dni = document.getElementById('dniInput').value;


            fetch(`/api/students/${dni}`)
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Student not found');
                    }
                    return response.json();
                })
                .then(data => {

                    renderForm(`
                        <div class="form">
                            <h2><strong>Update Student</strong></h2>
                            <form id="updateStudentForm">
                                <input type="hidden" id="idInput" value="${data.id}">
                                <div class="mb-3">
                                    <select id="idType" class="form-select" required>
                                        <option value="" disabled>Selected: ${data.idType}</option>
                                        <option value="National ID Card">National ID Card</option>
                                        <option value="Foreign ID Card">Foreign ID Card</option>
                                        <option value="Passport">Passport</option>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <input type="text" class="form-control" id="nameInput" value="${data.name}" required>
                                </div>
                                <div class="mb-3">
                                    <input type="text" class="form-control" id="userInput" value="${data.user}" required>
                                </div>
                                <div class="mb-3">
                                    <input type="password" class="form-control" id="passwordInput" placeholder="Password" required>
                                </div>
                                <div class="mb-3">
                                    <input type="email" class="form-control" id="emailInput" value="${data.email}" required>
                                </div>
                                <button type="submit" class="btn btn-primary">Update</button>
                            </form>
                        </div>
                    `);


                    document.getElementById('updateStudentForm').addEventListener('submit', function (e) {
                        e.preventDefault();

                        const id = document.getElementById('idInput').value;
                        const idType = document.getElementById('idType').value;
                        const name = document.getElementById('nameInput').value;
                        const user = document.getElementById('userInput').value;
                        const password = document.getElementById('passwordInput').value;
                        const email = document.getElementById('emailInput').value;

                        const studentData = {
                            idType,
                            id,
                            name,
                            user,
                            password,
                            email
                        };


                        fetch(`/api/students/${id}`, {
                            method: 'PUT',
                            headers: {
                                'Content-Type': 'application/json',
                            },
                            body: JSON.stringify(studentData),
                        })
                        .then(response => {
                            if (!response.ok) {
                                throw new Error('Error updating student');
                            }
                            return response.json();
                        })
                        .then(data => {
                            alert('Student updated successfully!');
                            renderForm('');
                        })
                        .catch((error) => {
                            console.error('Error:', error);
                            alert('Error updating student.');
                        });
                    });
                })
                .catch((error) => {
                    console.error('Error:', error);
                    alert('Student not found.');
                });
        });
    });
});
