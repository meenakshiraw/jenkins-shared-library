def call(String type = 'fs') {
    def cacheDir = "${env.WORKSPACE}/.trivycache"
    sh "mkdir -p ${cacheDir}"

    if (type == 'fs') {
        echo 'Scanning source code for vulnerabilities...'
        sh "trivy fs --severity HIGH,CRITICAL --cache-dir ${cacheDir} . || true"
    } else {
        echo 'Scanning Docker images for vulnerabilities...'
        sh "trivy image --severity HIGH,CRITICAL --cache-dir ${cacheDir} backend-image:latest || true"
        sh "trivy image --severity HIGH,CRITICAL --cache-dir ${cacheDir} frontend-image:latest || true"
    }
}
