package com.charity.charityapi.service;

import com.charity.charityapi.config.jwt.authority.JwtUser;
import com.charity.charityapi.dto.TaskDto;
import com.charity.charityapi.dto.request.CreateTaskRequest;
import com.charity.charityapi.dto.request.GetTasksRequest;
import com.charity.charityapi.dto.response.GetTasksResponse;
import com.charity.charityapi.persistence.User;
import jakarta.annotation.Nonnull;
import java.util.Set;

/**
 * Service for {@link com.charity.charityapi.persistence.Task}.
 */
public interface TaskService {

  /**
   * Method to create new task.
   *
   * @param request data of new task.
   * @param author author of new task.
   * @return dto instance of created task.
   */
  @Nonnull
  TaskDto createTask(@Nonnull CreateTaskRequest request, @Nonnull User author);

  /**
   * Method to get page of tasks.
   *
   * @param request request with params.
   * @return set of tasks.
   */
  @Nonnull
  GetTasksResponse getTasks(@Nonnull GetTasksRequest request);

  /**
   * Method to get page of tasks that contains specific tags.
   *
   * @param request request with params.
   * @return set of tasks.
   */
  @Nonnull
  GetTasksResponse getTasksContainingTags(@Nonnull GetTasksRequest request);

  /**
   * Method to get task.
   *
   * @param id identifier of task to get.
   * @return task DTO.
   */
  @Nonnull
  TaskDto getTask(long id);

  /**
   * Method to delete task.
   *
   * @param id identifier of task to delete.
   * @return deleted task DTO.
   */
  @Nonnull
  TaskDto deleteTask(long id);

  /**
   * Method to add volunteer to task.
   *
   * @param taskId task add volunteer to.
   * @param user user who wants to be a volunteer.
   * @return task DTO.
   */
  TaskDto addVolunteer(long taskId, @Nonnull JwtUser user);

  /**
   * Method to delete volunteer from task.
   *
   * @param taskId task delete volunteer from.
   * @param user user which is already a volunteer.
   * @return task DTO.
   */
  TaskDto  deleteVolunteer(long taskId, @Nonnull JwtUser user);

  /**
   * Method to add executor from volunteers.
   *
   * @param taskId task add executor for
   * @param userId identifier of executor
   * @param user user who performed action.
   * @return task DTO.
   */
  TaskDto  addExecutor(long taskId, long userId, @Nonnull JwtUser user);

  /**
   * Mark task as finished.
   *
   * @param taskId identifier of task to finish
   * @param user user who performed action.
   * @return task DTO.
   */
  TaskDto  finishTask(long taskId, @Nonnull JwtUser user);

  /**
   * Get all tasks created by user.
   *
   * @param userId identifier of user.
   * @return set of task DTO.
   */
  Set<TaskDto> getTasksCreatedByUser(long userId);

  /**
   * Get all tasks where user is a volunteer.
   *
   * @param userId identifier of user.
   * @return set of task DTO.
   */
  Set<TaskDto> getUserVolunteerRequests(long userId);
}
