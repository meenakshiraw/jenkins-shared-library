def call(String composeFile = "docker-compose.yaml") {
    def IMAGE_TAG = "build-${env.BUILD_NUMBER}"
    echo "Using image tag: ${IMAGE_TAG}"

    sh """
        sed -i 's|chattingo-frontend:.*|chattingo-frontend:${IMAGE_TAG}|' ${composeFile}
        sed -i 's|chattingo-backend:.*|chattingo-backend:${IMAGE_TAG}|' ${composeFile}
        cat ${composeFile}
    """

    //sh "/usr/local/bin/docker-compose -f ${composeFile} up --build -d"
    sh "/usr/local/bin/docker-compose -f ${composeFile} down"
}
