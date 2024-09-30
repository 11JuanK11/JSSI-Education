window.addEventListener("load", function () {
    const createBtn = document.getElementById('createBtn');
    const updateBtn = document.getElementById('updateBtn');
    const deleteBtn = document.getElementById('deleteBtn');
    const findAllBtn = document.getElementById('findAllBtn');
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

    function loadDegreesCourses() {
            return fetch('/degree-courses/')
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Error fetching degrees');
                    }
                    return response.json();
                });
        }

    function loadSemester() {
        return fetch('/semesters/')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error fetching semester');
                }
                return response.json();
            });
    }


    findAllBtn.addEventListener('click', function () {
        const url = '/courses/';
        fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok' + reponse);
                }
                return response.json();
            })
            .then(courses => {
                renderCourseList(courses);
            })
            .catch(error => {
                alert('Error fetching courses: ' + error.message);
                console.error(error);
            });
    });

    function renderCourseList(courses) {
        const formContainer = document.getElementById('formContainer');
        formContainer.innerHTML = `<h2><strong>List of Courses</strong></h2>`;

        if (courses.length === 0) {
            formContainer.innerHTML += '<p>No courses found.</p>';
            return;
        }

        const table = `
            <table class="table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Semester</th>
                    </tr>
                </thead>
                <tbody>
                    ${courses.map(course => `
                        <tr>
                            <td>${course.courseId}</td>
                            <td>${course.courseName}</td>
                            <td>${course.semester.semester}</td>
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
            <h2><strong>Add Course</strong></h2>
            <form id="courseForm">
                <div class="mb-3">
                    <input type="text" class="form-control" id="nameInput" placeholder="Name" required>
                </div>
                <div class="mb-3">
                    <select id="degreeSelect" class="form-select" required>
                        <option value="" disabled selected>Select Degree</option>
                    </select>
                </div>
                <div class="mb-3">
                    <select id="semesterSelect" class="form-select" required>
                        <option value="" disabled selected>Select Semester</option>
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

        fetch('/semesters/')
            .then(response => response.json())
            .then(data => {
                const semesterSelect = document.querySelector('#semesterSelect');
                data.forEach(semester => {
                    const option = document.createElement('option');
                    option.value = semester.id;
                    option.text = semester.semester;
                    semesterSelect.appendChild(option);
                });
            })
            .catch(error => console.error('Error loading semester:', error));

        document.getElementById("courseForm").onsubmit = function (e) {
            e.preventDefault();

            const courseData = {
                courseName: document.querySelector('#nameInput').value,
                degreeCourses: {
                    degree: {
                        id: document.querySelector('#degreeSelect').value
                    }
                },
                semester: {
                    id: document.querySelector('#semesterSelect').value
                }
            };

            console.log(JSON.stringify(courseData));

            const url = '/courses/';
            const settings = {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(courseData)
            };

            fetch(url, settings)
                .then(response => response.json())
                .then(data => {
                    alert('Course added successfully');
                    document.getElementById('courseForm').reset();
                })
                .catch(error => {
                    alert('Error adding course' + error);
                    console.error(error);
                });
        };
    });


    updateBtn.addEventListener('click', function () {
        renderForm(`
            <div class="form">
                <h2><strong>Update Course</strong></h2>
                <form id="searchCourseForm">
                    <div class="mb-3">
                        <input type="text" class="form-control" id="idInput" placeholder="Enter ID" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Search</button>
                </form>
            </div>
        `);

        document.getElementById("searchCourseForm").onsubmit = function (e) {
            e.preventDefault();
            const id = document.querySelector('#idInput').value;

            const url = `/courses/${id}`;
            fetch(url)
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Course not found');
                    }
                    return response.json();
                })
                .then(course => {
                    renderForm(`
                    <div class="form">
                        <h2><strong>Update Course</strong></h2>
                        <form id="updateCourseForm">
                            <div class="mb-3">
                                <input type="text" class="form-control" id="nameInput" value="${course.courseName}" required>
                            </div>
                            <div class="mb-3">
                                <select id="semesterSelect" class="form-select" required>
                                    <option value="" disabled selected>Select Semester</option>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary">Update</button>
                        </form>
                    </div>
                    `);

                    fetch('/semesters/')
                        .then(response => response.json())
                        .then(data => {
                            const semesterSelect = document.querySelector('#semesterSelect');
                            data.forEach(semester => {
                                const option = document.createElement('option');
                                option.value = semester.id;
                                option.text = semester.semester;
                                semesterSelect.appendChild(option);

                                if (semester.id === course.semester.id) {
                                    option.selected = true;
                                }
                            });
                        })
                        .catch(error => console.error('Error loading semester:', error));

                    document.getElementById("updateCourseForm").onsubmit = function (e) {
                        e.preventDefault();

                        const updatedData = {
                            courseName: document.querySelector('#nameInput').value,
                            semester: {
                                id: document.querySelector('#semesterSelect').value
                            }
                        };

                        const updateUrl = `/courses/${id}`;
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
                                    throw new Error('Error updating course');
                                }
                                return response.json();
                            })
                            .then(data => {
                                alert('Course updated successfully');
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
                <h2><strong>Delete Course</strong></h2>
                <form id="deleteCourseForm">
                    <div class="mb-3">
                        <input type="text" class="form-control" id="idInput" placeholder="Enter ID" required>
                    </div>
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
            </div>
        `);

        document.getElementById("deleteCourseForm").onsubmit = function (e) {
            e.preventDefault();
            const id = document.querySelector('#idInput').value;

            const url = `/courses/${id}`;
            const settings = {
                method: 'DELETE'
            };

            fetch(url, settings)
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Error deleting course');
                    }
                    alert('Course deleted successfully');
                })
                .catch(error => {
                    alert(error.message);
                    console.error(error);
                });
        };
    });

});