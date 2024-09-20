window.addEventListener("load", function () {
    const createBtn = document.getElementById('createBtn');
    const searchBtn = document.getElementById('searchBtn');
    const updateBtn = document.getElementById('updateBtn');
    const deleteBtn = document.getElementById('deleteBtn');
    const findAllBtn = document.getElementById('findAllBtn');
    const formContainer = document.getElementById('formContainer');

    function renderForm(htmlContent) {
        formContainer.innerHTML = htmlContent;
    }

// ---------------------------------------------- findAll -------------------------------------------------
    findAllBtn.addEventListener('click', function () {
        const url = '/teachers/'; // Asegúrate de que este es el endpoint correcto
        fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(teachers => {
                renderTeacherList(teachers);
            })
            .catch(error => {
                alert('Error fetching teachers: ' + error.message);
                console.error(error);
            });
    });

    function renderTeacherList(teachers) {
        const formContainer = document.getElementById('formContainer');
        formContainer.innerHTML = `<h2><strong>List of Teachers</strong></h2>`;

        if (teachers.length === 0) {
            formContainer.innerHTML += '<p>No teachers found.</p>';
            return;
        }

        const table = `
            <table class="table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>ID Number</th> <!-- Agregando columna para el ID Number -->
                        <th>Name</th>
                        <th>Lastname</th>
                        <th>Username</th> <!-- Agregando columna para el Username -->
                        <th>Email</th>
                        <th>Specialization</th>
                    </tr>
                </thead>
                <tbody>
                    ${teachers.map(teacher => `
                        <tr>
                            <td>${teacher.id}</td>
                            <td>${teacher.user.idNumber}</td> <!-- Mostrar ID Number -->
                            <td>${teacher.user.name}</td>
                            <td>${teacher.user.lastname}</td>
                            <td>${teacher.user.userName}</td> <!-- Mostrar Username -->
                            <td>${teacher.user.email}</td>
                            <td>${teacher.specialization}</td>
                        </tr>
                    `).join('')}
                </tbody>
            </table>
        `;

        formContainer.innerHTML += table;
    }



// ---------------------------------------------- create ----------------------------------------------
createBtn.addEventListener('click', function () {
    renderForm(`
    <div class="form">
        <h2><strong>Add Teacher</strong></h2>
        <form id="teacherForm">
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
                <input type="text" class="form-control" id="specializationInput" placeholder="Specialization" required>
            </div>
            <button type="submit" class="btn btn-primary">Add</button>
        </form>
    </div>
    `);

    document.getElementById("teacherForm").onsubmit = function (e) {
        e.preventDefault();

        const teacherData = {
            specialization: document.querySelector('#specializationInput').value,
            user: {
                idNumber: document.querySelector('#idInput').value,
                name: document.querySelector('#nameInput').value,
                lastname: document.querySelector('#lastnameInput').value,
                userName: document.querySelector('#userInput').value,
                password: document.querySelector('#passwordInput').value,
                email: document.querySelector('#emailInput').value
            }
        };


        const url = '/teachers/';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(teacherData)
        };

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                alert('Teacher added successfully');
                document.getElementById('teacherForm').reset();
            })
            .catch(error => {
                alert('Error adding teacher');
                console.error(error);
            });
    };
});

// ---------------------------------------------- findOne ----------------------------------------------
searchBtn.addEventListener('click', function () {
    renderForm(`
        <div class="form">
            <h2><strong>Search Teacher by ID number</strong></h2>
            <form id="searchTeacherForm">
                <div class="mb-3">
                    <input type="text" class="form-control" id="idInput" placeholder="Enter ID number" required>
                </div>
                <button type="submit" class="btn btn-primary">Search</button>
            </form>
        </div>
    `);

    document.getElementById("searchTeacherForm").onsubmit = function (e) {
        e.preventDefault();
        const id = document.querySelector('#idInput').value;

        const url = `/teachers/${id}`;
        fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Teacher not found');
                }
                return response.json();
            })
            .then(teacher => {
                renderForm(`
                    <div class="form">
                        <h2><strong>Teacher Details</strong></h2>
                        <p><strong>Name:</strong> ${teacher.user.name}</p>
                        <p><strong>Lastname:</strong> ${teacher.user.lastname}</p>
                        <p><strong>Email:</strong> ${teacher.user.email}</p>
                        <p><strong>Username:</strong> ${teacher.user.userName}</p>
                        <p><strong>ID Number:</strong> ${teacher.user.idNumber}</p>
                        <p><strong>Specialization:</strong> ${teacher.specialization}</p>
                    </div>
                `);
            })
            .catch(error => {
                alert(error.message);
                console.error(error);
            });
    };
});

// ---------------------------------------------- Delete ----------------------------------------------
deleteBtn.addEventListener('click', function () {
    renderForm(`
        <div class="form">
            <h2><strong>Delete Teacher</strong></h2>
            <form id="deleteTeacherForm">
                <div class="mb-3">
                    <input type="text" class="form-control" id="idInput" placeholder="Enter ID number" required>
                </div>
                <button type="submit" class="btn btn-danger">Delete</button>
            </form>
        </div>
    `);

    document.getElementById("deleteTeacherForm").onsubmit = function (e) {
        e.preventDefault();
        const id = document.querySelector('#idInput').value;

        const url = `/teachers/${id}`;
        const settings = {
            method: 'DELETE'
        };

        fetch(url, settings)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error deleting teacher');
                }
                alert('Teacher deleted successfully');
            })
            .catch(error => {
                alert(error.message);
                console.error(error);
            });
    };
});


// ---------------------------------------------- Update ----------------------------------------------
updateBtn.addEventListener('click', function () {
    renderForm(`
        <div class="form">
            <h2><strong>Update Teacher</strong></h2>
            <form id="searchTeacherForm">
                <div class="mb-3">
                    <input type="text" class="form-control" id="idInput" placeholder="Enter ID number" required>
                </div>
                <button type="submit" class="btn btn-primary">Search</button>
            </form>
        </div>
    `);

    document.getElementById("searchTeacherForm").onsubmit = function (e) {
        e.preventDefault();
        const id = document.querySelector('#idInput').value;

        const url = `/teachers/${id}`;
        fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Teacher not found');
                }
                return response.json();
            })
            .then(teacher => {
                renderForm(`
                    <div class="form">
                        <h2><strong>Update Teacher</strong></h2>
                        <form id="updateTeacherForm">
                            <div class="mb-3">
                                <input type="text" class="form-control" id="nameInput" value="${teacher.user.name}" required>
                            </div>
                            <div class="mb-3">
                                <input type="text" class="form-control" id="lastnameInput" value="${teacher.user.lastname}" required>
                            </div>
                            <div class="mb-3">
                                <input type="email" class="form-control" id="emailInput" value="${teacher.user.email}" required>
                            </div>
                            <div class="mb-3">
                                <input type="text" class="form-control" id="specializationInput" value="${teacher.specialization}" required>
                            </div>
                            <button type="submit" class="btn btn-primary">Update</button>
                        </form>
                    </div>
                `);

                document.getElementById("updateTeacherForm").onsubmit = function (e) {
                    e.preventDefault();

                    const updatedData = {
                        user: {
                            name: document.querySelector('#nameInput').value,
                            lastname: document.querySelector('#lastnameInput').value,
                            email: document.querySelector('#emailInput').value
                        },
                        specialization: document.querySelector('#specializationInput').value
                    };

                    const url = `/teachers/${id}`;
                    const settings = {
                        method: 'PUT',
                        headers: {
                            'Content-Type': 'application/json',
                        },
                        body: JSON.stringify(updatedData)
                    };

                    fetch(url, settings)
                        .then(response => {
                            if (!response.ok) {
                                throw new Error('Error updating teacher');
                            }
                            return response.json();
                        })
                        .then(data => {
                            alert('Teacher updated successfully');
                        })
                        .catch(error => {
                            alert(error.message);
                            console.error(error);
                        });
                };
            })
            .catch(error => {
                alert(error.message);
                console.error(error);
            });
    };
});


});
