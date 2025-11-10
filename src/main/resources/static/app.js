const API = {
    tasks: '/api/tasks',
    signup: '/api/auth/signup',
    login: '/api/auth/login'
};

function getToken() {
    return localStorage.getItem('jwtToken');
}

function saveToken(t) {
    localStorage.setItem('jwtToken', t);
}

function authHeaders() {
    const token = getToken();
    return token ? { 'Authorization': 'Bearer ' + token } : {};
}

async function request(url, opts = {}) {
    opts.headers = Object.assign({'Content-Type': 'application/json'}, authHeaders(), opts.headers || {});
    const res = await fetch(url, opts);
    if (!res.ok) {
        // try parse error
        let err = await res.text();
        try { err = JSON.parse(err); } catch(e){}
        throw { status: res.status, body: err };
    }
    if (res.status === 204) return null;
    return res.json();
}

// Auth controls
document.getElementById('signup').onclick = async () => {
    const u = document.getElementById('username').value;
    const p = document.getElementById('password').value;
    try {
        const r = await request(API.signup, { method: 'POST', body: JSON.stringify({ username: u, password: p }) });
        saveToken(r.token);
        onLogin();
    } catch(e) { alert('Signup failed: ' + JSON.stringify(e)); }
};

document.getElementById('login').onclick = async () => {
    const u = document.getElementById('username').value;
    const p = document.getElementById('password').value;
    try {
        const r = await request(API.login, { method: 'POST', body: JSON.stringify({ username: u, password: p }) });
        saveToken(r.token);
        onLogin();
    } catch(e) { alert('Login failed: ' + JSON.stringify(e)); }
};

document.getElementById('logout').onclick = () => {
    localStorage.removeItem('jwtToken');
    onLogout();
};

// Task CRUD
const form = document.getElementById('task-form');
const taskList = document.getElementById('task-list');

form.onsubmit = async (e) => {
    e.preventDefault();
    const shortDescription = document.getElementById('shortDescription').value;
    const longDescription = document.getElementById('longDescription').value;
    const status = document.getElementById('status').value;
    await request(API.tasks, { method: 'POST', body: JSON.stringify({ shortDescription, longDescription, status }) });
    form.reset();
    loadTasks();
};

async function loadTasks() {
    try {
        const tasks = await request(API.tasks);
        renderTasks(tasks);
    } catch(e) {
        console.error('loadTasks error', e);
        renderTasks([]);
    }
}

function renderTasks(tasks) {
    taskList.innerHTML = '';
    tasks.forEach(t => {
        const li = document.createElement('li');
        li.className = 'list-group-item d-flex justify-content-between align-items-start';
        li.innerHTML = `<div>
        <h5>${escapeHtml(t.shortDescription)}</h5>
        <p class="mb-1">${escapeHtml(t.longDescription)}</p>
        <small>Status: ${t.status}</small>
      </div>
      <div>
        <button class="btn btn-sm btn-danger me-2">Delete</button>
      </div>`;
        li.querySelector('button').addEventListener('click', async () => {
            await request(`/api/tasks/${t.id}`, { method: 'DELETE' });
            loadTasks();
        });
        taskList.appendChild(li);
    });
}

function escapeHtml(text) {
    if (!text) return '';
    return text.replace(/[&<>"']/g, c => ({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'})[c]);
}

// UI state after login/logout
function onLogin() {
    document.getElementById('task-form').style.display = 'block';
    document.getElementById('logout').style.display = 'inline-block';
    document.getElementById('login').style.display = 'none';
    document.getElementById('signup').style.display = 'none';
    loadTasks();
}

function onLogout() {
    document.getElementById('task-form').style.display = 'none';
    document.getElementById('logout').style.display = 'none';
    document.getElementById('login').style.display = 'inline-block';
    document.getElementById('signup').style.display = 'inline-block';
    taskList.innerHTML = '';
}

// Check if already logged in
if (getToken()) {
    onLogin();
} else {
    onLogout();
}
