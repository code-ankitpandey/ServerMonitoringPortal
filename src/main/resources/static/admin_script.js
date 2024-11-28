
const modalForm = document.getElementById("modalForm");
let authToken;
class SafeStringStack {
    constructor() {
        this.stack = []; // Internal array to store stack elements
    }

    // Push a string onto the stack
    push(item) {
        if (typeof item !== "string") {
            console.warn("Only strings are allowed. Item not added to the stack.");
            return false; // Indicate failure to push
        }
        this.stack.push(item);
        return true; // Indicate success
    }

    // Pop the top element from the stack
    pop() {
        if (this.isEmpty()) {
            console.warn("Stack is empty. Nothing to pop.");
            return null; // Return null when stack is empty
        }
        return this.stack.pop();
    }

    // Peek at the top element of the stack without removing it
    peek() {
        if (this.isEmpty()) {
            console.warn("Stack is empty. Nothing to peek.");
            return null; // Return null when stack is empty
        }
        return this.stack[this.stack.length - 1];
    }

    // Check if the stack is empty
    isEmpty() {
        return this.stack.length === 0;
    }
}


const modalStack = new SafeStringStack();


function hideLoader() {
    isBlur = false;
    const loader = document.getElementById("loader");
    loader.style.display = 'none';
    document.querySelector('.mainBody').classList.remove("blur-effect");
}
function showLoader() {
    isBlur = true;
    document.querySelector('.mainBody').classList.add("blur-effect");
    const loader = document.getElementById("loader");
    loader.style.display = 'block';
}


function openModal(id) {
    if (!modalStack.isEmpty()) {
        const openModal = document.getElementById(modalStack.peek());
        openModal.style.display = "none";
    }
    const modal = document.getElementById(id);
    if (modal) {
        modal.style.display = "block";
        modalStack.push(id);
    } else {
        console.error(`No modal found with id: ${id}`);
    }
}

function openModalAndGenerate(option) {
    if (!modalStack.isEmpty()) {
        const openModal = document.getElementById(modalStack.peek());
        openModal.style.display = "none";
    }

    const id = "common_modal";

    const modal = document.getElementById(id);
    if (modal) {
        modal.style.display = "block";
        modalStack.push(id);
    } else {
        console.error(`No modal found with id: ${id}`);
    }
    modalForm.innerHTML = generateForm(option);
}

function openModalAndGenerateResult(option,modalId,formId) {
    if (!modalStack.isEmpty()) {
        const openModal = document.getElementById(modalStack.peek());
        openModal.style.display = "none";
    }

    const modal = document.getElementById(modalId);
    if (modal) {
        modal.style.display = "block";
        modalStack.push(modalId);
    } else {
        console.error(`No modal found with id: ${modalId}`);
    }
    let modalForm1 = document.getElementById(formId);
    modalForm1.innerHTML = generateForm(option);
}

function closeModal() {
    let id = modalStack.pop();
    const modal = document.getElementById(id);
    if (modal) {
        modal.style.display = "none";
    } else {
        console.error(`No modal found with id: ${id}`);
    }
    if (!modalStack.isEmpty()) {
        const openModal = document.getElementById(modalStack.peek());
        openModal.style.display = "block";
    }
}

function generateForm(option) {
    if (option === "displayServer") {
        return `
            <div class="form-container">
                <h2>Display Server Details</h2>
                <input type="text" id="serverIP" placeholder="Enter Server IP" required>
                <button type="button" onclick="validateDisplayServerForm()">Submit</button>
            </div>
        `;
    } else if (option === "createServer") {
        return `
            <div class="form-container">
                <h2>Add Server</h2>
                <input type="text" id="IP" placeholder="IP" required>
                <input type="text" id="userName" placeholder="User Name" required>
                <input type="password" id="password" placeholder="Password" required>
                 <input type="password" id="confirmPassword" placeholder="Confirm Password" required>
                <input type="text" id="Name" placeholder="Name" required>
                <input type="text" id="Description" placeholder="Description">
                <input type="number" id="consumptionAlertPercentage" placeholder="Consumption Alert (%)" required>
                <input type="number" id="growthAlertPercentage" placeholder="Growth Alert (%)" required>
                <input type="checkbox" id="isActive"> Active
                <input type="number" id="port" placeholder="Port" required>
                <button type="button" onclick="validateCreateServerForm()">Submit</button>
            </div>
        `;
    } else if (option === "changeServerPassword") {
        return `
            <div class="form-container">
                <h2>Change Server Password</h2>
                <input type="text" id="serverIP" placeholder="Server IP" required>
                <input type="password" id="newPassword" placeholder="New Password" required>
                <input type="password" id="confirmNewPassword" placeholder="Confirm New Password" required>
                <button type="button" onclick="validateChangeServerPasswordForm()">Submit</button>
            </div>
        `;
    } else if (option === "modifyServer") {
        return `
            <div class="form-container">
                <h2>Modify Server Details</h2>
                <input type="text" id="serverIP" placeholder="Server IP" required>
                <button type="button" onclick="validateModifyServerForm()">Fetch Details</button>
                <div id="modifyForm"></div>
            </div>
        `;
    } else if (option === "createUser") {
        return `
            <div class="form-container">
                <h2>Create User</h2>
                <input type="text" id="userName" placeholder="Username" required>
                <input type="text" id="firstName" placeholder="First Name" required>
                <input type="text" id="lastName" placeholder="Last Name" required>
                <input type="email" id="email" placeholder="Email" required>
                <input type="password" id="password" placeholder="Password" required>
                <input type="password" id="confirmPassword" placeholder="Confirm Password" required>
                <label for="isActive">Active</label>
                <input type="checkbox" id="isActive">
                <select id="role" required>
                    <option value="" disabled selected>Select Role</option>
                    <option value="ADMIN">ADMIN</option>
                    <option value="USER">USER</option>
                </select>
                <button type="button" onclick="validateCreateUserForm()">Submit</button>
            </div>
        `;
    } else if (option === "modifyUser") {
        return `
            <div class="form-container">
                <h2>Modify User Details</h2>
                <input type="text" id="userName" placeholder="Username" required>
                <button type="button" onclick="validateModifyUserForm()">Fetch Details</button>
                <div id="modifyForm"></div>
            </div>
        `;
    } else if (option === "changeUserPassword") {
        return `
            <div class="form-container">
                <h2>Change User Password</h2>
                <input type="text" id="userName" placeholder="Username" required>
                <input type="password" id="newPassword" placeholder="New Password" required>
                <input type="password" id="confirmNewPassword" placeholder="Confirm New Password" required>
                <button type="button" onclick="validateChangeUserPasswordForm()">Submit</button>
            </div>
        `;
    } else if (option === "displayUser") {
        return `
            <div class="form-container">
                <h2>Display User Details</h2>
                <input type="text" id="userName" placeholder="Username" required>
                <button type="button" onclick="validateDisplayUserForm()">Fetch Details</button>
                <div id="DisplayForm"></div>
            </div>
        `;
    } else if (option === "displayUserResponse") {
        return `
        <div class="form-container">
            <h2>User Details</h2>
            <input type="text" id="userName_res" placeholder="Username" required readOnly>
            <input type="text" id="firstName_res" placeholder="First Name" required readOnly>
            <input type="text" id="lastName_res" placeholder="Last Name" required readOnly>
            <input type="email" id="email_res" placeholder="Email" required readOnly>
            <label for="isActive_res" readOnly>Active</label>
             <input type="checkbox" id="isActive_res" disabled>
             <select id="role_res" required disabled>
        <option value="" disabled selected>Select Role</option>
        <option value="ADMIN">ADMIN</option>
        <option value="USER">USER</option>
         </select>
        </div>
    `;
    }
    else if (option ==="changeUserResponse"){
        return `
        <div class="form-container">
            <h2>Modify User</h2>
            <input type="text" id="userName_mod" placeholder="Username" required readOnly>
            <input type="text" id="firstName_mod" placeholder="First Name" required>
            <input type="text" id="lastName_mod" placeholder="Last Name" required>
            <input type="email" id="email_mod" placeholder="Email" required>
            <label for="isActive_mod">Active</label>
            <input type="checkbox" id="isActive_mod">
            <select id="role_mod" required>
                <option value="" disabled selected>Select Role</option>
                <option value="ADMIN">ADMIN</option>
                <option value="USER">USER</option>
            </select>
            <button type="button" onclick="validateModifyUserSubmitForm()">Submit</button>
        </div>
    `;
    }

}


async function validateModifyUserSubmitForm(){

    const userName = document.getElementById("userName_mod").value;
    const firstName = document.getElementById("firstName_mod").value;
    const lastName = document.getElementById("lastName_mod").value;
    const email = document.getElementById("email_mod").value;
    const active = document.getElementById("isActive_mod").checked;
    const role = document.getElementById("role_mod").value;

    if (!userName || !firstName || !lastName || !email   || !role) {
        alert("Please fill out all fields.");
        return;
    }
  else if(!validateEmail(email)){
        alert("invalid email");
        return;
    }
    const authToken = localStorage.getItem("authToken");
    if (!authToken) {
        alert("Session expired. Please log in.");
        return;
    }


    showLoader();
    const userDTO = {
        userName,
        firstName,
        lastName,
        email,
        active,
        role
    };

    try {
        console.log(userDTO);
        // Send a POST request to the API
        const response = await fetch("http://localhost:8000/api/admin/user/modifyUser", {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${authToken}`
            },
            body: JSON.stringify(userDTO),
        });

        // Parse and handle the response
        if (response.ok) {
            const result = await response.text(); // Assuming the API returns plain text
            alert(result);
        } else {
            const error = await response.text();
            alert("Error modifying user: "  );
        }
    } catch (error) {
        alert("Failed to connect to the server: " + error.message);
    }
    hideLoader();
}

async function validateCreateUserForm() {

    const userName = document.getElementById("userName").value;
    const firstName = document.getElementById("firstName").value;
    const lastName = document.getElementById("lastName").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirmPassword").value;
    const active = document.getElementById("isActive").checked;
    const role = document.getElementById("role").value;

    if (!userName || !firstName || !lastName || !email || !password || !confirmPassword || !role) {
        alert("Please fill out all fields.");
        return;
    }
    else if (password !== confirmPassword) {
        alert("Passwords do not match.");
        return;
    }else if (!validateEmail(email)){
        alert("Invalid email");
        return;
    }
    const authToken = localStorage.getItem("authToken");
    if (!authToken) {
        alert("Session expired. Please log in.");
        return;
    }


    showLoader();
    const userDTO = {
        userName,
        firstName,
        lastName,
        email,
        password,
        active,
        role
    };

    try {
        // Send a POST request to the API
        const response = await fetch("http://localhost:8000/api/admin/user/createUser", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${authToken}`
            },
            body: JSON.stringify(userDTO),
        });

        // Parse and handle the response
        if (response.ok) {
            const result = await response.text(); // Assuming the API returns plain text
            alert(result);
        } else {
            const error = await response.text();
            alert("Error creating user: " + error);
        }
    } catch (error) {
        alert("Failed to connect to the server: " + error.message);
    }
    hideLoader();
}

async function validateChangeUserPasswordForm() {

    const userName = document.getElementById("userName").value;
    const newPassword = document.getElementById("newPassword").value;
    const confirmNewPassword = document.getElementById("confirmNewPassword").value;

    if (!userName || !newPassword || !confirmNewPassword) {
        alert("Please fill out all fields.");
        return;
    }

    if (newPassword !== confirmNewPassword) {
        alert("Passwords do not match.");
        return;
    }
        showLoader();

        const apiUrl = `http://localhost:8000/api/admin/user/changePassword?userName=${userName}&newPassword=${newPassword}`;
        try {
            // Make the GET request to the API
            const response = await fetch(apiUrl, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${authToken}` // Add your auth token here
                }
            });

            // Check if the response is successful (status code 200)
            if (!response.ok) {
                throw new Error('Failed to fetch user data');
            }
            else {
                alert(response.text() )
            }

    } catch(error){
        alert("Something went wrong, Try again");
    }
    hideLoader();


}

async function validateDisplayUserForm() {

    const userName = document.getElementById("userName").value;

    if (!userName) {
        alert("Please enter a username.");
        return;
    }
    showLoader();

    const apiUrl = `http://localhost:8000/api/admin/user/getUser?userName=${userName}`;
    try {
        // Make the GET request to the API
        const response = await fetch(apiUrl, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${authToken}` // Add your auth token here
            }
        });

        // Check if the response is successful (status code 200)
        if (!response.ok) {
            throw new Error('Failed to fetch user data');
        }
        openModalAndGenerateResult("displayUserResponse","display_modal","modalResponseForm");

        // Parse the response JSON into UserResponseDTO
        const userResponse = await response.json();
        // Populate the form fields with the user data


        document.getElementById('userName_res').value = userResponse.userName;
        document.getElementById('firstName_res').value = userResponse.firstName;
        document.getElementById('lastName_res').value = userResponse.lastName;
        document.getElementById('email_res').value = userResponse.email;
        document.getElementById('role_res').value = userResponse.role;
        document.getElementById('isActive_res').checked = userResponse.active;


} catch(error){
    alert("Something went wrong, Try again");
}
hideLoader();
}


async function validateModifyUserForm() {

  const userName = document.getElementById("userName").value;

    if (!userName) {
        alert("Please enter a username.");
        return;
    }

    showLoader();

    const apiUrl = `http://localhost:8000/api/admin/user/getUser?userName=${userName}`;
    try {
        // Make the GET request to the API
        const response = await fetch(apiUrl, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${authToken}` // Add your auth token here
            }
        });

        // Check if the response is successful (status code 200)
        if (!response.ok) {
            throw new Error('Failed to fetch user data');
        }
        openModalAndGenerateResult("changeUserResponse","display_modal","modalResponseForm");

        // Parse the response JSON into UserResponseDTO
        const userResponse = await response.json();
        console.log(userResponse);
        // Populate the form fields with the user data
        document.getElementById('userName_mod').value = userResponse.userName;
        document.getElementById('firstName_mod').value = userResponse.firstName;
        document.getElementById('lastName_mod').value = userResponse.lastName;
        document.getElementById('email_mod').value = userResponse.email;
        document.getElementById('role_mod').value = userResponse.role;
        document.getElementById('isActive_mod').checked = Boolean(userResponse.active);

    } catch (error) {
        console.error('Error fetching user data:', error);
        alert('Failed to fetch user data');
    }
    hideLoader();
}



function validateCreateServerForm() {

    const IP = document.getElementById("IP").value;
    const userName = document.getElementById("userName").value;
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirmPassword").value;
    const Name = document.getElementById("Name").value;
    const Description = document.getElementById("Description").value;
    const consumptionAlertPercentage = document.getElementById("consumptionAlertPercentage").value;
    const growthAlertPercentage = document.getElementById("growthAlertPercentage").value;
    const isActive = document.getElementById("isActive").checked;
    const port = document.getElementById("port").value;

    if (!IP || !userName || !password || !confirmPassword || !Name || !consumptionAlertPercentage || !growthAlertPercentage || !port || !Description) {
        alert("Please fill out all required fields.");
        return;
    }
    if (password !== confirmPassword) {
        alert("Password does not match");
    }

}

function validateChangeServerPasswordForm() {
    const serverIP = document.getElementById("serverIP").value;
    const newPassword = document.getElementById("newPassword").value;
    const confirmNewPassword = document.getElementById("confirmNewPassword").value;

    if (!serverIP || !newPassword || !confirmNewPassword) {
        alert("Please fill out all fields.");
        return;
    }

    if (newPassword !== confirmNewPassword) {
        alert("Passwords do not match.");
        return;
    }

    handleAPI("changeServerPassword", { serverIP, newPassword });
}

function validateDisplayServerForm() {
    const serverIP = document.getElementById("serverIP").value;

    if (!serverIP) {
        alert("Please enter a server IP.");
        return;
    }

    fetchServerDetails(serverIP);
}

function validateModifyServerForm() {
    const serverIP = document.getElementById("serverIP").value;

    if (!serverIP) {
        alert("Please enter a server IP.");
        return;
    }

    fetchServerDetails(serverIP);
}



function checkAuthorization() {
    // Retrieve the token from localStorage
    authToken = localStorage.getItem("authToken");

    if (!authToken) {
        // If the token is not found, redirect to the login screen
        alert("Please login. Redirecting to login...");
        window.location.href = "/login.html"; // Replace with your login page URL
        return;
    }

    try {
        // Decode the JWT to extract the role (base64 decoding)
        const tokenPayload = JSON.parse(atob(authToken.split(".")[1])); // Extract the payload from the token
        const userRole = tokenPayload.role;

        // Check if the role is 'ADMIN'
        if (userRole !== "ADMIN") {
            alert("Access denied. Admins only.");
            window.location.href = "/login.html"; // Redirect to the login screen
            return;
        }
        document.body.style.display = "block";

    } catch (error) {
        // Handle invalid token format or decoding errors
        alert("Invalid token. Redirecting to login...");
        window.location.href = "/login.html"; // Redirect to the login screen
    }
}


function validateEmail(email) {
    // Regular expression for validating an email address
    const emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    // Test the email against the regex
    if (emailRegex.test(email)) {
        return true; // Valid email
    } else {
        return false; // Invalid email
    }
}

// Call the function on page load
checkAuthorization();



