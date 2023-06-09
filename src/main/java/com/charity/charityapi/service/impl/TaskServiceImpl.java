package com.charity.charityapi.service.impl;

import com.charity.charityapi.config.jwt.authority.JwtUser;
import com.charity.charityapi.dto.TaskDto;
import com.charity.charityapi.dto.mapper.TaskDtoMapper;
import com.charity.charityapi.dto.mapper.UserDtoMapper;
import com.charity.charityapi.dto.request.CreateTaskRequest;
import com.charity.charityapi.dto.request.GetTasksRequest;
import com.charity.charityapi.dto.response.GetTasksResponse;
import com.charity.charityapi.handler.exception.AccessDeniedException;
import com.charity.charityapi.handler.exception.NotFoundException;
import com.charity.charityapi.persistence.Task;
import com.charity.charityapi.persistence.User;
import com.charity.charityapi.persistence.Volunteer;
import com.charity.charityapi.persistence.repository.TaskRepository;
import com.charity.charityapi.persistence.repository.UserRepository;
import com.charity.charityapi.persistence.repository.VolunteerRepository;
import com.charity.charityapi.service.TaskService;
import jakarta.annotation.Nonnull;
import jakarta.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
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
  public GetTasksResponse getTasks(@Nonnull final GetTasksRequest request) {
    final var tasks = taskRepository.findAllByFinishedOrderByIdDesc(
        PageRequest.of(request.getPage(), request.getSize()),
        false
    );
    if (tasks.size() == 0) {
      return GetTasksResponse.builder().lastPage(true).build();
    }
    final var taskDtos = taskDtoMapper.tasksToTasksDto(tasks);
    if (tasks.size() < request.getSize()) {
      return GetTasksResponse.builder()
          .tasks(taskDtos)
          .lastPage(true)
          .build();
    }
    return GetTasksResponse.builder()
        .tasks(taskDtos)
        .lastPage(false)
        .build();
  }

  @Nonnull
  @Override
  public GetTasksResponse getTasksContainingTags(@Nonnull final GetTasksRequest request) {
    final var tasks = taskRepository.findAllByTagsInIgnoreCaseAndFinished(
        PageRequest.of(request.getPage(), request.getSize()),
        request.getTags(),
        false
    );
    if (tasks.size() == 0) {
      return GetTasksResponse.builder().lastPage(true).build();
    }
    final var taskDtos = taskDtoMapper.tasksToTasksDto(tasks);
    if (tasks.size() < request.getSize()) {
      return GetTasksResponse.builder()
          .tasks(taskDtos)
          .lastPage(true)
          .build();
    }
    return GetTasksResponse.builder()
        .tasks(taskDtos)
        .lastPage(false)
        .build();
  }

  @Nonnull
  @Override
  public TaskDto getTask(final long id) {
    final var task = taskRepository.findById(id);
    if (task != null) {
      final var taskDto = taskDtoMapper.taskToTaskDto(task);
      return taskDto;
    }
    throw new NotFoundException(String.format("Task with id %s not found", id));
  }

  @Nonnull
  @Override
  public TaskDto deleteTask(final long id) {
    final var task = taskRepository.findById(id);
    if (task != null) {
      taskRepository.deleteById(id);
      final var taskDto = taskDtoMapper.taskToTaskDto(task);
      return taskDto;
    }
    throw new NotFoundException(String.format("Task with id %s not found", id));
  }

  @Override
  public TaskDto addVolunteer(final long taskId, @Nonnull final JwtUser user) {
    final var task = taskRepository.findById(taskId);
    if (task == null) {
      throw new NotFoundException(String.format("Task with id %s not found", taskId));
    }
    if (!volunteerRepository.existsByUserIdAndTaskId(user.getId(), taskId)) {
      final var userEntity = userRepository.findById(user.getId());
      final var volunteer = Volunteer.builder()
          .user(userEntity)
          .task(task)
          .executor(false)
          .build();
      volunteerRepository.save(volunteer);
    }
    final var taskDto = taskDtoMapper.taskToTaskDto(task);
    return taskDto;
  }

  @Override
  @Transactional
  public TaskDto deleteVolunteer(final long taskId, @Nonnull final JwtUser user) {
    final var task = taskRepository.findById(taskId);
    if (task == null) {
      throw new NotFoundException(String.format("Task with id %s not found", taskId));
    }
    if (volunteerRepository.existsByUserIdAndTaskId(user.getId(), taskId)) {
      final var volunteer = volunteerRepository.findByUserIdAndTaskId(user.getId(), taskId);
      volunteerRepository.delete(volunteer);
    }
    final var taskDto = taskDtoMapper.taskToTaskDto(task);
    return taskDto;
  }

  @Override
  public TaskDto addExecutor(final long taskId, final long userId, @Nonnull final JwtUser user) {
    final var task = taskRepository.findById(taskId);
    if (task == null) {
      throw new NotFoundException(String.format("Task with id %s not found", taskId));
    }
    if (task.getAuthor().getId() != user.getId()) {
      throw new AccessDeniedException("You cannot perform this action");
    }
    if (volunteerRepository.existsByUserIdAndTaskId(user.getId(), taskId)) {
      final var volunteer = volunteerRepository.findByUserIdAndTaskId(user.getId(), taskId);
      volunteer.setExecutor(true);
      volunteerRepository.save(volunteer);
    }
    final var taskDto = taskDtoMapper.taskToTaskDto(task);
    return taskDto;
  }

  @Override
  public TaskDto finishTask(final long taskId, @Nonnull final JwtUser user) {
    final var task = taskRepository.findById(taskId);
    if (task == null) {
      throw new NotFoundException(String.format("Task with id %s not found", taskId));
    }
    if (task.getAuthor().getId() != user.getId()) {
      throw new AccessDeniedException("You cannot perform this action");
    }
    task.setFinished(true);
    final var executors = volunteerRepository.findByTaskId(taskId);
    for (final var executor: executors) {
      final var userVolunteer = executor.getUser();
      userVolunteer.setReputation(userVolunteer.getReputation()+1);
      userRepository.save(userVolunteer);
    }
    taskRepository.save(task);
    final var taskDto = taskDtoMapper.taskToTaskDto(task);
    return taskDto;
  }

  @Override
  public Set<TaskDto> getTasksCreatedByUser(long userId) {
    final var user = userRepository.findById(userId);
    if (user != null) {
      final var tasks = taskRepository.findByAuthorIdAndFinished(userId,false);
      final var taskDtos = taskDtoMapper.tasksToTasksDto(tasks);
      return taskDtos;
    }
    throw new NotFoundException("Cant access data of non existing user.");
  }

  @Override
  public Set<TaskDto> getUserVolunteerRequests(long userId) {
    final var user = userRepository.findById(userId);
    if (user != null) {
      final var volunteers = volunteerRepository.findByUserId(userId);
      final var tasks = new HashSet<Task>();
      for (final var volunteer:volunteers) {
        tasks.add(volunteer.getTask());
      }
      final var taskDtos = taskDtoMapper.tasksToTasksDto(tasks);
      return taskDtos;
    }
    throw new NotFoundException("Cant access data of non existing user.");
  }
}
