package com.charity.charityapi.dto.mapper;

import com.charity.charityapi.dto.TaskDto;
import com.charity.charityapi.dto.VolunteerDto;
import com.charity.charityapi.persistence.Task;
import com.charity.charityapi.persistence.repository.VolunteerRepository;
import jakarta.annotation.Nonnull;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Bold mapper for:
 * <p>{@link com.charity.charityapi.persistence.Task}</p>
 * <p>{@link com.charity.charityapi.dto.TaskDto}</p>
 */
@Component
@RequiredArgsConstructor
public class TaskDtoMapper {

  private final UserDtoMapper userDtoMapper;

  private final VolunteerDtoMapper volunteerDtoMapper;

  private final VolunteerRepository volunteerRepository;

  /**
   * Map from {@link Task} to {@link TaskDto}.
   *
   * @param task entity to map.
   * @return DTO of task.
   */
  public TaskDto taskToTaskDto(@Nonnull final Task task) {
    final var volunteers = volunteerRepository.findAllByTaskId(task.getId());
    Set<VolunteerDto> volunteersDto = null;
    if (volunteers != null && !volunteers.isEmpty()) {
       volunteersDto = volunteerDtoMapper.volunteerCollectionToVolunteerDtoSet(volunteers);
    }
    final var taskDto = TaskDto.builder()
        .id(task.getId())
        .name(task.getName())
        .author(userDtoMapper.userToDto(task.getAuthor()))
        .description(task.getDescription())
        .shortDescription(task.getShortDescription())
        .finished(task.isFinished())
        .tags(task.getTags())
        .volunteers(volunteersDto)
        .build();
    return taskDto;
  }

  /**
   * Map from collection of {@link Task} to set of {@link TaskDto}.
   *
   * @param tasks collection of entities to map.
   * @return DTO set of tasks.
   */
  public Set<TaskDto> tasksToTasksDto(@Nonnull final Collection<Task> tasks) {
   final var resultSet = new HashSet<TaskDto>();
   for (final var task:tasks) {
     resultSet.add(taskToTaskDto(task));
   }
    return resultSet;
  }



}
