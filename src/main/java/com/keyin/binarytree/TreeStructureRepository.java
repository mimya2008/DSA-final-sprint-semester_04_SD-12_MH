package com.keyin.binarytree;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TreeStructureRepository extends JpaRepository<TreeStructure, Long> {
    Optional<TreeStructure> findByCanonicalInputs(String canonicalInputs);
}