package com.exam.colegio.service.course.content.resource.activity;

import com.exam.colegio.model.course.CourseScheduled;
import com.exam.colegio.model.course.content.resource.activity.Activity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityService {

        public <T extends Activity> List<T> getActivitiesByTypeAndDateRange(CourseScheduled courseScheduled, Class<T> activityType, Date createdAt, Date dueDate) {
                return courseScheduled.getContentList().stream()
                        .flatMap(content -> content.getResourceList().stream())
                        .filter(activityType::isInstance)
                        .map(activityType::cast)
                        .filter(activity -> activity.getDueDate().before(dueDate) && activity.getDueDate().after(createdAt))
                        .collect(Collectors.toList());
        }

}
