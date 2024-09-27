window.addEventListener("load", function () {
    const createBtn = document.getElementById('createBtn');
    const updateBtn = document.getElementById('updateBtn');
    const deleteBtn = document.getElementById('deleteBtn');
    const findAllBtn = document.getElementById('findAllBtn');
    const formContainer = document.getElementById('formContainer');

    function renderForm(htmlContent) {
        formContainer.innerHTML = htmlContent;
    }


// findAll
findAllBtn.addEventListener('click', function () {
    const url = '/courses';
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


// create
createBtn.addEventListener('click', function () {
    renderForm(`
    <div class="form">
        <h2><strong>Add Manager</strong></h2>
        <form id="courseForm">
            <div class="mb-3">
                <input type="text" class="form-control" id="nameInput" placeholder="Name" required>
            </div>
            <div class="mb-3">
                <input type="text" class="form-control" id="idDegreeInput" placeholder="Id Degree" required>
            </div>
            <div class="mb-3">
                <input type="text" class="form-control" id="idSemesterInput" placeholder="Id Semester" required>
            </div>
            <button type="submit" class="btn btn-primary">Add</button>
        </form>
    </div>
    `);

    document.getElementById("courseForm").onsubmit = function (e) {
        e.preventDefault();

        const courseData = {
            courseName: document.querySelector('#nameInput').value,
            degreeCourses: {
                degree: {
                    id: document.querySelector('#idDegreeInput').value
                }
            },
            semester: {
                id: document.querySelector('#idSemesterInput').value
            }
        };

        console.log(JSON.stringify(courseData));

        const url = '/courses';
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


// Update
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
                                <input type="text" class="form-control" id="idSemesterInput" value="${course.semester.id}" required>
                            </div>
                            <button type="submit" class="btn btn-primary">Update</button>
                        </form>
                    </div>
                `);

                document.getElementById("updateCourseForm").onsubmit = function (e) {
                    e.preventDefault();

                    const updatedData = {
                        courseName: document.querySelector('#nameInput').value,

                        semester: {
                            id: document.querySelector('#idSemesterInput').value
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


// Delete
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