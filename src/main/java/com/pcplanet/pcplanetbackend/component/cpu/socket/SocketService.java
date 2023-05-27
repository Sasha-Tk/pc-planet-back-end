package com.pcplanet.pcplanetbackend.component.cpu.socket;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SocketService {
    private final SocketRepository socketRepository;

    public Socket findSocketByName(String name) {
        return socketRepository.findSocketBySocketName(name).orElseThrow();
    }

    public Socket createSocketOrGetExisting(String name) {
//        return socketRepository.findSocketBySocketName(name).orElse(socketRepository.save(new Socket(name)));
        return socketRepository.findSocketBySocketName(name).orElseGet(() -> socketRepository.save(new Socket(name)));
    }

    public List<Socket> createSocketsOrGetExisting(List<String> names) {
        return names.stream().map(this::createSocketOrGetExisting).toList();
    }
}
