document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("createMaterialBtn").addEventListener("click", function (e) {
        e.preventDefault();
        showCreateForm();
    });

    document.getElementById("updateMaterialBtn").addEventListener("click", function (e) {
        e.preventDefault();
        showUpdateForm();
    });

    function showCreateForm() {
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
                            throw new Error(`Error ${response.status}: ${text}`);
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
    }



    function showUpdateForm() {
        const formContainer = document.getElementById("formContainer");
        formContainer.innerHTML = `
            <div class="form">
                <h2><strong>Update Didactic Material</strong></h2>
                <div class="mb-3">
                    <label for="groupSelectUpdate" class="form-label">Select Group</label>
                    <select class="form-select" id="groupSelectUpdate" required>
                        ${groups.map(group => `<option value="${group.groupId}">${group.classroom}</option>`).join('')}
                    </select>
                </div>
                <div class="mb-3">
                    <label for="courseSelectUpdate" class="form-label">Select Course</label>
                    <select class="form-select" id="courseSelectUpdate" required>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="materialSelect" class="form-label">Select Material</label>
                    <input type="text" id="materialIdInput" class="form-control" required>
                    </select>
                </div>
                <div class="mb-3">
                    <textarea id="updateDescriptionInput" class="form-control" placeholder="Enter new description" required></textarea>
                </div>
                <button id="submitUpdateBtn" class="btn btn-primary">Update</button>
                <button id="showMaterials" class="btn btn-info">Show Materials</button>
            </div>
        `;

        document.getElementById("groupSelectUpdate").addEventListener("change", function () {
            const groupId = this.value;
            const selectedCourses = courses[groupId] || [];
            const courseSelect = document.getElementById("courseSelectUpdate");
            courseSelect.innerHTML = selectedCourses.map(course => `<option value="${course.courseId}">${course.courseName}</option>`).join('');
            document.getElementById("materialIdInput").innerHTML = '';
        });
            document.getElementById("courseSelectUpdate").addEventListener("change", function () {
            const groupId = document.getElementById("groupSelectUpdate").value;
            const courseId = this.value;

            if (groupId && courseId) {
                fetchMaterialsByGroupAndCourse(groupId, courseId);
            }
        });

        document.getElementById("submitUpdateBtn").addEventListener("click", function(e) {
            e.preventDefault();
            const materialId = document.getElementById("materialIdInput").value;
            const newDescription = document.getElementById("updateDescriptionInput").value;

            const materialData = {
                description: newDescription
            };

            const url = `/didactic-materials/${materialId}`;
            const settings = {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(materialData)
            };

            fetch(url, settings)
                .then(response => {
                    if (!response.ok) {
                        return response.text().then(text => {
                            throw new Error(`Error ${response.status}: ${text}`);
                        });
                    }
                    return response.json();
                })
                .then(data => {
                    alert('Material updated successfully! New Description: ' + data.description);
                    formContainer.innerHTML = '';
                })
                .catch(error => {
                    alert('Error updating material: ' + error.message);
                    console.error(error);
                });

                const tbody = document.querySelector("#materialsTable tbody");
                tbody.innerHTML = '';
        });

        document.getElementById("showMaterials").addEventListener("click", function() {
            const groupId = document.getElementById("groupSelectUpdate").value;
            const courseId = document.getElementById("courseSelectUpdate").value;

            // Verifica que ambos campos tengan valores
            if (groupId && courseId) {
                const url = `/didactic-materials/${groupId}/${courseId}`;

                fetch(url)
                    .then(response => {
                        if (!response.ok) {
                            throw new Error(`Error: ${response.statusText}`);
                        }
                        return response.json();
                    })
                    .then(data => {
                        showMaterials(data);
                    })
                    .catch(error => {
                        alert('Error fetching materials: ' + error.message);
                        console.error(error);
                    });
            } else {
                alert("Please select both a group and a course.");
            }
        });

        function showMaterials(materials) {
            const materialsContainer = document.getElementById("materialsContainer");
            const tbody = document.getElementById("materialsTable").querySelector("tbody");

            // Limpia el contenido previo
            tbody.innerHTML = '';

            // Agrega los materiales a la tabla
            materials.forEach(material => {
                const row = `<tr>
                    <td>${material.id}</td>
                    <td>${material.description}</td>
                    <td>${material.group_has_course.group.classroom}</td>
                    <td>${material.group_has_course.group.teacher.user.name} ${material.group_has_course.group.teacher.user.lastname}</td>
                    <td>${material.group_has_course.course.courseName}</td>
                </tr>`;
                tbody.innerHTML += row;
            });

            if (materials.length > 0) {
                materialsContainer.style.display = 'block';  // Muestra el contenedor
            } else {
                materialsContainer.style.display = 'none';  // Oculta el contenedor
            }
        }

    }



    function fetchMaterialsByGroupAndCourse( groupId, courseId) {
        fetch(`/didactic-materials/${groupId}/${courseId}`)
            .then(response => {
                if (!response.ok) {
                    return response.text().then(text => {
                        throw new Error(`Error ${response.status}: ${text}`);
                    });
                }
                return response.json();
            })
            .then(data => {
                materials = data;
                showMaterialsOptions();
            })
            .catch(error => {
                alert('Error fetching materials: ' + error.message);
                console.error(error);
            });
    }

    function showMaterialsOptions() {
        const materialSelect = document.getElementById("materialSelect");
        materialSelect.innerHTML = materials.map(material => `<option value="${material.id}">${material.description}</option>`).join('');
    }

    function fetchMaterials() {
        fetch('/didactic-materials/')
            .then(response => {
                if (!response.ok) {
                    return response.text().then(text => {
                        throw new Error(`Error ${response.status}: ${text}`);
                    });
                }
                return response.json();
            })
            .then(data => {
                materials = data;
            })
            .catch(error => {
                alert('Error fetching materials: ' + error.message);
                console.error(error);
            });
    }

});
