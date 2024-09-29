window.addEventListener("load", function () {
    const searchBtn = document.getElementById('searchBtn');
    const userIdElement = document.getElementById("userId");
    const userId = userIdElement.innerText;

    function renderForm(htmlContent) {
        formContainer.innerHTML = htmlContent;
    }

searchBtn.addEventListener('click', function () {
    renderForm(`
            <div class="form">
                <h2><strong>Search Group by ID</strong></h2>
                <form id="searchGroupForm">
                    <div class="mb-3">
                        <input type="text" class="form-control" id="idInput" placeholder="Enter Student ID" required>
                    </div>
                    <div class="mb-3">
                        <select id="courseSelect" class="form-select" required>
                            <option value="" disabled selected>Select Group</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Search</button>
                </form>
            </div>
        `);
        fetch(`/teachers/teacher-group/${userId}`)
                    .then(response => response.json())
                    .then(data => {
                        const degreeSelect = document.querySelector('#courseSelect');
                        data.forEach(group => {
                            const option = document.createElement('option');
                            option.value = group.groupId;
                            option.text = `${group.groupId} - ${group.offerDayWeek.dayWeek.day}`;
                            degreeSelect.appendChild(option);
                        });
                    })
                    .catch(error => console.error('Error loading course:', error));


    document.getElementById("searchGroupForm").onsubmit = function (e) {
        e.preventDefault();
        const studentId = document.querySelector('#idInput').value;
        const groupId = document.querySelector('#courseSelect').value;

        const url = `/teachers/teacher-grade/${studentId}/${groupId}/${userId}`;
        fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Group not found');
                }
                return response.json();
            })
            .then(grade => {
                renderForm(`
                    <div class="form">
                        <h2><strong>Management Grades</strong></h2>
                        <h3 id="studentName"></h3>
                        <form id="updateGroupForm">
                            <div class="mb-3">
                                <input type="number" class="form-control" id="testOneInput" value="${grade.testOne}" step="0.1" required>
                            </div>
                            <div class="mb-3">
                                <input type="number" class="form-control" id="testTwoInput" value="${grade.testTwo}" step="0.1" required>
                            </div>
                            <div class="mb-3">
                                <input type="number" class="form-control" id="followUpInput" value="${grade.followUp}" step="0.1" required>
                            </div>
                            <button type="submit" class="btn btn-primary">Update</button>
                        </form>
                    </div>
                `);
                const studentNameElement = document.getElementById('studentName');
                studentNameElement.textContent = `${grade.student.user.name} ${grade.student.user.lastname}`;

                document.getElementById("updateGroupForm").onsubmit = function (e) {
                    e.preventDefault();

                    const updatedData = {
                        testOne: parseFloat(document.querySelector('#testOneInput').value) || 0,
                        testTwo: parseFloat(document.querySelector('#testTwoInput').value) || 0,
                        followUp: parseFloat(document.querySelector('#followUpInput').value) || 0,
                        group_has_course: {
                            id: grade.group_has_course.id
                        },
                        student:{
                            id: grade.student.id
                        }
                    };

                    updatedData.status = (updatedData.testOne + updatedData.testTwo + updatedData.followUp) / 3 >= 3 ? 1 : 0;

                    const updateUrl = `/grades/${grade.gradeId}`;
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