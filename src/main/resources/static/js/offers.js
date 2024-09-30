window.addEventListener("load", function () {
    const createBtn = document.getElementById('createBtn');
    const updateBtn = document.getElementById('updateBtn');
    const deleteBtn = document.getElementById('deleteBtn');
    const findAllBtn = document.getElementById('findAllBtn');
    const formContainer = document.getElementById('formContainer');

    function renderForm(htmlContent) {
        formContainer.innerHTML = htmlContent;
    }


    findAllBtn.addEventListener('click', function () {
        const url = `/groups/`;
        fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(groups => {
                renderOfferList(groups);
            })
            .catch(error => {
                alert('Error fetching offers: ' + error.message);
                console.error(error);
            });
    });

    function renderOfferList(groups) {
        const formContainer = document.getElementById('formContainer');
        formContainer.innerHTML = `<h2><strong>List of Offers</strong></h2>`;

        if (groups.length === 0) {
            formContainer.innerHTML += '<p>No offers found.</p>';
            return;
        }

        const table = `
            <table class="table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Group</th>
                        <th>Name Teacher</th>
                        <th>Lastname Teacher</th>
                        <th>Number Students</th>
                        <th>Classroom</th>
                        <th>Start Time</th>
                        <th>End Time</th>
                        <th>Day</th>
                    </tr>
                </thead>
                <tbody>
                    ${groups.map(group => `
                        <tr>
                            <td>${group.offerDayWeek.id}</td>
                            <td>${group.groupId}</td>
                            <td>${group.teacher.user.name}</td>
                            <td>${group.teacher.user.lastname}</td>
                            <td>${group.numberStudents}</td>
                            <td>${group.classroom}</td>
                            <td>${group.offerDayWeek.offer.startTime}</td>
                            <td>${group.offerDayWeek.offer.endTime}</td>
                            <td>${group.offerDayWeek.dayWeek.day}</td>
                        </tr>
                    `).join('')}
                </tbody>
            </table>
        `;

        formContainer.innerHTML += table;
    }


    createBtn.addEventListener('click', function () {
            renderForm(`
            <div class="form">
                <h2><strong>Add Offer</strong></h2>
                <form id="offerForm">
                    <div class="mb-3">
                        <input type="text" class="form-control" id="startTime" placeholder="Start Time" required>
                    </div>
                    <div class="mb-3">
                        <input type="text" class="form-control" id="endTime" placeholder="End Time" required>
                    </div>
                    <div class="mb-3">
                        <input type="text" class="form-control" id="idDay" placeholder="Id Day" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Add</button>
                </form>
            </div>
            `);

            document.getElementById("offerForm").onsubmit = function (e) {
                e.preventDefault();

                const offerData = {
                    offer: {
                        startTime: document.querySelector('#startTime').value,
                        endTime: document.querySelector('#endTime').value
                    },
                    dayWeek: {
                        id: document.querySelector('#idDay').value
                    }
                };

                console.log(JSON.stringify(offerData));

                const url = '/offers/dayWeek/';
                const settings = {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(offerData)
                };

                fetch(url, settings)
                    .then(response => response.json())
                    .then(data => {
                        alert('Offer added successfully');
                        document.getElementById('offerForm').reset();
                    })
                    .catch(error => {
                        alert('Error adding offer: ' + error);
                        console.error(error);
                    });
            };
        });


    updateBtn.addEventListener('click', function () {
        renderForm(`
            <div class="form">
                <h2><strong>Update Teacher</strong></h2>
                <form id="searchOfferForm">
                    <div class="mb-3">
                        <input type="text" class="form-control" id="idInput" placeholder="Enter ID" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Search</button>
                </form>
            </div>
        `);

        document.getElementById("searchOfferForm").onsubmit = function (e) {
            e.preventDefault();
            const id = document.querySelector('#idInput').value;

            const url = `/offers/dayWeek/${id}`;
            fetch(url)
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Manager not found');
                    }
                    return response.json();
                })
                .then(offerDayWeek => {
                    renderForm(`
                        <div class="form">
                            <h2><strong>Update Offer</strong></h2>
                            <form id="updateOfferForm">
                                <div class="mb-3">
                                    <input type="text" class="form-control" id="startTime" value="${offerDayWeek.offer.startTime}" required>
                                </div>
                                <div class="mb-3">
                                    <input type="text" class="form-control" id="endTime" value="${offerDayWeek.offer.endTime}" required>
                                </div>
                                <div class="mb-3">
                                    <input type="text" class="form-control" id="idDay" value="${offerDayWeek.dayWeek.id}" required>
                                </div>
                                <button type="submit" class="btn btn-primary">Update</button>
                            </form>
                        </div>
                    `);

                    document.getElementById("updateOfferForm").onsubmit = function (e) {
                        e.preventDefault();

                        const updatedData = {
                            offer: {
                                id: offerDayWeek.offer.id,
                                startTime: document.querySelector('#startTime').value,
                                endTime: document.querySelector('#endTime').value
                            },
                            dayWeek: {
                                id: document.querySelector('#idDay').value
                            }
                        };

                        const updateUrl = `/offers/dayWeek/${id}`;
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
                                    throw new Error('Error updating offer');
                                }
                                return response.json();
                            })
                            .then(data => {
                                alert('Offer updated successfully');
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


    deleteBtn.addEventListener('click', function () {
        renderForm(`
            <div class="form">
                <h2><strong>Delete Offer</strong></h2>
                <form id="deleteOfferForm">
                    <div class="mb-3">
                        <input type="text" class="form-control" id="idInput" placeholder="Enter ID" required>
                    </div>
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
            </div>
        `);

        document.getElementById("deleteOfferForm").onsubmit = function (e) {
            e.preventDefault();
            const id = document.querySelector('#idInput').value;

            const url = `/offers/dayWeek/deletes/${id}`;
            const settings = {
                method: 'DELETE'
            };

            fetch(url, settings)
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Error deleting offer');
                    }
                    alert('Offer deleted successfully');
                })
                .catch(error => {
                    alert(error.message);
                    console.error(error);
                });
        };
    });

});