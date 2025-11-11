const API = {
    register: '/api/auth/register',
    login: '/api/auth/login',
    tasks: '/api/tasks'
};

function token() { return localStorage.getItem('jwt'); }
function authHeaders() {
    const t = token();
    return t ? { 'Authorization': 'Bearer ' + t } : {};
}

async function call(url, opts = {}) {
    opts.headers = Object.assign({ 'Content-Type': 'application/json' }, authHeaders(), opts.headers || {});
    const res = await fetch(url, opts);
    if (!res.ok) throw await res.json();
    if (res.status === 204) return null;
    return res.json();
}

document.getElementById('btnRegister').onclick = async () => {
    const u = document.getElementById('username').value;
    const p = document.getElementById('password').value;
    try {
        await call(API.register, { method: 'POST', body: JSON.stringify({ username: u, password: p }) });
        alert('registered - now login');
    } catch (e) { alert('register failed: ' + JSON.stringify(e)); }
};

document.getElementById('btnLogin').onclick = async () => {
    const u = document.getElementById('username').value;
    const p = document.getElementById('password').value;
    try {
        const r = await call(API.login, { method: 'POST', body: JSON.stringify({ username: u, password: p }) });
        localStorage.setItem('jwt', r.token);
        onLogin();
    } catch (e) { alert('login failed: ' + JSON.stringify(e)); }
};

document.getElementById('btnLogout').onclick = () => { localStorage.removeItem('jwt'); onLogout(); };

document.getElementById('create').onclick = async () => {
    const short = document.getElementById('short').value;
    const long = document.getElementById('long').value;
    const status = document.getElementById('status').value;
    try {
        await call(API.tasks, { method: 'POST', body: JSON.stringify({ shortDescription: short, longDescription: long, status }) });
        loadTasks();
    } catch (e) { alert('create failed: ' + JSON.stringify(e)); }
};

async function loadTasks() {
    try {
        const tasks = await call(API.tasks);
        const ul = document.getElementById('tasks');
        ul.innerHTML = '';
        tasks.forEach(t => {
            const li = document.createElement('li');
            li.className = 'list-group-item d-flex justify-content-between align-items-start';
            li.innerHTML = `<div>
                        <div><strong>${escapeHtml(t.shortDescription)}</strong></div>
                        <div>${escapeHtml(t.longDescription)}</div>
                        <small>${t.status}</small>
                      </div>
                      <div>
                        <button class="btn btn-sm btn-danger">Delete</button>
                      </div>`;
            li.querySelector('button').addEventListener('click', async () => {
                await call(API.tasks + '/' + t.id, { method: 'DELETE' });
                loadTasks();
            });
            ul.appendChild(li);
        });
    } catch (e) {
        console.error(e);
    }
}

function onLogin(){
    document.getElementById('taskArea').style.display = 'block';
    document.getElementById('btnLogout').style.display = 'inline-block';
    document.getElementById('btnLogin').style.display = 'none';
    document.getElementById('btnRegister').style.display = 'none';
    loadTasks();
}

function onLogout(){
    document.getElementById('taskArea').style.display = 'none';
    document.getElementById('btnLogout').style.display = 'none';
    document.getElementById('btnLogin').style.display = 'inline-block';
    document.getElementById('btnRegister').style.display = 'inline-block';
}

function escapeHtml(s){ return (s||'').replace(/[&<>"']/g, c => ({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'})[c]); }

if (token()) onLogin();
