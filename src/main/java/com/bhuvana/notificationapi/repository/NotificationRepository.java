package com.bhuvana.notificationapi.repository;

import com.bhuvana.notificationapi.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
}