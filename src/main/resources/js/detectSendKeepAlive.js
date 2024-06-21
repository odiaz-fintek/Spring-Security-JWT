let timeout;
const keepAliveInterval = 4 * 60 * 1000; // 4 minutos

function resetTimeout() {
    clearTimeout(timeout);
    timeout = setTimeout(sendKeepAliveRequest, keepAliveInterval);
}

function sendKeepAliveRequest() {
    const token = localStorage.getItem('token');
    fetch('http://localhost:8000/keep-alive', {
        method: 'POST',
        headers: {
            'Authorization': `Bearer ${token}`
        }
    })
        .then(response => {
            if (response.status === 200) {
                return response.json();
            } else {
                throw new Error('Failed to keep alive');
            }
        })
        .then(data => {
            if (data.token) {
                localStorage.setItem('token', data.token);
            }
            resetTimeout();
        })
        .catch(error => {
            console.error(error);
            // Handle token expiration or error
        });
}

// Detect user activity
window.addEventListener('mousemove', resetTimeout);
window.addEventListener('keydown', resetTimeout);
window.addEventListener('click', resetTimeout);
window.addEventListener('scroll', resetTimeout);

// Initialize timeout
resetTimeout();
