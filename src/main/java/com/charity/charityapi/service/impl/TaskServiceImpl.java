package com.charity.charityapi.service.impl;

import com.charity.charityapi.dto.TaskDto;
import com.charity.charityapi.dto.mapper.TaskDtoMapper;
import com.charity.charityapi.dto.mapper.UserDtoMapper;
import com.charity.charityapi.dto.request.CreateTaskRequest;
import com.charity.charityapi.persistence.Task;
import com.charity.charityapi.persistence.User;
import com.charity.charityapi.persistence.repository.TaskRepository;
import com.charity.charityapi.persistence.repository.UserRepository;
import com.charity.charityapi.persistence.repository.VolunteerRepository;
import com.charity.charityapi.service.TaskService;
import jakarta.annotation.Nonnull;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Implementation service of {@link TaskService}.
 */
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
  private final TaskRepository taskRepository;
  private final VolunteerRepository volunteerRepository;
  private final UserRepository userRepository;
  private final TaskDtoMapper taskDtoMapper;
  @Nonnull
  @Override
  public TaskDto createTask(@Nonnull final CreateTaskRequest request, @Nonnull final User author) {
    final var task = Task.builder()
        .name(request.getName())
        .shortDescription(request.getShortDescription())
        .description(request.getDescription())
        .finished(false)
        .tags(request.getTags())
        .author(author)
        .build();
    taskRepository.save(task);
    final var dto = taskDtoMapper.taskToTaskDto(task);
    return dto;
  }

  @Nonnull
  @Override
  public Set<TaskDto> getTasks(long page, long size) {
    return null;
  }

  @Nonnull
  @Override
  public Set<TaskDto> getTasksContainingTags(long page, long size, Set<String> tags) {
    return null;
  }

  @Nonnull
  @Override
  public TaskDto getTask(long id) {
    return null;
  }

  @Nonnull
  @Override
  public TaskDto deleteTask(long id) {
    return null;
  }

  @Override
  public TaskDto addVolunteer(long taskId, long userId) {
    return null;
  }

  @Override
  public TaskDto deleteVolunteer(long taskId, long userId) {
    return null;
  }

  @Override
  public TaskDto addExecutor(long taskId, long userId) {
    return null;
  }

  @Override
  public TaskDto finishTask(long taskId) {
    return null;
  }
}
