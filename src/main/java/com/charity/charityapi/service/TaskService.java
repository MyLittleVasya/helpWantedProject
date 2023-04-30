package com.charity.charityapi.service;

import com.charity.charityapi.dto.TaskDto;
import com.charity.charityapi.dto.request.CreateTaskRequest;
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
   * @param page current number of page to get.
   * @param size size of page to get.
   * @return set of tasks.
   */
  @Nonnull
  Set<TaskDto> getTasks(long page, long size);

  /**
   * Method to get page of tasks that contains specific tags.
   *
   * @param page current number of page to get.
   * @param size size of page to get.
   * @param tags tags which task should contain.
   * @return set of tasks.
   */
  @Nonnull
  Set<TaskDto> getTasksContainingTags(long page, long size, Set<String> tags);

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
   * @param userId volunteer id.
   * @return task DTO.
   */
  TaskDto addVolunteer(long taskId, long userId);

  /**
   * Method to delete volunteer from task.
   *
   * @param taskId task delete volunteer from.
   * @param userId volunteer id.
   * @return task DTO.
   */
  TaskDto  deleteVolunteer(long taskId, long userId);

  /**
   * Method to add executor from volunteers.
   *
   * @param taskId task add executor for
   * @param userId identifier of executor
   * @return task DTO.
   */
  TaskDto  addExecutor(long taskId, long userId);

  /**
   * Mark task as finished.
   *
   * @param taskId identifier of task to finish
   * @return task DTO.
   */
  TaskDto  finishTask(long taskId);
}
