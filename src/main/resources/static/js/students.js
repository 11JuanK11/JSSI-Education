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

//------------------------------------------delete-------------------------------------------------------------------------

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


       document.getElementById('deleteStudentForm').addEventListener('submit', function (e) {
           e.preventDefault();
           const dni = document.getElementById('dniInput').value;


           fetch(`/students/${dni}`, {
               method: 'DELETE',
           })
           .then(response => {
               if (response.ok) {
                   alert('Student deleted successfully!');
                   renderForm('');
               } else if (response.status === 404) {
                   throw new Error('Student not found.');
               } else {
                   throw new Error('Error deleting student.');
               }
           })
           .catch((error) => {
               console.error('Error:', error);
               alert(error.message);
           });
       });
   });

// ----------------------------------update-------------------------------------------------------------------

updateBtn.addEventListener('click', function () {
    renderForm(`
        <div class="form">
            <h2><strong>Update Student</strong></h2>
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

        // Fetch the student by ID number
        fetch(`/students/${idNumber}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Student not found');
                }
                return response.json();
            })
            .then(student => {
                renderForm(`
                    <div class="form">
                        <h2><strong>Update Student</strong></h2>
                        <form id="updateStudentForm">
                            <div class="mb-3">
                                <input type="text" class="form-control" id="idNumberInput" value="${student.user.idNumber}" placeholder="Enter ID number" required>
                            </div>
                            <div class="mb-3">
                                <input type="text" class="form-control" id="nameInput" value="${student.user.name}" required>
                            </div>
                            <div class="mb-3">
                                <input type="text" class="form-control" id="lastnameInput" value="${student.user.lastname}" required>
                            </div>
                            <div class="mb-3">
                                <input type="email" class="form-control" id="emailInput" value="${student.user.email}" required>
                            </div>
                            <div class="mb-3">
                                <input type="text" class="form-control" id="usernameInput" value="${student.user.userName}" placeholder="Enter username" required>
                            </div>
                            <div class="mb-3">
                                <input type="password" class="form-control" id="passwordInput" placeholder="Leave blank to keep unchanged">
                            </div>
                            <div class="mb-3">
                                <select id="degreeSelect" class="form-select" required>
                                    <option value="" disabled>Select Degree</option>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary">Update</button>
                        </form>
                    </div>
                `);

                // Fetch degrees to populate the dropdown
                fetch('/degrees/')
                    .then(response => response.json())
                    .then(degrees => {
                        const degreeSelect = document.querySelector('#degreeSelect');
                        degrees.forEach(degree => {
                            const option = document.createElement('option');
                            option.value = degree.id;
                            option.text = degree.name;
                            if (degree.id === student.degree.id) {
                                option.selected = true; // Select the current degree
                            }
                            degreeSelect.appendChild(option);
                        });
                    })
                    .catch(error => console.error('Error loading degrees:', error));


                document.getElementById("updateStudentForm").onsubmit = function (e) {
                    e.preventDefault();

                    const updatedData = {
                        user: {
                            idNumber: document.querySelector('#idNumberInput').value,
                            name: document.querySelector('#nameInput').value,
                            lastname: document.querySelector('#lastnameInput').value,
                            userName: document.querySelector('#usernameInput').value,
                            email: document.querySelector('#emailInput').value,
                            password: document.querySelector('#passwordInput').value || undefined
                        },
                        degree: {
                            id: document.querySelector('#degreeSelect').value
                        }
                    };


                    fetch(`/students/${idNumber}`, {
                        method: 'PUT',
                        headers: {
                            'Content-Type': 'application/json',
                        },
                        body: JSON.stringify(updatedData)
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
                    .catch(error => {
                        alert(error.message);
                        console.error('Update error details:', error);
                    });
                };
            })
            .catch(error => {
                alert(error.message);
                console.error('Search error:', error);
            });
    };
});
 //---------------------------------------------------findAll-------------------------------------------------------------

 findAllBtn.addEventListener('click', function () {
     const url = '/students/';
     fetch(url)
         .then(response => {
             if (!response.ok) {
                 throw new Error('Network response was not ok');
             }
             return response.json();
         })
         .then(students => {
             renderStudentList(students);
         })
         .catch(error => {
             alert('Error fetching students: ' + error.message);
             console.error(error);
         });
 });


 function renderStudentList(students) {
     const formContainer = document.getElementById('formContainer');
     formContainer.innerHTML = `<h2><strong>List of Students</strong></h2>`;

     if (students.length === 0) {
         formContainer.innerHTML += '<p>No students found.</p>';
         return;
     }

     const table = `
         <table class="table">
             <thead>
                 <tr>
                     <th>ID Number</th>
                     <th>Name</th>
                     <th>Lastname</th>
                     <th>Username</th>
                     <th>Email</th>
                     <th>Degree</th>
                 </tr>
             </thead>
             <tbody>
                 ${students.map(student => `
                     <tr>
                         <td>${student.user.idNumber}</td>
                         <td>${student.user.name}</td>
                         <td>${student.user.lastname}</td>
                         <td>${student.user.userName}</td>
                         <td>${student.user.email}</td>
                         <td>${student.degree ? student.degree.name : 'No degree'}</td>
                     </tr>
                 `).join('')}
             </tbody>
         </table>
     `;

     formContainer.innerHTML += table;
 }




});
