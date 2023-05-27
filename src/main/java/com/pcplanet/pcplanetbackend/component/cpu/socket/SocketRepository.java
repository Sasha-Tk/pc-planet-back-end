package com.pcplanet.pcplanetbackend.component.cpu.socket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SocketRepository extends JpaRepository<Socket, Long> {
    Optional<Socket> findSocketBySocketName(String socketName);
}
