package com.lpg.codechallenge.tourismpassmanagement.repository;

import com.lpg.codechallenge.tourismpassmanagement.entity.VendorEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<VendorEntity, UUID> {

}
