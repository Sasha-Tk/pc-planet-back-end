package com.pcplanet.pcplanetbackend.component.motherboard.filter;

import com.pcplanet.pcplanetbackend.component.ComponentFilter;
import com.pcplanet.pcplanetbackend.component.computer_case.motherboard_form_factor.MotherboardFormFactor;
import com.pcplanet.pcplanetbackend.component.cpu.socket.Socket;
import com.pcplanet.pcplanetbackend.component.vendor.Vendor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MotherboardFilter extends ComponentFilter {
    private List<Vendor> vendor = new ArrayList<>();
    private List<Socket> socket = new ArrayList<>();
    private List<String> chipset = new ArrayList<>();
    private List<MotherboardFormFactor> formFactor = new ArrayList<>();
    private List<String> memoryType = new ArrayList<>();
    private List<Short> memorySlotsAmount = new ArrayList<>();
    private List<Short> maxRamAmount = new ArrayList<>();
    private List<Short> sataConnectorsAmount = new ArrayList<>();
    private List<Short> m2ConnectorsAmount = new ArrayList<>();

    @Override
    public List<String> rangeFiltersName() {
        return List.of(
                "sataConnectorsAmount",
                "m2ConnectorsAmount"
        );
    }
}
