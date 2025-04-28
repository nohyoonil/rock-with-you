package com.example.rock.repository;

import com.example.rock.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

interface NotificationRepository extends JpaRepository<Notification, Long> {
}
