package com.financeapi.repository;

import com.financeapi.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    // Você pode adicionar métodos personalizados aqui, se necessário
}
