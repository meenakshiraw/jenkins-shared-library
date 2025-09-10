def call(String credsId, String repo) {
    echo 'Pushing Docker images to Docker Hub...'
    docker.withRegistry('https://index.docker.io/v1/', credsId) {
        sh "docker tag backend-image:latest ${repo}:backend-latest"
        sh "docker tag frontend-image:latest ${repo}:frontend-latest"

        sh "docker push ${repo}:backend-latest"
        sh "docker push ${repo}:frontend-latest"
    }
}
