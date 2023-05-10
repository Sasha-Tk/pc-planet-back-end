package com.pcplanet.pcplanetbackend.component.vendor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
    public boolean existsByVendorName(String vendorName);

    Optional<Vendor> findByVendorName(String vendorName);
}
