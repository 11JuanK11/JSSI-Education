document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("createMaterialBtn").addEventListener("click", function (e) {
        e.preventDefault();

        const formContainer = document.getElementById("formContainer");
        formContainer.innerHTML = `
            <div class="form">
                <h2><strong>Create Didactic Material</strong></h2>
                <form id="didacticMaterialForm">
                    <div class="mb-3">
                        <label for="groupSelect" class="form-label">Select Group</label>
                        <select class="form-select" id="groupSelect" required>
                            ${groups.map(group => `<option value="${group.groupId}">${group.classroom}</option>`).join('')}
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="courseSelect" class="form-label">Select Course</label>
                        <select class="form-select" id="courseSelect" required>
                            <!-- Aquí se llenará con los cursos asociados al grupo seleccionado -->
                        </select>
                    </div>
                    <div class="mb-3">
                        <textarea id="descriptionInput" class="form-control" placeholder="Enter material description" required></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">Create</button>
                </form>
            </div>
        `;

        document.getElementById("groupSelect").addEventListener("change", function () {
            const groupId = this.value;
            const selectedCourses = courses[groupId] || [];

            const courseSelect = document.getElementById("courseSelect");
            courseSelect.innerHTML = selectedCourses.map(course => `<option value="${course.courseId}">${course.courseName}</option>`).join('');
        });

        const groupSelect = document.getElementById("groupSelect");
        if (groupSelect.options.length > 0) {
            groupSelect.value = groupSelect.options[0].value;
            groupSelect.dispatchEvent(new Event('change'));
        }

        document.getElementById("didacticMaterialForm").onsubmit = function (e) {
            e.preventDefault();
            const groupId = document.querySelector('#groupSelect').value;
            const courseId = document.querySelector('#courseSelect').value;
            const description = document.querySelector('#descriptionInput').value;

            const materialData = {
                group_has_course: {
                    id: groupId
                },
                description: description
            };

            const url = `/didactic-materials/`;
            const settings = {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(materialData)
            };

            fetch(url, settings)
                .then(response => {
                    if (!response.ok) {
                        return response.text().then(text => {
                            throw new Error('Error creating material: ' + text);
                        });
                    }
                    return response.json();
                })
                .then(data => {
                    alert('Material created successfully! ID: ' + data.id + ' Description: ' + data.description);
                    formContainer.innerHTML = '';
                })
                .catch(error => {
                    alert('Error creating material: ' + error.message);
                    console.error(error);
                });
        };
    });
});
