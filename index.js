
const { spawn } = require('child_process');
const path = require('path');
const http = require('http');


const JAR_PATH = path.join(process.cwd(), 'target', 'api-task-0.0.1-SNAPSHOT.jar');


const springBootProcess = spawn('java', ['-jar', JAR_PATH], { stdio: 'pipe' });

springBootProcess.stdout.on('data', (data) => {
    console.log(`Spring Boot STDOUT: ${data}`);
});

springBootProcess.stderr.on('data', (data) => {
    console.error(`Spring Boot STDERR: ${data}`);
});

springBootProcess.on('close', (code) => {
    console.log(`Spring Boot process exited with code ${code}`);
});

module.exports = async (req, res) => {
    //
    const backendPort = process.env.PORT || 8080;
    const options = {
        hostname: '127.0.0.1',
        port: backendPort,
        path: req.url,
        method: req.method,
        headers: req.headers,
    };

    const proxyReq = http.request(options, (proxyRes) => {
        res.writeHead(proxyRes.statusCode, proxyRes.headers);
        proxyRes.pipe(res);
    });

    proxyReq.on('error', (e) => {
        console.error(`Proxy request error: ${e.message}`);
        res.writeHead(500, { 'Content-Type': 'application/json' });
        res.end(JSON.stringify({ error: 'Failed to connect to Spring Boot backend.', details: e.message }));
    });


    req.pipe(proxyReq);
};
