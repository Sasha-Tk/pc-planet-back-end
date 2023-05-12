package com.pcplanet.pcplanetbackend.component.vendor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VendorService {
    private final VendorRepository vendorRepository;

    public boolean vendorAlreadyExist(String vendorName) {
        return vendorRepository.existsByVendorName(vendorName);
    }

    public Vendor findByVendorName(String vendorName) {
        return vendorRepository
                .findByVendorName(vendorName)
                .orElseThrow();
    }

    public Vendor createVendorOrGetExisting(String vendorName) {
        return vendorAlreadyExist(vendorName) ?
                findByVendorName(vendorName) :
                vendorRepository.save(new Vendor(vendorName));
    }
}
