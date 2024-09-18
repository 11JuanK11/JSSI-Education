window.addEventListener("load", function () {
    const createBtn = document.getElementById('createBtn');
    const searchBtn = document.getElementById('searchBtn');
    const updateBtn = document.getElementById('updateBtn');
    const deleteBtn = document.getElementById('deleteBtn');
    const formContainer = document.getElementById('formContainer');

    function renderForm(htmlContent) {
        formContainer.innerHTML = htmlContent;
    }

    //crear estudiante
    createBtn.addEventListener('click', function () {
        renderForm(`
            <div class="form">
                <h2><strong>Add Student</strong></h2>
                <form>
                    <div class="mb-3">
                        <select id="idType" class="form-select">
                            <option value="" disabled selected>ID Type</option>
                            <option>National ID Card</option>
                            <option>Foreign ID card</option>
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
    });

    //buscar estudiante
    searchBtn.addEventListener('click', function () {
        renderForm(`
            <div class="form">
                <h2><strong>Search Student by ID number</strong></h2>
                <form>
                    <div class="mb-3">
                        <input type="text" class="form-control" id="dniInput" placeholder="Enter ID number" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Search</button>
                </form>
            </div>
        `);
    });

    //borrar estudiante
    deleteBtn.addEventListener('click', function () {
        renderForm(`
            <div class="form">
                <h2><strong>Delete Student</strong></h2>
                <form>
                    <div class="mb-3">
                        <input type="text" class="form-control" id="dniInput" placeholder="Enter ID number" required>
                    </div>
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
            </div>
        `);
    });

    //actualizar estudiante
    updateBtn.addEventListener('click', function () {
        renderForm(`
            <div class="form">
                <h2><strong>Update Student</strong></h2>
                <form id="searchForm">
                    <div class="mb-3">
                        <input type="text" class="form-control" id="dniInput" placeholder="Enter ID number" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Search</button>
                </form>
            </div>
        `);

        //hay que hacer que cuando encuentre el estudiante a actualizar se renderize el form de crear estudiante pero con el id fijo y los valores que ya habian en la bd
    });
});
