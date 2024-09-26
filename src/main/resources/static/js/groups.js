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
    const url = '/groups/';
    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(groups => {
            renderTeacherList(groups);
        })
        .catch(error => {
            alert('Error fetching groups: ' + error.message);
            console.error(error);
        });
});

function renderGroupList(groups) {
    const formContainer = document.getElementById('formContainer');
    formContainer.innerHTML = `<h2><strong>List of Teachers</strong></h2>`;

    if (teachers.length === 0) {
        formContainer.innerHTML += '<p>No Groups found.</p>';
        return;
    }

    const table = `
        <table class="table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Number Students</th>
                    <th>Classroom</th>

                </tr>
            </thead>
            <tbody>
                ${groups.map(groups => `
                    <tr>
                        <td>${groups.groupId}</td>
                        <td>${groups.classroom}</td>
                        <td>${groups.numberStudents}</td>
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
        <h2><strong>Add Groups</strong></h2>
        <form id="groupForm">
            <div class="mb-3">
                <input type="text" class="form-control" id="idInput" placeholder="ID" required>
            </div>
            <div class="mb-3">
                <input type="text" class="form-control" id="classroomInput" placeholder="Classroom" required>
            </div>
            <div class="mb-3">
                <input type="text" class="form-control" id="numberStudentsInput" placeholder="Number of students" required>
            </div>
            <button type="submit" class="btn btn-primary">Add</button>
        </form>
    </div>
    `);

    document.getElementById("groupForm").onsubmit = function (e) {
        e.preventDefault();

        const groupData = {
                idNumber: document.querySelector('#idInput').value,
                name: document.querySelector('#classroomInput').value,
                lastname: document.querySelector('#numberStudentsInput').value
        };


        const url = '/groups/';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(groupData)
        };

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                alert('Group added successfully');
                document.getElementById('groupForm').reset();
            })
            .catch(error => {
                alert('Error adding group');
                console.error(error);
            });
    };
});

// ---------------------------------------------- findOne ----------------------------------------------
searchBtn.addEventListener('click', function () {
    renderForm(`
        <div class="form">
            <h2><strong>Search Group by ID</strong></h2>
            <form id="searchGroupForm">
                <div class="mb-3">
                    <input type="text" class="form-control" id="idInput" placeholder="Enter ID" required>
                </div>
                <button type="submit" class="btn btn-primary">Search</button>
            </form>
        </div>
    `);

    document.getElementById("searchGroupForm").onsubmit = function (e) {
        e.preventDefault();
        const groupId = document.querySelector('#idInput').value;

        const url = `/groups/${groupId}`;
        fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Group not found');
                }
                return response.json();
            })
            .then(group => {
                renderForm(`
                    <div class="form">
                        <h2><strong>Group Details</strong></h2>
                        <p><strong>Name:</strong> ${group.numberStudents}</p>
                        <p><strong>Lastname:</strong> ${group.classroom}</p>
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
            <h2><strong>Delete Group</strong></h2>
            <form id="deleteGroupForm">
                <div class="mb-3">
                    <input type="text" class="form-control" id="idInput" placeholder="Enter ID" required>
                </div>
                <button type="submit" class="btn btn-danger">Delete</button>
            </form>
        </div>
    `);

    document.getElementById("deleteGroupForm").onsubmit = function (e) {
        e.preventDefault();
        const groupId = document.querySelector('#idInput').value;

        const url = `/groups/${groupId}`;
        const settings = {
            method: 'DELETE'
        };

        fetch(url, settings)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error deleting Group');
                }
                alert('Group deleted successfully');
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
            <h2><strong>Update Group</strong></h2>
            <form id="searchGroupForm">
                <div class="mb-3">
                    <input type="text" class="form-control" id="idInput" placeholder="Enter ID" required>
                </div>
                <button type="submit" class="btn btn-primary">Search</button>
            </form>
        </div>
    `);

    document.getElementById("searchGroupForm").onsubmit = function (e) {
        e.preventDefault();
        const groupId = document.querySelector('#idInput').value;

        const url = `/groups/${groupId}`;
        fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Group not found');
                }
                return response.json();
            })
            .then(group => {
                renderForm(`
                    <div class="form">
                        <h2><strong>Update Group</strong></h2>
                        <form id="updateGroupForm">
                            <div class="mb-3">
                                <input type="text" class="form-control" id="classroomInput" value="${group.classroom}" required>
                            </div>
                            <div class="mb-3">
                                <input type="text" class="form-control" id="numberStudentInput" value="${group.numberStudents}" required>
                            </div>
                            <button type="submit" class="btn btn-primary">Update</button>
                        </form>
                    </div>
                `);

                document.getElementById("updateGroupForm").onsubmit = function (e) {
                    e.preventDefault();

                    const updatedData = {
                        name: document.querySelector('#classroomInput').value,
                        lastname: document.querySelector('#numberStudentInput').value,
                    };

                    const updateUrl = `/groups/${idNumber}`;
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
                                throw new Error('Error updating group');
                            }
                            return response.json();
                        })
                        .then(data => {
                            alert('Group updated successfully');
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