package com.pcplanet.pcplanetbackend.component.mapper;

import com.pcplanet.pcplanetbackend.component.computer_case.motherboard_form_factor.MotherboardFormFactorService;
import com.pcplanet.pcplanetbackend.component.cpu.socket.SocketService;
import com.pcplanet.pcplanetbackend.component.motherboard.Motherboard;
import com.pcplanet.pcplanetbackend.component.motherboard.MotherboardDTO;
import com.pcplanet.pcplanetbackend.component.vendor.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MotherboardMapper implements Mapper<MotherboardDTO, Motherboard> {
    private final VendorService vendorService;
    private final MotherboardFormFactorService motherboardFormFactorService;
    private final SocketService socketService;

    @Override
    public Motherboard mapToEntity(MotherboardDTO motherboardDTO) {
        return new Motherboard(
                motherboardDTO.getComponentName(),
                motherboardDTO.getSku(),
                motherboardDTO.getVendor() == null ?
                        null :
                        vendorService.createVendorOrGetExisting(motherboardDTO.getVendor()),
                motherboardDTO.getImageURL(),
                motherboardDTO.getSocket() == null ?
                        null :
                        socketService.createSocketOrGetExisting(motherboardDTO.getSocket()),
                motherboardDTO.getChipset(),
                motherboardDTO.getFormFactor() == null ?
                        null :
                        motherboardFormFactorService.createFormFactorOrGetExisting(motherboardDTO.getFormFactor()),
                motherboardDTO.getMemoryType(),
                motherboardDTO.getMemorySlotsAmount(),
                motherboardDTO.getMaxRamAmount(),
                motherboardDTO.getSataConnectorsAmount(),
                motherboardDTO.getM2ConnectorsAmount(),
                null,
                null,
                null
        );
    }
}
