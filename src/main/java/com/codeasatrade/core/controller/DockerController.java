package com.codeasatrade.core.controller;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.exception.DockerException;
import com.github.dockerjava.api.model.HostConfig;

public class DockerController {

    private DockerClient dockerClient;

    public DockerController() {
        this.dockerClient = DockerClientBuilder.getInstance().build();
    }

    public String createContainer(String imageName) {
        try {
            CreateContainerResponse container = dockerClient.createContainerCmd(imageName)
                    .withHostConfig(HostConfig.newHostConfig())
                    .exec();
            return container.getId();
        } catch (DockerException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void startContainer(String containerId) {
        try {
            dockerClient.startContainerCmd(containerId).exec();
        } catch (DockerException e) {
            e.printStackTrace();
        }
    }
}
