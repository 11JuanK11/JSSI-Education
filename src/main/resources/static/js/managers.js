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

// findAll
findAllBtn.addEventListener('click', function () {
    const url = '/managers/';
    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(managers => {
            renderManagerList(managers);
        })
        .catch(error => {
            alert('Error fetching managers: ' + error.message);
            console.error(error);
        });
});

function renderManagerList(managers) {
    const formContainer = document.getElementById('formContainer');
    formContainer.innerHTML = `<h2><strong>List of Managers</strong></h2>`;

    if (managers.length === 0) {
        formContainer.innerHTML += '<p>No managers found.</p>';
        return;
    }

    const table = `
        <table class="table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>ID Number</th>
                    <th>Name</th>
                    <th>Lastname</th>
                    <th>Username</th>
                    <th>Email</th>
                    <th>Password</th>
                </tr>
            </thead>
            <tbody>
                ${managers.map(manager => `
                    <tr>
                        <td>${manager.id}</td>
                        <td>${manager.user.idNumber}</td>
                        <td>${manager.user.name}</td>
                        <td>${manager.user.lastname}</td>
                        <td>${manager.user.userName}</td>
                        <td>${manager.user.email}</td>
                        <td>${manager.user.password}</td>
                    </tr>
                `).join('')}
            </tbody>
        </table>
    `;

    formContainer.innerHTML += table;
}


// create
createBtn.addEventListener('click', function () {
    renderForm(`
    <div class="form">
        <h2><strong>Add Manager</strong></h2>
        <form id="managerForm">
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
            <button type="submit" class="btn btn-primary">Add</button>
        </form>
    </div>
    `);

    document.getElementById("managerForm").onsubmit = function (e) {
        e.preventDefault();

        const managerData = {
            user: {
                idNumber: document.querySelector('#idInput').value,
                name: document.querySelector('#nameInput').value,
                lastname: document.querySelector('#lastnameInput').value,
                userName: document.querySelector('#userInput').value,
                password: document.querySelector('#passwordInput').value,
                email: document.querySelector('#emailInput').value
            }
        };

        console.log(JSON.stringify(managerData));

        const url = '/managers/';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(managerData)
        };

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                alert('Manager added successfully');
                document.getElementById('managerForm').reset();
            })
            .catch(error => {
                alert('Error adding manager' + error);
                console.error(error);
            });
    };
});

// findOne
searchBtn.addEventListener('click', function () {
    renderForm(`
        <div class="form">
            <h2><strong>Search Manager by ID number</strong></h2>
            <form id="searchManagerForm">
                <div class="mb-3">
                    <input type="text" class="form-control" id="idInput" placeholder="Enter ID number" required>
                </div>
                <button type="submit" class="btn btn-primary">Search</button>
            </form>
        </div>
    `);

    document.getElementById("searchManagerForm").onsubmit = function (e) {
        e.preventDefault();
        const idNumber = document.querySelector('#idInput').value;

        const url = `/managers/${idNumber}`;
        fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Manager not found' + response.ok);
                }
                return response.json();
            })
            .then(manager => {
                renderForm(`
                    <div class="form">
                        <h2><strong>Manager Details</strong></h2>
                        <p><strong>ID Number:</strong> ${manager.user.idNumber}</p>
                        <p><strong>Name:</strong> ${manager.user.name}</p>
                        <p><strong>Lastname:</strong> ${manager.user.lastname}</p>
                        <p><strong>Username:</strong> ${manager.user.userName}</p>
                        <p><strong>Email:</strong> ${manager.user.email}</p>
                    </div>
                `);
            })
            .catch(error => {
                alert(error.message);
                console.error(error);
            });
    };
});

// Update
updateBtn.addEventListener('click', function () {
    renderForm(`
        <div class="form">
            <h2><strong>Update Teacher</strong></h2>
            <form id="searchManagerForm">
                <div class="mb-3">
                    <input type="text" class="form-control" id="idInput" placeholder="Enter ID number" required>
                </div>
                <button type="submit" class="btn btn-primary">Search</button>
            </form>
        </div>
    `);

    document.getElementById("searchManagerForm").onsubmit = function (e) {
        e.preventDefault();
        const idNumber = document.querySelector('#idInput').value;

        const url = `/managers/${idNumber}`;
        fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Manager not found');
                }
                return response.json();
            })
            .then(manager => {
                renderForm(`
                    <div class="form">
                        <h2><strong>Update Manager</strong></h2>
                        <form id="updateManagerForm">
                            <div class="mb-3">
                                <input type="text" class="form-control" id="idNumberInput" value="${manager.user.idNumber}" placeholder="Enter ID number" required>
                            </div>
                            <div class="mb-3">
                                <input type="text" class="form-control" id="nameInput" value="${manager.user.name}" required>
                            </div>
                            <div class="mb-3">
                                <input type="text" class="form-control" id="lastnameInput" value="${manager.user.lastname}" required>
                            </div>
                            <div class="mb-3">
                                <input type="email" class="form-control" id="emailInput" value="${manager.user.email}" required>
                            </div>
                            <div class="mb-3">
                                <input type="text" class="form-control" id="usernameInput" value="${manager.user.userName}" placeholder="Enter username" required>
                            </div>
                            <div class="mb-3">
                                <input type="password" class="form-control" id="passwordInput" placeholder="Enter new password (leave empty if not changing)">
                            </div>
                            <button type="submit" class="btn btn-primary">Update</button>
                        </form>
                    </div>
                `);

                document.getElementById("updateManagerForm").onsubmit = function (e) {
                    e.preventDefault();

                    const updatedData = {
                        user: {
                            name: document.querySelector('#nameInput').value,
                            lastname: document.querySelector('#lastnameInput').value,
                            email: document.querySelector('#emailInput').value,
                            userName: document.querySelector('#usernameInput').value,
                            idNumber: document.querySelector('#idNumberInput').value,
                            password: document.querySelector('#passwordInput').value
                        }
                    };

                    if (!updatedData.user.password) {
                        delete updatedData.user.password;
                    }

                    const updateUrl = `/managers/${idNumber}`;
                    const settings = {
                        method: 'PUT',
                        headers: {
                            'Content-Type': 'application/json',
                        },
                        body: JSON.stringify(updatedData)
                    };

                    fetch(updateUrl, settings)
                        .then(response => {
                            if (!response.ok) {
                                throw new Error('Error updating manager');
                            }
                            return response.json();
                        })
                        .then(data => {
                            alert('Manager updated successfully');
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