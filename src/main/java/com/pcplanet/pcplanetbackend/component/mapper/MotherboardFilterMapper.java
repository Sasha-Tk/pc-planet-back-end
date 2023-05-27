package com.pcplanet.pcplanetbackend.component.mapper;

import com.pcplanet.pcplanetbackend.component.computer_case.motherboard_form_factor.MotherboardFormFactor;
import com.pcplanet.pcplanetbackend.component.computer_case.motherboard_form_factor.MotherboardFormFactorService;
import com.pcplanet.pcplanetbackend.component.cpu.socket.Socket;
import com.pcplanet.pcplanetbackend.component.cpu.socket.SocketService;
import com.pcplanet.pcplanetbackend.component.motherboard.filter.MotherboardFilter;
import com.pcplanet.pcplanetbackend.component.motherboard.filter.MotherboardFilterDTO;
import com.pcplanet.pcplanetbackend.component.vendor.Vendor;
import com.pcplanet.pcplanetbackend.component.vendor.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MotherboardFilterMapper implements Mapper<MotherboardFilterDTO, MotherboardFilter> {
    private final VendorService vendorService;
    private final MotherboardFormFactorService motherboardFormFactorService;
    private final SocketService socketService;

    @Override
    public MotherboardFilter mapToEntity(MotherboardFilterDTO motherboardFilterDTO) {
        if (motherboardFilterDTO == null) {
            motherboardFilterDTO = new MotherboardFilterDTO();
        }
        return new MotherboardFilter(
                motherboardFilterDTO.getVendor() == null ?
                        null :
                        motherboardFilterDTO.getVendor()
                                .stream()
                                .map(vendorService::findByVendorName)
                                .toList(),
                motherboardFilterDTO.getSocket() == null ?
                        null :
                        motherboardFilterDTO.getSocket()
                                .stream()
                                .map(socketService::findSocketByName)
                                .toList(),
                motherboardFilterDTO.getChipset(),
                motherboardFilterDTO.getFormFactor() == null ?
                        null :
                        motherboardFilterDTO.getFormFactor()
                                .stream()
                                .map(motherboardFormFactorService::findFormFactorByFormFactorName)
                                .toList(),
                motherboardFilterDTO.getMemoryType(),
                motherboardFilterDTO.getMemorySlotsAmount(),
                motherboardFilterDTO.getMaxRamAmount(),
                motherboardFilterDTO.getSataConnectorsAmount(),
                motherboardFilterDTO.getM2ConnectorsAmount()
        );
    }

    @Override
    public MotherboardFilterDTO mapToDTO(MotherboardFilter motherboardFilter) {
        return new MotherboardFilterDTO(
                motherboardFilter.getVendor()
                        .stream()
                        .map(Vendor::getVendorName)
                        .toList(),
                motherboardFilter.getSocket()
                        .stream()
                        .map(Socket::getSocketName)
                        .toList(),
                motherboardFilter.getChipset(),
                motherboardFilter.getFormFactor()
                        .stream()
                        .map(MotherboardFormFactor::getFormFactorName)
                        .toList(),
                motherboardFilter.getMemoryType(),
                motherboardFilter.getMemorySlotsAmount(),
                motherboardFilter.getMaxRamAmount(),
                motherboardFilter.getSataConnectorsAmount(),
                motherboardFilter.getM2ConnectorsAmount()
        );
    }
}
